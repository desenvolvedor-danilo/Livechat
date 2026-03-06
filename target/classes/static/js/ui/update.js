export const updateDom = (element, attribute) => {
  const el = document.getElementById(element);
  el.append(`<div>${attribute}</div>`)
}
