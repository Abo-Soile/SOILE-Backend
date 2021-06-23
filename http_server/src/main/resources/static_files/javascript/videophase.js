navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

require([
  "dojo/request/xhr",
  "dojo/json",
  "dojo/ready"
  ],
function(xhr, JSON, ready) {
  ready(async function () {

    var config = window.testConfig;
    var logMessages = [];
    var dataToStore = new FormData();

    //Startbutton
    const startButton = document.querySelector('#start-button');
    startButton.innerHTML = config.button || "Start";
    startButton.style.display = "inherit";
    // Wait until the button was clicked
    await new Promise((resolve, reject) => {
      startButton.addEventListener('click', event => resolve());
    });
    log("meta", "start clicked");
    startButton.style.display = "None";

    //mediasettings
    var mediaContstraints = {
      audio: true,
    };
    if (config.recordAudioOnly) {
      dataToStore.append("fname", "recording.mp3");
      mediaRecorderOptions = {mimeType: 'audio/webm'};

    } else {
      dataToStore.append("fname", "recording.mp4");
      mediaRecorderOptions = {mimeType: 'video/webm;codecs=vp8,opus'};
      mediaContstraints.video = {width: {exact: 1280}, height: {exact: 720}}
    }

    //TODO - Figure out logic for videoPreview
    //and how it should work with recordDuringVideo and recordAfterVideo
    console.log("#Main-video: ", document.querySelector('#main-video'));
    if(!document.querySelector('#main-video')==""){

    }
    //showVideo
    const mainVideo = document.querySelector('#main-video');
    mainVideo.style.display = "inherit";
    let recorder = null;
    //Record during video
    if (config.recordingOnStart) {
      stream = await navigator.mediaDevices.getUserMedia(mediaContstraints);
      log("meta", "Camera access ok");

      recorder = new MediaRecorder(stream, mediaRecorderOptions);
      var chunks = [];
      recorder.ondataavailable = (e) => {
        chunks.push(e.data)
        console.log("recorded chunk: ", chunks.length);
      };
      mainVideo.addEventListener('ended', event => {
        recorder.stop();
        console.log("stopped the recorder: ", recorder);
      });
      recorder.start();
      console.log("starting recorder: ", recorder);
    }

    mainVideo.play();
    console.log("runing main video: ", mainVideo);

    await new Promise((resolve, reject) => {
      mainVideo.addEventListener('ended', event => resolve());
    });
    console.log("stopped main video: ", mainVideo);

    //Record after video
    if (config.recordingAfterVideo) {
      stream = await navigator.mediaDevices.getUserMedia(mediaContstraints);
      log("meta", "Camera access ok");

      recorder = new MediaRecorder(stream, mediaRecorderOptions);
      var chunks = [];
      recorder.ondataavailable = (e) => {
        chunks.push(e.data);
        console.log("recorded chunk: ", chunks.length);
      };

      console.log("Wait for: ", config.startRecordingDelay);
      await Promise.race([
        (async () => {
          await new Promise((resolve) => setTimeout(resolve, config.startRecordingDelay))
          if(config.startRecordingDelay=='undefined') {
            console.log("Delay time: undefined")
            resolve();
          };
        })(),
        (async () => {
          await new Promise((resolve) => document.addEventListener("keydown", event => {
            console.log("keypressed to skip waiting: ", event.key);
            resolve();
          }));
        })(),
      ]);
      recorder.start();
      console.log("starting recorder: ", recorder);

      //Stop recording eventhandler
      await new Promise((resolve, reject) => {
        document.addEventListener('keydown', event => {
          if (event.key == " " || event.key == "Spacebar") {
            resolve();
          }
        });
      });
      console.log("recorder: ", recorder);
      recorder.stop();
    }

    //Send recording
    if(config.recordingOnStart || config.recordingAfterVideo) {
      dataToStore.append("data", new Blob(chunks, {type: mediaRecorderOptions.mimeType}));
      await xhr.post(document.URL + "/video", {
        timeout: 50000,
        data: dataToStore,
      });
    }

    //TODO - Fix catch
    //Send meta-data
    async function sendMetaData(){
      return await xhr.post(document.URL, {
        timeout:10000,
        data:JSON.stringify(logMessages)
      }).catch(error => {
        setTimeout(function(){
          console.log("...resending");
          sendMetaData();
        }, 1000);
      });
    }
    var response = await sendMetaData();

    //Initiate next phase
    if (typeof response !== 'undefined') {
      response = JSON.parse(response);
    }
    if(response.redirect) {
      console.log("JSON_REDIRECTING");
      window.location.replace(response.redirect);
    }
    else {
      var url = document.URL;
      var currentPhase = parseInt(url.substr(url.lastIndexOf("/")+1));
      url = url.slice(0, url.lastIndexOf("/")+1);
      if(!isNaN(currentPhase)) {
        console.log("Redirecting " + isNaN(currentPhase));
        window.location.href = url+(currentPhase+1);
      }else {
        location.reload();
      }
    }

    //writeData
    function log(messageType, message) {
      logMessages.push({
        timestamp: new Date().toString(),
        messageType: messageType,
        message: message,
      })
      console.log(logMessages[logMessages.length - 1])
    }

    //Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
    document.addEventListener("keydown", function (e) {
      if ([37, 38, 39, 40, 8].indexOf(e.keyCode) > -1) {
        if (e.target.tagName == "INPUT" || e.target.type == "text" || e.target.tagName == "TEXTAREA") {
          return
        }

        e.preventDefault();
      }
    }, false);
  });
});