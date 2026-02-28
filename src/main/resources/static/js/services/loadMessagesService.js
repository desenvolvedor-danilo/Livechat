import { header } from "../utils/headers.js"

export const allMessagesPrivate = () => {
  const param = new URLSearchParams(window.location.search).get("user")
  fetch(`/private-messages/find?to=${param}&from=${localStorage.getItem("email")}`, { headers: header() })
    .then((res) => res.json())
    .then((data) => {

      data.map((msg) => {
        if (msg.from == localStorage.getItem("email") || msg.to == localStorage.getItem("email")) {
          if (msg.url) {
            $("#chat").append(`<div class='msg-sent'><div class='username-sent'></div><img src="${msg.url}" style="max-width:100%;height:auto;"></div>`)
          } else
            if (msg.to != localStorage.getItem("email")) {

              $("#chat").append(
                `<div class='msg-sent'><div class='username-sent'> ~${msg.user}</div>${msg.message}<div class='timestamp-sent'>${msg.time}</div></div>`)
            } else {
              $("#chat").append(
                `<div class='msg-received'><div class='username-received'> ~${msg.user} </div>${msg.message} <div class='timestamp-received'> ${msg.time}</div></div>`)


            }
        }
      })
    })
}

