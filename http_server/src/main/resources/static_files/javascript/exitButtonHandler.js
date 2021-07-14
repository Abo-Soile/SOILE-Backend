const exitButton = document.querySelector('#exit-button')

const url = window.location.href
const afterExperiment = url.slice(url.indexOf('experiment'))

const splitted = afterExperiment.split('/')
const begining = url.slice(0, url.indexOf('experiment'))
const exitUrl = `${begining}${splitted[0]}/${splitted[1]}/exit`

console.log(exitUrl)

exitButton.addEventListener('click', (event) => {
    console.log('exit was clicked')
    window.location.href = exitUrl
})