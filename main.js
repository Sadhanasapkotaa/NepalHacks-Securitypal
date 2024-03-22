const APP_ID = "6f3b4c9135de4bc5ae86540f7025c566"
const TOKEN = "007eJxTYChTfVS1a1Z8Q8Ue/kVP2SqmXVji/mJB85X0Or1Xjzi4ko8rMJilGSeZJFsaGpumpJokJZsmplqYmZoYpJkbGJkmm5qZpbD+TW0IZGS48O0VAyMUgvgsDLmJmXkMDAC96CFg"
const CHANNEL = "main"
const CLIENT = AgoraRTC.createClient({mode:'rtc', codec:'vp8'})

let localTracks = []
let remoteUsers = {}

let joinAndDisplayLocalStream = async () =>{
    let UID = await client.join(APP_ID, CHANNEL, TOKEN, null)
    localTracks = await AgoraRTC.createMicrophoneAndCameraTracks()
    let player = `<div class="video-container" id="user-container-${UID}">
    <div class="video-player" id="user-${UID}"></div>
    </div>`
    document.getElementById('video-streams').insertAdjacentHTML('beforeend', player)
    localTracks[1].play(`user-${UID}`)
    await client.publish([localTracks[0], localTracks[1]])
}
let joinStream = async () => {
    await joinAndDisplayLocalStream()
    document.getElementById('join-btn').style.display = 'none'
    document.getElementById('stream-controls').style.display = 'flex'
    
}

document.getElementById('join-btn').addEventListener('click', joinStream)