export const showPhotoProfile = (src) => {
  const photo = document.getElementById("imagePreview")
  if (src) {
    photo.setAttribute("src", src)
  } else {
    photo.setAttribute("src", "/icons/avatar.png")
  }
}
