import { header } from "../utils/headers.js";
import { setSelectFiles } from "./messageService.js.bak";


let arquivo = null;
//let selectFiles = false;

export const inputFiles = (sendFiles) => {
  sendFiles.click();
  sendFiles.addEventListener("change", (e) => {
    //selectFiles = true
    setSelectFiles(true);
    //  console.log(selectFiles)
    arquivo = e.target.files[0];
  });
};

export const uploadFile = async () => {
  const formdata = new FormData();
  formdata.append("file", arquivo);
  const res = await fetch("/files/save", {
    method: "POST",
    body: formdata,
    headers: header()
  });
  const uri = await res.json();
  return uri;
};
export const input = document.getElementById("sendFiles");
