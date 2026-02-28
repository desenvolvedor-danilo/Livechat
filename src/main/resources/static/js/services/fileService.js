let arquivo = null;
let selectFiles = false;

export const inputFiles = (sendFiles) => {
  sendFiles.click();
  sendFiles.addEventListener("change", (e) => {
    selectFiles = true;
    arquivo = e.target.files[0];
  });
};

export const uploadFile = async () => {
  const formdata = new FormData();
  formdata.append("file", arquivo);
  const res = await fetch("/files/save", {
    method: "POST",
    body: formdata
  });
  return await res.json();
};
