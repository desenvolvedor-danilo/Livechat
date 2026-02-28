export const createStompClient = (email) => {
  return new StompJs.Client({
    webSocketFactory: () =>
      new SockJS(`${window.location.origin}/buildrun-livechat-websocket?user=${email}`)
  });
};
