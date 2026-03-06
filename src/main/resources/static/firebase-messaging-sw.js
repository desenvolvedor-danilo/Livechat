import { firebaseConfig } from "./js/config/firebase";

importScripts("https://www.gstatic.com/firebasejs/9.23.0/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/9.23.0/firebase-messaging-compat.js");
// Mesmo config do app.js
firebase.initializeApp({
	firebaseConfig
});
const messaging = firebase.messaging();

// Quando chegar push em segundo plano
messaging.onBackgroundMessage(payload => {

	console.log("[SW] Push recebido:", payload);
	const notificationTitle = payload.notification.title;
	const notificationOptions = {
		body: payload.notification.body,
		icon: "/chat-icon.png"
	};

	self.registration.showNotification(notificationTitle, notificationOptions);

});

