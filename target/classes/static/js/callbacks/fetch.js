
export const postData = (target, body) => {
  fetch(target, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify(body)
  }).then((res) => console.log(res.status))
}
export const getData = async (target, nameParam = "", valueParam = "") => {
  let url = ""
  if (nameParam && valueParam) {
    url += `?${nameParam}=${valueParam}`
    console.log(url)
  }
  const res = await fetch(`${target}${url}`, {
    // headers: { Authorization: headers }
    credentials: "include"
  })
  return parseResponse(res)
}
const parseResponse = async (res) => {
  const contentType = res.headers.get("content-type")
  if (contentType?.includes("application/json")) {
    return res.json()
  } else {
    return res.text()
  }

}
