//import { listenMessages, requestPermission } from "../config_firebase/messagesFirebase.js";
import { listenMessages, requestPermission } from "../config_firebase/messagesFirebase.js";
import { getCurrentTime } from "../utils/helpers.js";
import { uploadFile } from "./fileService.js";

let targetEmail;
let lastSender;
let selectFiles = false;

export const setLastSender = (sender) => {
  lastSender = sender;
};

export const setSelectFiles = (value) => {
  selectFiles = value;
};

export const sendMsgPrivate = async (stompClient) => {
  const param = new URLSearchParams(window.location.search).get('user');
  localStorage.setItem('email-target', param);
  targetEmail = param;

  if (targetEmail === localStorage.getItem('email')) {
    targetEmail = lastSender;
  }

  let uri = null;
  if (selectFiles) {
    uri = await uploadFile();
    selectFiles = false;
  }

  stompClient.publish({
    destination: '/app/chat/private/',
    body: JSON.stringify({
      to: targetEmail,
      message: uri ? null : $('#msgPrivate').val(),
      urlFile: uri?.url || null,
    }),
  });

  $('#chat').append(`
    <div class='msg-sent'>
      <div class='username-sent'>~${localStorage.getItem('usuario')}</div>
      ${uri
      ? `<img src="${uri.url}" style="max-width:100%;height:auto;">`
      : $('#msgPrivate').val()
    }
      <div class='timestamp-sent'>${getCurrentTime().hour}:${getCurrentTime().minutes}</div>
    </div>
  `);

  $('#msgPrivate').val('');
  try {
    requestPermission().then((token) => console.log(token))
    listenMessages((payload) => console.log(payload.notification.body))
  } catch (error) {
    console.log(error.message)
  }

};
