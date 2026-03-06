export const showAllUsers = (userEmail, userImg, userName) => {
  const a = document.createElement('a')
  const img = document.createElement('img')
  const div = document.createElement('div')
  const span = document.createElement('span')
  a.href = `private.html?user=${userEmail}`
  a.classList.add("contact-item")
  a.classList.add("online-contact")
  img.src = userImg ? userImg : "/icons/avatar.png"
  img.classList.add("contact-avatar")
  div.classList.add("contact-info")
  span.textContent = userName
  span.classList.add("contact-name")
  div.appendChild(span)
  a.appendChild(img)
  a.appendChild(div)
  return a
}
