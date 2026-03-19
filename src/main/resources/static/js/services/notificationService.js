import { getData } from "../callbacks/fetch.js";
import { doFilter } from "../callbacks/filter.js";
import { showNotifications } from "../ui/notificationsComponent.js";
import { header } from "../utils/headers.js";
import { storage } from "../utils/storage.js";
export const notifications = async () => {
  const currentUser = storage.get("email");
  const message = document.getElementById("notifications")
  const notifications = await getData("/conversas/private", header().Authorization, "from", currentUser)
  const lastByUser = {}
  notifications.forEach((notify) => {
    lastByUser[notify.id] = notify
  })
  Object.values(lastByUser).forEach(async (msg) => {
    const participante = doFilter(msg.participantes, currentUser)
    const user = await getData("/users/find-users", header().Authorization, "email", participante)
    message.appendChild(showNotifications(participante, user.url, user.nome, msg.message, msg.updatedAt))
  })
};
