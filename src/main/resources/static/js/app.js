
import { allMessagesPrivate } from "./services/loadMessagesService.js";
import { createStompClient } from "./config/stomp.js";
import { handleLogin, handleCadastro, logout } from "./services/authService.js";
import { sendMsgPrivate } from "./services/messageService.js";
import { inputFiles, input } from "./services/fileService.js";
import { connectStomp } from "./services/onConnectedStomp.js";
import { handlePrivate } from "./config/handles/handlePrivateMessages.js";
import { findUsers } from "./services/findUsersService.js";
import { notifications } from "./services/notificationService.js";
import { searchPhotoProfile } from "./services/profileService.js";

document.addEventListener
  ("DOMContentLoaded", () => {
    const email = localStorage.getItem("email");
    const stompClient = createStompClient(email);
    stompClient.activate();
    connectStomp(stompClient, handlePrivate)
    $(document).ready(() => allMessagesPrivate())
    $(document).ready(() => notifications())
    $(document).ready(() => searchPhotoProfile())
    $(document).ready(() => findUsers())
    $('#hiddenInput').click(() => inputFiles(input))
    $("#login").click(() => handleLogin());
    $("#cadastro").click(() => handleCadastro());
    $("#logout").click(() => logout());
    $("#sendPrivate").click(() => sendMsgPrivate(stompClient));
  });
