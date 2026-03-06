import { getData } from "../callbacks/fetch.js";
import { showAllUsers } from "../ui/findUsersComponent.js";
import { header } from "../utils/headers.js";

export const findUsers = async () => {
  const data = await getData("/users/findall", header().Authorization)
  const contactList = document.getElementById("contactList")
  data.map((user) => {
    contactList.appendChild(showAllUsers(user.email, user.url, user.nome))
  })
}
