navigator.getUserMedia =
  navigator.getUserMedia ||
  navigator.webkitGetUserMedia ||
  navigator.mozGetUserMedia

//start video recording and returns stop-function inside a promise.
// When stop-function is called it stops recording and returns the video data inside a promise
async function startRecording(stream, startDelay) {
  let recorder = new MediaRecorder(stream)
  let data = []

  recorder.ondataavailable = (event) => data.push(event.data)
  //wait the startdelay time
  await new Promise((resolve) =>
    setTimeout(() => {
      recorder.start()
      resolve()
    }, startDelay)
  )

  console.log('recorder: ', recorder.state)

  //returns a function that is used to stop recording
  return async function () {
    recorder.stop()
    await new Promise((resolve, reject) => {
      recorder.onstop = resolve
      recorder.onerror = (event) => reject(event.name)
    })
    return data
  }
}

async function videophase() {
  //Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
  document.addEventListener(
    'keydown',
    function (e) {
      if ([37, 38, 39, 40, 8].indexOf(e.keyCode) > -1) {
        if (
          e.target.tagName == 'INPUT' ||
          e.target.type == 'text' ||
          e.target.tagName == 'TEXTAREA'
        ) {
          return
        }

        e.preventDefault()
      }
    },
    false
  )

  var config = window.testConfig
  var logMessages = []
  var dataToStore = new FormData()

  //mediasettings
  var mediaContstraints = {
    audio: true
  }
  if (config.recordAudioOnly) {
    dataToStore.append('fname', 'recording.mp3')
    mediaRecorderOptions = { mimeType: 'audio/webm' }
  } else {
    dataToStore.append('fname', 'recording.mp4')
    mediaRecorderOptions = { mimeType: 'video/webm;codecs=vp8,opus' }
    mediaContstraints.video = {
      width: { exact: 1280 },
      height: { exact: 720 }
    }
  }

  //Startbutton
  const startButton = document.querySelector('#start-button')
  startButton.innerHTML = config.button || 'Start'
  startButton.style.display = 'true'

  const preview = document.querySelector('#camera-video')

  let stream = null
  //gets camera stream.
  //this is a function so camera stream is started only when its used first time
  async function getStream() {
    if (stream === null) {
      stream = await navigator.mediaDevices.getUserMedia(mediaContstraints)
    }
    return stream
  }

  //show preview
  if (config.showVideoPreview) {
    preview.srcObject = await getStream()
    preview.captureStream = preview.captureStream || preview.mozCaptureStream

    await new Promise((resolve) => (preview.onplaying = resolve))
  }
  // Wait until the button was clicked
  await new Promise((resolve, reject) => {
    startButton.addEventListener('click', (event) => resolve())
  })
  log('meta', 'start clicked')

  startButton.style.display = 'None'
  preview.style.display = 'None'

  //TODO - Figure out logic for videoPreview
  //and how it should work with recordDuringVideo and recordAfterVideo
  console.log('#Main-video: ', document.querySelector('#main-video'))
  if (!document.querySelector('#main-video') == '') {
  }
  //showVideo
  const mainVideo = document.querySelector('#main-video')
  mainVideo.style.display = 'inherit'

  mainVideo.play()

  //Record during video
  if (config.recordingOnStart) {
    const stop = await startRecording(await getStream(), 0)

    await new Promise((resolve, reject) => {
      mainVideo.addEventListener('ended', (event) => resolve())
    })

    const data = await stop()

    const recordedBlob = new Blob(data, {
      type: mediaRecorderOptions.mimeType
    })

    dataToStore.append('data', recordedBlob)
  }
  //Record after video
  if (config.recordingAfterVideo) {
    const recordButton = document.querySelector('#record-button')

    await new Promise((resolve, reject) => {
      if (mainVideo.ended) {
        resolve()
      }
      mainVideo.addEventListener('ended', (event) => resolve())
    })
    recordButton.innerHTML = config.recordButtonStart || 'Start recording'
    recordButton.style.visibility = 'visible'
    // Wait until the button was clicked
    await new Promise((resolve, reject) => {
      recordButton.addEventListener('click', (event) => resolve())
    })

    const stop = await startRecording(await getStream(), 0)

    recordButton.innerHTML = config.recordButtonEnd || 'Stop recording'

    //Stop recording eventhandler
    await new Promise((resolve, reject) => {
      recordButton.addEventListener('click', (event) => resolve())

      document.addEventListener('keydown', (event) => {
        if (event.key == ' ' || event.key == 'Spacebar') {
          resolve()
        }
      })
    })

    const data = await stop()
    recordButton.style.visibility = 'hidden'

    const recordedBlob = new Blob(data, {
      type: mediaRecorderOptions.mimeType
    })

    dataToStore.append('data', recordedBlob)
  }
  stream.getTracks().forEach((track) => track.stop())
  preview.style.display = 'none'

  //send recorded data
  try {
    await fetch(document.URL + '/video', {
      body: dataToStore,
      method: 'post'
    })
  } catch (e) {
    console.log(e)
  }

  //Send meta-data
  async function sendMetaData() {
    var response = null
    try {
      response = await fetch(document.URL, {
        method: 'post',
        body: JSON.stringify(logMessages)
      })
    } catch (e) {
      response = await new Promise((resolve, reject) => {
        setTimeout(function () {
          console.log('...resending')
          resolve(sendMetaData())
        }, 1000)
      })
    } finally {
      return response.json()
    }
  }
  var response = await sendMetaData()

  // //Initiate next phase
  // if (typeof response !== 'undefined') {
  //   response = JSON.parse(response)
  // }
  if (response.redirect) {
    console.log('JSON_REDIRECTING')
    window.location.replace(response.redirect)
  } else {
    var url = document.URL
    var currentPhase = parseInt(url.substr(url.lastIndexOf('/') + 1))
    url = url.slice(0, url.lastIndexOf('/') + 1)
    if (!isNaN(currentPhase)) {
      console.log('Redirecting ' + isNaN(currentPhase))
      window.location.href = url + (currentPhase + 1)
    } else {
      location.reload()
    }
  }

  //writeData
  function log(messageType, message) {
    logMessages.push({
      timestamp: new Date().toString(),
      messageType: messageType,
      message: message
    })
    console.log(logMessages[logMessages.length - 1])
  }
}
videophase()
