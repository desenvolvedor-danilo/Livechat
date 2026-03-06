import { getMessaging, getToken, onMessage }
  from "https://www.gstatic.com/firebasejs/10.12.0/firebase-messaging.js";
import app from "./initFirebase.js";
import { postData } from "../callbacks/fetch.js";
import { header } from "../utils/headers.js";
import { storage } from "../utils/storage.js";


//firebase.initializeApp(firebaseConfig);
// const registration = await navigator.serviceWorker.register("/firebase-messaging-sw.js")
const messaging = getMessaging(app);
export async function requestPermission() {
  const permission = await Notification.requestPermission()
  if (permission === "granted") {
    const token = await getToken(messaging, {
      vapidKey: "BA00hc2JI1NUNqmWsqctZp1H3n8lp2I9_4UqDna77-2E9iCWBqmBfhbqLf9YI7bDnvzaItCx69FDm9jfndJ3hxI"
      // serviceWorkerRegistration: registration
    })

    postData("/users/save-token", header().Authorization, { email: storage.get("email"), token: token })
  }
}
export function listenMessages(callback) {
  onMessage(messaging, (payload) => {
    callback(payload)
  })
}
