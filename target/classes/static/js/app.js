
import { allMessagesPrivate } from "./services/loadMessagesService.js";
import { createStompClient } from "./config/stomp.js";
import { handleLogin, handleCadastro, logout } from "./services/authService.js";
import { sendMsgPrivate } from "./services/messageService.js";
document.addEventListener
  ("DOMContentLoaded", () => {

    const email = localStorage.getItem("email");
    const stompClient = createStompClient(email);
    stompClient.activate();

    $(document).ready(() => allMessagesPrivate())
    $("#login").click(() => handleLogin());
    $("#cadastro").click(() => handleCadastro());
    $("#logout").click(() => logout());
    $("#sendPrivate").click(() => sendMsgPrivate(stompClient));
  });
