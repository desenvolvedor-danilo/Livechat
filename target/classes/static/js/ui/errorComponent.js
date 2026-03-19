export const error = (error, idElement) => {
  const elementError = "error"
  if (!document.getElementById(elementError)) {
    const errorContainer = document.createElement("section")
    errorContainer.id = "error"
    errorContainer.textContent = error
    document.getElementById(idElement).appendChild(errorContainer)
  }
} 
