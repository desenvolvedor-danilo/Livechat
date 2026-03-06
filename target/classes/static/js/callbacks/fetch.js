
export const postData = (target, headers, body) => {
  fetch(target, {
    method: "POST",
    headers: { Authorization: headers, "Content-Type": "application/json" },
    body: JSON.stringify(body)
  }).then((res) => console.log(res.status))
}
export const getData = async (target, headers, nameParam, valueParam) => {
  const res = await fetch(`${target}?${nameParam}=${valueParam}`, {
    headers: { Authorization: headers }
  })
  return res.json()
}
