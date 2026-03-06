import { privateChat } from "../../services/domUpdate.js";
import { isDuplicate } from "../../utils/helpers.js";

export function handlePrivate(message) {
  if (!isDuplicate(message)) {
    privateChat(message)
  }
}
