import { getData } from "../callbacks/fetch.js"
import { showPhotoProfile } from "../ui/profileComponent.js"
import { storage } from "../utils/storage.js"

export const searchPhotoProfile = async () => {
  const photo = await getData("/users/get-photo-profile", "email", storage.get("email"))
  console.log(photo.url)
  showPhotoProfile(photo.url)
} 
