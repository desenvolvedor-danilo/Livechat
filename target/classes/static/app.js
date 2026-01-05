const currentPage = location.href
const links = document.querySelectorAll(".nav-link")
links.forEach((link) => {
  link.classList.add("text-white")
  if (link.href === currentPage) {
    link.classList.add("text-decoration-underline")
  } else {
    link.classList.remove("text-decoration-underline")
  }

})
let currentEmail = localStorage.getItem("email")
const stompClient = new StompJs.Client({
  webSocketFactory: () => new SockJS(`${window.location.origin}/buildrun-livechat-websocket?user=${currentEmail}`)
});

const hrefCadastro = () => {
  window.location.href = "/register"
}
const userna = document.getElementById('usuario')
const pass = document.getElementById('password')
const mail = document.getElementById('email')
const input = document.getElementById("inputPreview")

const sendFiles = document.getElementById("sendFiles")
const img = document.getElementById("imagePreview")
const message = { email: localStorage.getItem("email"), message: '' }
let messages = new Array()
let users = new Array()
let selectFiles = false;
let notify = new Array()
let targetEmail;
let isLogado = false;
let lastSender;
let isNotificated;
let state;
let isNotification = true;
let last;
let lastTo;
let photo = ""
let arquivo = null;

const setNome = (name) => {
  nome = name;
}
const btnConversas = document.getElementById("box-conversas");
const firebaseConfig = {
  apiKey: "AIzaSyBcRyd7KPNzebG-KDkqOgW2XHub6OTndWo",
  authDomain: "livechat-ce9c4.firebaseapp.com",
  projectId: "livechat-ce9c4",
  storageBucket: "livechat-ce9c4.firebasestorage.app",
  messagingSenderId: "646790522951",
  appId: "1:646790522951:web:da157fffedde43d759e962"
}

const preLoadingPhotoProfile = () => {

  fetch(`http://localhost:8080/users/photo-profile?email=${localStorage.getItem("email")}`).then((res) => res.text())
    .then((dado) => {

      const img = document.getElementById("imagePreview")
      if (dado) {
        img.setAttribute("src", dado)
      } else {
        img.setAttribute("src", "https://rozup.ir/view/3716005/default-avatar.png")
      }
    })




}
const inputFiles = async () => {
  sendFiles.click()
  sendFiles.addEventListener("change", async (e) => {
    selectFiles = true;
    arquivo = e.target.files[0]
    if (arquivo) {

      // const div = document.getElementById("previa")
      // const img = document.createElement("img")
      //
      // const url = URL.createObjectURL(arquivo)
      //const formdata = new FormData()
      formdata.append("File", arquivo)
      // const uri = await uploadFile(formdata)
      // div.style.display = "flex"
      // img.setAttribute("src", url)
      // img.style.width = "250px"
      // img.style.height = "250px"
      // div.appendChild(img)
      //return uri;


    }
  })

}
const inputImg = () => {
  input.click()
  input.addEventListener("change", (e) => {
    const arquivo = e.target.files[0]
    if (arquivo) {
      const url = URL.createObjectURL(arquivo)
      const img = document.getElementById("imagePreview")
      img.setAttribute("src", url)

      formdata.append("email", localStorage.getItem("email"))
      formdata.append("file", arquivo)
      fetch("/users/profile", {
        method: "POST",
        body: formdata
      }).then((res) => res.text())
        .then((data) => console.log(data))
    }
  })
}

stompClient.onConnect = (frame) => {

  console.log('Connected: ' + frame);
  stompClient.subscribe('/topics/livechat', (message) => {
    if (!isDuplicate(JSON.parse(message.body))) {
      updateLiveChat(JSON.parse(message.body));
    }
  });
  stompClient.subscribe("/user/queue/message", (message) => {
    if (!isDuplicate(JSON.parse(message.body))) {
      privateChat(JSON.parse(message.body))
    }
    console.log(JSON.parse(message.body))
  })


};
stompClient.onWebSocketError = (error) => {
  console.error('Error with websocket', error);
};
stompClient.onStompError = (frame) => {
  console.error('Broker reported error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
    $("#logado").show();
  }
  else {
    $('#logado').innerText = ''
    $('#logado').hide()
    $("#conversation").hide();
  }
}
async function photoProfile(email) {
  const url = (await fetch("/users/photo-profile?email=" + email)).text()
  return url;
}

function connect() {
  stompClient.activate();
}
function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  const pLogado = document.getElementById("main-content")
  const logado = document.getElementById("logado")
  pLogado.removeChild(logado)
  console.log("Disconnected");
}
async function sendMsgPrivate() {
  const param = new URLSearchParams(window.location.search).get("user")
  localStorage.setItem("email-target", param)
  targetEmail = param
  const uri = await uploadFile()
  if (selectFiles) {
    stompClient.publish({
      destination: "/app/chat/private/",
      body: JSON.stringify({ "to": targetEmail, "urlFile": uri })
    })

    $("#chat").append(`<div class='msg-sent'><div class='username-sent'></div><img src="${uri}" style="max-width:100%;height:auto;"></div>`)

  }
  uri = ""
  if (targetEmail != localStorage.getItem("email")) {
    stompClient.publish({

      destination: "/app/chat/private/",

      body: JSON.stringify({ "to": targetEmail, "message": $("#msgPrivate").val() })
    })
  } else {
    stompClient.publish({

      destination: "/app/chat/private/",

      body: JSON.stringify({ "to": lastSender, "message": $("#msgPrivate").val() })
    })
  }

  const now = new Date()
  const hour = now.getHours().toString().padStart(2, "0")
  const minutes = now.getMinutes().toString().padStart(2, "0")
  fetch("/users/find-users/" + localStorage.getItem("email")).
    then((res) => res.text())
    .then((dado) => {
      const name = dado;
      $("#chat").append("<div class='msg-sent'><div class='username-sent'>" + "~" + name + "</div>" + $('#msgPrivate').val() + "<div class='timestamp-sent'>" + hour + ":" + minutes + "</div></div>")

    }).then(() => {
      $("#msgPrivate").val("")
    }
    )
  firebase.initializeApp(firebaseConfig);
  const messaging = firebase.messaging();
  Notification.requestPermission().then((permission) => {
    if (permission === "granted") {
      fetch("/users/allow-notification", { method: "POST", headers: { "Content-Type": "application/json;charset=UTF-8" }, body: JSON.stringify({ "email": localStorage.getItem("email"), "notificated": true }) }).then((res) => console.log(res.text()))
      messaging.getToken({ vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI" })
        .then((currentToken) => {
          if (currentToken) {
            console.log("Token de notificação:", currentToken); // Aqui você envia o token para seu backend (Spring)          
            fetch("/users/save-token",
              {
                method: "POST", headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: currentEmail, token: currentToken })
              })
          }
        });
    }
  });
}
function sendMessage() {
  let uuid = crypto.randomUUID()
  stompClient.publish({
    destination: "/app/new-message",
    body: JSON.stringify({ 'id': uuid, 'from': localStorage.getItem("email"), 'message': $("#msg").val() })
  }
  );
  $("#msg").val("");
  firebase.initializeApp(firebaseConfig);
  const messaging = firebase.messaging();
  Notification.requestPermission().then((permission) => {
    if (permission === "granted") {
      fetch("/users/allow-notification", { method: "POST", headers: { "Content-Type": "application/json;charset=UTF-8" }, body: JSON.stringify({ "email": localStorage.getItem("email"), "notificated": true }) }).then((res) => console.log(res.text()))
      messaging.getToken({ vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI" })
        .then((currentToken) => {
          if (currentToken) {
            console.log("Token de notificação:", currentToken); // Aqui você envia o token para seu backend (Spring)          
            fetch("/users/save-token",
              {
                method: "POST", headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: currentEmail, token: currentToken })
              })
          }
        });
    }
  });
}
function isDuplicate(msg) {
  return msg.content === ""
}

function updateLiveChat(message) {
  if (isDuplicate(message)) return
  let userCurrent = localStorage.getItem("usuario")
  if (userCurrent == message.from) {
    $("#livechat").append("<div class='msg-received'>" + "<div class='username-received'> ~ " + message.from + "</div>" + message.content + "<div class='timestamp'>" + message.timeStamp + "</div></div>")
  } else {
    $("#livechat").append("<div class='msg-sent'>" + "<div class='username-sent'> ~ " + message.from + "</div>" + message.content + "<div class='timestamp-sent'>" + message.timeStamp + "</div></div>")
  }
}
function privateChat(message) {
  if (isDuplicate(message)) return
  notifications()


  $("#chat").append("<div class='msg-received'>" + "<div class='username-received'> ~ " + message.name + "</div>" + message.message + "<div class='timestamp-received'>" + message.timeStamp + "</div></div>")
  lastSender = message.from
  lastTo = message.to

  //   $("#chat").append("<div class='msg-received'>" + "<div class='username-received'> ~ " + message.name + "</div>" + message.message + "<div class='timestamp-received'>" + message.timeStamp + "</div></div>")
  //   lastSender = message.from
  //   lastTo = message.to
}
const handleCadastro = () => {
  fetch("/users/save", {
    headers: { "Content-Type": "application/json; charset=UTF-8" },
    method: 'POST',
    body: JSON.stringify({ 'email': $("#email").val(), 'senha': $("#password").val(), 'usuario': $("#usuario").val(), "name": $("#name") })
  }).then((res) => {
    if (res.status == 200) {
      window.location.href = "/login"
    }
    console.log(res.status);
  })

}
const notifications = () => {

  fetch("/conversas/private?from=" + localStorage.getItem("email"))
    .then((res) => res.json())

    .then((data) => {
      const lastByUser = {}
      data.forEach(msg => {
        lastByUser[msg.id] = msg;
      });
      $("#notifications").empty()
      Object.values(lastByUser).forEach((msg) => {
        const participante = msg.participantes.filter(part => part !== localStorage.getItem("email"))
        fetch("/users/find-users/" + participante).
          then((res) => res.text())
          .then(async (dado) => {
            const url = await photoProfile(participante)
            const name = dado;
            $("#notifications").append(
              `<a href="/private.html?user=${participante}" class="contact-item online-contact">
				<img  class="contact-avatar" src="${url ? url : "https://rozup.ir/view/3716005/default-avatar.png"}" width="25" height="25">
				<div class="contact-info">
          <div class="">${name}</div>
					<span class="contact-name">${msg.lastMessage}</span>
					<span class="timeStamp">${msg.updatedAt}</span>	
       </div>
			</a>`
            )
          })
      })
    })
}
const loadedMessagesPrivate = () => {
  const param = new URLSearchParams(window.location.search).get("user")
  fetch(`/private-messages/find?to=${param}&from=${localStorage.getItem("email")}`)
    .then((res) => res.json())
    .then((data) => {

      data.map((msg) => {
        if (msg.from == localStorage.getItem("email") || msg.to == localStorage.getItem("email")) {
          if (msg.to != localStorage.getItem("email")) {
            $("#chat").append(
              "<div class='msg-sent'>" + "<div class='username-sent'> ~ " + msg.name + "</div>" + msg.message + "<div class='timestamp-sent'>" + msg.timeStamp + "</div></div>")

          } else {
            $("#chat").append(
              "<div class='msg-received'>" + "<div class='username-received'> ~ " + msg.name + "</div>" + msg.message + "<div class='timestamp-received'>" + msg.timeStamp + "</div></div>")
          }
        }
      })
    })
}

const loadedMessages = () => {
  let userCurrent = localStorage.getItem("usuario")
  fetch("/messages/findall")
    .then((res) => res.json())
    .then((data) => messages = data)
    .then(() => {
      messages.map((msg) => {
        if (isDuplicate(msg.message)) return
        if (userCurrent == msg.username) {

          $("#livechat").append("<div class='msg-received'>" + "<div class='username-received'> ~ " + msg.username + "</div>" + msg.message + "<div class='timestamp'>" + msg.timeStamp + "</div></div>")
        } else {
          $("#livechat").append("<div class='msg-sent'>" + "<div class='username-sent'> ~ " + msg.username + "</div>" + msg.message + "<div class='timestamp-sent'>" + msg.timeStamp + "</div></div>")
        }

      })
    })
}

const handleLogin = () => {

  fetch('/users/find-by-email?email=' + $('#correio').val(), {
    headers: { "password": $("#senha").val() }
  })
    .then((res) => {
      if (!res.ok) {
        state = false
        alert("Usuário ou senha incorretos")
      } else {
        state = true
        return res.json()
      }

    }).then((dado) => {
      localStorage.setItem("email", $("#correio").val())
      localStorage.setItem("usuario", dado.usuario)
      localStorage.setItem("logado", true)
    }).then(() => connect())

    .then(() => {
      if (state) {
        window.location.href = "/notifications"
      }
    })
}
const uploadFile = async () => {
  const formdata = new FormData()
  formdata.append("File", arquivo)
  const res = await fetch("/files/save", {
    method: "POST",
    body: formdata
  })
  return await res.text()
}
const logout = () => {
  localStorage.clear()
  location.href = "/login"
}
const findAllUsers = () => {
  if (localStorage.getItem("logado")) {
    fetch("/users/findall")
      .then((res) => res.json())
      .then((dado) =>
        users = dado)
      .then(() => {
        users.map((user) => {
          if (user.email != localStorage.getItem("email")) {
            $("#contactList").append(`
      <a href="private.html?user=${user.email}" class="contact-item online-contact">
        <div class="contact-avatar">A</div>
        <div class="contact-info">
          <span class="contact-name">${user.name}</span>
        </div>
      </a>`)
          }
        })

      })
  }
}

$(function() {
  $("form").on("submit", (e) => e.preventDefault());
  $("#disconnect").click(() => disconnect());
  $("#logout").click(() => logout())
  $('document').ready(() => preLoadingPhotoProfile())
  $('#hiddenInput').click(() => inputFiles())
  $("#send").click(() => sendMessage());
  $("#imagePreview").click(() => inputImg())
  $("#cadastro").click(() => handleCadastro());
  $("#login").click(() => handleLogin());
  $("document").ready(() => connect())
  $("document").ready(() => findAllUsers())
  $("document").ready(() => notifications())
  $("#livechat").ready(() => loadedMessages())
  $("#chat").ready(() => loadedMessagesPrivate())
  $("#sendPrivate").click(() => sendMsgPrivate())

  document.getElementById('profile').addEventListener('load', preLoadingPhotoProfile())
}); 
