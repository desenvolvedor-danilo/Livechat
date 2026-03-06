export const showFile = (urlFile) => {
  const sent = document.createElement("div")
  sent.classList.add("msg-sent")
  const img = document.createElement("img")
  img.src = urlFile
  img.style.maxWidth = "100%"
  img.style.height = "auto"
  sent.appendChild(img)
  return sent
}
export const showMessage = (user, message, createdAt) => {
  const received = document.createElement("div")
  const userReceived = document.createElement("div")
  const createAt = document.createElement("div")
  const msg = document.createElement("div")

  received.classList.add("msg-received")

  userReceived.classList.add("username-received")
  userReceived.textContent = "~" + user

  msg.classList.add("text-received")
  msg.textContent = message

  createAt.classList.add("timestamp-received")
  createAt.textContent = createdAt


  received.appendChild(userReceived)
  received.appendChild(msg)
  received.appendChild(createAt)

  return received
}
