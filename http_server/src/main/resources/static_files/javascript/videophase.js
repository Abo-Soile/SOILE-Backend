require([
  "dojo/request/xhr",
  "dojo/json",
  "levenshtein",
  "dojo/ready"
  ],
function(
  xhr,
  JSON,
  levenshtein,
  ready
  ) {
  ready(function() {

    var config = window.testConfig

    var recordAfterVideo = config.recordingAfterVideo

    var isRecordingAfter = false;

    //Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
    document.addEventListener("keydown", function (e) {
      if([37,38,39,40,8].indexOf(e.keyCode) > -1){
        //console.log(e);
        if(e.target.tagName == "INPUT" || e.target.type == "text" || e.target.tagName == "TEXTAREA") {
          return
        }

        e.preventDefault();
      }
    }, false);


    function sendCapture(fd) {
      xhr.post(document.URL + "/video",
        {
          timeout: 50000,
          data: fd,
        }).then(
          function (response) {
            console.log("Upload Done")
            console.log(response)

            sendData(dataInput)
          })
    }


    function sendData(d) {

      //Send data xhr,
      xhr.post(document.URL, {timeout:10000,data:JSON.stringify(d)}).then(
        function(response) {

          if (typeof response !== 'undefined') {
            response = JSON.parse(response);
          }

          if(response.redirect) {
            console.log("JSON_REDIRECTING");
            window.location.replace(response.redirect);
          }

          else {
            //Navigate to next phase
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
        }, function(error) {
          console.log("Sending data failed, retrying...");
          setTimeout(function() {
            console.log("...resending");
            sendData(d);
          }, 1000);
        });
    }

    let recorder = false;

    const constraints = {
      video: true
    };

    const chunks = []
    let onData = (e) => {
      console.log("got webcamera data")
      chunks.push(e.data)
    }

    const mainVideo = document.querySelector('#main-video');
    const video = document.querySelector('#camera-video');
    const warning = document.querySelector('#warning');

    const dataInput = [];

    function writeData(messageType, message) {
      dataInput.push({
        timestamp: new Date().toString(),
        messageType: messageType,
        message: message,
      })

      console.log(dataInput[dataInput.length - 1])
    }

    function onKeyInput(event) {
      let key = event.key;

      writeData("input", key);

      /* Stop recording on space*/
      if (recordAfterVideo) {
        if (key == " " || key == "Spacebar") {
          let r = recorder.requestData()
          recorder.stop()
        }
      }
    }

    /*Prevent backspace from going back*/
    document.addEventListener("keydown", function (e) {
      if ([8, 32].indexOf(e.keyCode) > -1) {
        e.preventDefault();
      }
    }, false);

    writeData("meta", "page loaded");

    video.width = 320;

    // navigator.mediaDevices.getUserMedia(constraints).
    //     then((stream) => { video.srcObject = stream });

    const vgaConstraints = {
      video: { width: { exact: 640 }, height: { exact: 480 } },
      audio: false,
    };

    mainVideo.addEventListener('canplaythrough', function () {
      writeData("meta", "Video loaded");
      startPlayback();
    });


    function startPlayback() {
      writeData("meta", "Requesting camera access");

      if (recordAfterVideo) {
        mainVideo.style.display = "inherit"

        mainVideo.play()

        document.addEventListener("keydown", onKeyInput);
        writeData("meta", "started playback")
      } else {
        navigator.mediaDevices.getUserMedia(vgaConstraints).
          then((stream) => {

            mainVideo.style.display = "inherit"
            warning.style.display = "none"

            writeData("meta", "Camera access ok");
            video.srcObject = stream;

            recorder = new MediaRecorder(stream)
            recorder.ondataavailable = onData
            recorder.onstop = stopRecording

            document.addEventListener("keypress", onKeyInput);
            writeData("meta", "start input recording")

            recorder.start()
            mainVideo.play()
            writeData("meta", "started playback")
          });
      }
    }

    const stopRecording = function() {
      console.log("Stopping")
      // Upload
      // https://gist.github.com/rissem/d51b1997457a7f6dc4cf05064f5fe984
      const bigVideoBlob = new Blob(chunks, { 'type': 'video/webm; codecs=webm' })

      let fd = new FormData()
      fd.append('fname', 'testvideo.webm')
      fd.append('data', bigVideoBlob)

      writeData("meta", "starting upload")

      // var request = new XMLHttpRequest();
      // request.open("POST", "/");
      // request.send(fd);

      sendCapture(fd)

      writeData("meta", "upload done")

      console.log(dataInput)
    }

    mainVideo.onended = function () {
      writeData("meta", "Playback done")
      video.pause()

      if (recordAfterVideo) {
        navigator.mediaDevices.getUserMedia(vgaConstraints).
          then((stream) => {

            isRecordingAfter = true;

            mainVideo.style.display = "inherit"
            warning.style.display = "none"

            video.srcObject = stream;

            writeData("meta", "Camera access ok");

            recorder = new MediaRecorder(stream)
            recorder.ondataavailable = onData
            recorder.onstop = stopRecording

            writeData("meta", "start input recording")

            recorder.start()
          });

      } else {
        let r = recorder.requestData()
        recorder.stop()
      }

    }
  });
});