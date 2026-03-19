export const showNotifications = (participant, urlImg, userName, message, sendedAt) => {
  const a = document.createElement("a")
  const img = document.createElement("img")
  const div = document.createElement("div")
  const divName = document.createElement("div")
  const spanMessage = document.createElement("span")
  const spanUpdating = document.createElement("span")
  a.href = `/private.html?user=${participant}`
  img.src = urlImg ? urlImg : "/icons/avatar.png"
  divName.textContent = userName
  spanMessage.textContent = message
  spanUpdating.textContent = sendedAt
  a.classList.add("contact-item", "online-contact")
  img.classList.add("contact-avatar")
  div.classList.add("contact-info")
  spanMessage.classList.add("contact-name")
  spanUpdating.classList.add("timestamp")
  div.append(divName, spanMessage, spanUpdating)
  a.append(img, div)
  return a
} 
