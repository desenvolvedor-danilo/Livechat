import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.0/firebase-app.js";
import { firebaseConfig } from "../config/firebase.js";




// export function connectFirebase(config) {
//
//   firebase.initializeApp(config);
//   const messaging = firebase.messaging();
//   Notification.requestPermission().then((permission) => {
//     if (permission === "granted") {
//       messaging.getToken({ vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI" })
//         .then((currentToken) => {
//           if (currentToken) {
//             console.log("Token de notificação:", currentToken); // Aqui você envia o token para seu backend (Spring)          
//             fetch("/users/save-token",
//               {
//                 method: "POST", headers: { "Content-Type": "application/json" },
//                 body: JSON.stringify({ email: currentEmail, token: currentToken })
//               })
//           }
//         })
//     }
//   })
const app = initializeApp(firebaseConfig);
export default app
