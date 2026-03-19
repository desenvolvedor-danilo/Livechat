import { getData } from "../callbacks/fetch.js"
import { showPhotoProfile } from "../ui/profileComponent.js"
import { header } from "../utils/headers.js"
import { storage } from "../utils/storage.js"

export const searchPhotoProfile = async () => {
  const photo = await getData("/users/get-photo-profile", header().Authorization, "email", storage.get("email"))
  console.log(photo.url)
  showPhotoProfile(photo.url)
} 
