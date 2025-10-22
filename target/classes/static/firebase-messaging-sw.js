importScripts("https://www.gstatic.com/firebasejs/9.23.0/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/9.23.0/firebase-messaging-compat.js");
// Mesmo config do app.js
firebase.initializeApp({
	apiKey: "AIzaSyBcRyd7KPNzebG-KDkqOgW2XHub6OTndWo",
	authDomain: "livechat-ce9c4.firebaseapp.com",
	projectId: "livechat-ce9c4",
	storageBucket: "livechat-ce9c4.firebasestorage.app",
	messagingSenderId: "646790522951",
	appId: "1:646790522951:web:da157fffedde43d759e962"
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
