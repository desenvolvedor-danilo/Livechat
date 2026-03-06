export const header = () => ({ Authorization: "Basic " + localStorage.getItem("authorization"), ContentType: "application/json" })
