let stompClient = null;
export function connectStomp(client, onPrivateMessage) {
  stompClient = client;
  stompClient.onConnect = (frame) => {
    console.log(frame)
    subscribe(onPrivateMessage)
  }
}
function subscribe(callback) {
  stompClient.subscribe("/user/queue/message", (message) => {
    const parsed = JSON.parse(message.body);
    callback(parsed);
  })
}
