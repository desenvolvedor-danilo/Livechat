//const stompClient = new StompJs.Client({ brokerURL: 'ws://' + window.location.host + '/buildrun-livechat-websocket' });
const stompClient = new StompJs.Client({
	webSocketFactory: () => new SockJS(`${window.location.origin}/buildrun-livechat-websocket`)
});
stompClient.heartbeatIncoming = 10000;
stompClient.heartbeatOutgoing = 10000;


const userna = document.getElementById('usuario')
const pass = document.getElementById('password')
const mail = document.getElementById('email')
const message = { email: localStorage.getItem("email"), message: '' }
//let isConnected;
let messages = new Array()
let isNotificated;
let state;
let isNotification = true;
let currentEmail = localStorage.getItem("email")
const btnConversas = document.getElementById("box-conversas");
const firebaseConfig = {
	apiKey: "AIzaSyBcRyd7KPNzebG-KDkqOgW2XHub6OTndWo",
	authDomain: "livechat-ce9c4.firebaseapp.com",
	projectId: "livechat-ce9c4",
	storageBucket: "livechat-ce9c4.firebasestorage.app",
	messagingSenderId: "646790522951",
	appId: "1:646790522951:web:da157fffedde43d759e962"
}

// const hideDiv = () => {
//     fetch("/users/is-notificated?email="+localStorage.getItem("email"))
//     .then((res)=>res.json())
//     .then((dado)=>isNotificated=dado)
//     .then(()=>{
//     if(isNotificated){
//     document.querySelector(".permission-overlay").style.display = "none"   
//     }
//  )}
// }
// $("#allow-button").click(() => {
//
//     firebase.initializeApp(firebaseConfig);
//     const messaging = firebase.messaging();
//     Notification.requestPermission().then((permission) => {
//         if (permission === "granted") {
//             fetch("/users/allow-notification", { method: "POST", headers: { "Content-Type": "application/json;charset=UTF-8" }, body: JSON.stringify({ "email": localStorage.getItem("email"), "notificated": true }) }).then((res) => console.log(res.text()))
//             messaging.getToken({ vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI" })
//                 .then((currentToken) => {
//                     if (currentToken) {
//                         console.log("Token de notificação:", currentToken); // Aqui você envia o token para seu backend (Spring)          
//                         fetch("/users/save-token",
//                             {
//                                 method: "POST", headers: { "Content-Type": "application/json" },
//                                 body: JSON.stringify({ email: currentEmail, token: currentToken })
//                             })
//
//
//
//                     }
//
//                 });
//         }
//     });
//
// })
//
//
stompClient.onConnect = (frame) => {
	console.log('Connected: ' + frame);
	stompClient.subscribe('/topics/livechat', (message) => {

		if (!isDuplicate(JSON.parse(message.body))) {

			updateLiveChat(JSON.parse(message.body));
		}
	});

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


function sendMessage() {
	let uuid = crypto.randomUUID()
	stompClient.publish({
		destination: "/app/new-message",
		body: JSON.stringify({ 'id': uuid, 'email': message.email, 'message': $("#msg").val() })
	}
	);

	$("#msg").val("");

	// firebase.initializeApp(firebaseConfig);
	// const messaging = firebase.messaging();
	// Notification.requestPermission().then((permission) => {
	//     if (permission === "granted") {
	//         fetch("/users/allow-notification", { method: "POST", headers: { "Content-Type": "application/json;charset=UTF-8" }, body: JSON.stringify({ "email": localStorage.getItem("email"), "notificated": true }) }).then((res) => console.log(res.text()))
	//         messaging.getToken({ vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI" })
	//             .then((currentToken) => {
	//                 if (currentToken) {
	//                     console.log("Token de notificação:", currentToken); // Aqui você envia o token para seu backend (Spring)          
	//                     fetch("/users/save-token",
	//                         {
	//                             method: "POST", headers: { "Content-Type": "application/json" },
	//                             body: JSON.stringify({ email: currentEmail, token: currentToken })
	//                         })
	//
	//
	//
	//                 }
	//
	//             });
	//     }
	// });


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
		//  $("#livechat").append("<p id='textodeoutro'>" + message.from + "</p>")
		$("#livechat").append("<div class='msg-sent'>" + "<div class='username-sent'> ~ " + message.from + "</div>" + message.content + "<div class='timestamp-sent'>" + message.timeStamp + "</div></div>")
	}
}



const handleCadastro = () => {


	fetch("/users/save", {
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		method: 'POST',
		body: JSON.stringify({ 'email': $("#email").val(), 'senha': $("#password").val(), 'usuario': $("#usuario").val() })
	}).then((res) => console.log(res.json()))

}
const loadedMessages = () => {
	let userCurrent = localStorage.getItem("usuario")
	fetch("/messages/findall")
		.then((res) => res.json())
		.then((data) => messages = data)
		.then(() => {
			messages.map((msg) => {
				console.log(msg.message)
				if (isDuplicate(msg.message)) return
				if (userCurrent == msg.username) {

					$("#livechat").append("<div class='msg-received'>" + "<div class='username-received'> ~ " + msg.username + "</div>" + msg.message + "<div class='timestamp'>" + msg.timeStamp + "</div></div>")
				} else {
					//  $("#livechat").append("<p id='textodeoutro'>" + message.from + "</p>")
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
		}).then(() => connect())

		.then(() => {
			if (state) {
				window.location.href = "/"
			}
		})
}
// function teste() {
//     fetch("/users/status?conectado=" + localStorage.getItem("email"))
//         .then((res) => res.json())
//         .then((dado) => {
//             isConnected = dado
//             if (isConnected) {
//                 $("#t").append("<button id=users>" + localStorage.getItem("usuario") + "</button><br>")
//             }
//         })
// }





$(function () {
	$("form").on("submit", (e) => e.preventDefault());
	$("#disconnect").click(() => disconnect());
	$("#send").click(() => sendMessage());
	$("#cadastro").click(() => handleCadastro());
	$("#login").click(() => handleLogin());
	$("document").ready(() => connect())
	$("#livechat").ready(() => loadedMessages())
	$("#send").click(() => sendMessage())
});
