import { setLastSender } from "./messageService.js"
import { isDuplicate } from "../utils/helpers.js"
import { showFile, showMessage } from "../ui/messageComponent.js"


export const privateChat = (message) => {
  if (isDuplicate(message)) return
  const chat = document.getElementById("chat")
  //  notifications()
  message.urlFile ?
    chat.appendChild(showFile(message.urlFile))
    :
    chat.appendChild(showMessage(message.user, message.message, message.createdAt))
  setLastSender(message.from)
}

