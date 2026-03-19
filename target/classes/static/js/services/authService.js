import { eventListener } from "../actions/events.js";
import { error } from "../ui/errorComponent.js";
import { hideElement } from "../ui/transforms/hideElements.js";
import { storage } from "../utils/storage.js";

eventListener("blur", () => {
  const isValid = /^[a-zA-Z0-9._-]+@[a-z]+\.[a-z]+(\.[a-z]+)?$/.test($("#email").val())
  if (!isValid) {
    error("Formato de e-mail inválido", "container-email")
  } else {
    hideElement("error");
  }
}, "email")
export const handleCadastro = () => {
  fetch("/users/create", {
    headers: { "Content-Type": "application/json; charset=UTF-8" },
    method: "POST",
    body: JSON.stringify({

      nome: $("#name").val(),
      email: $("#email").val(),
      password: $("#password").val(),
      username: $("#usuario").val(),

    })
  }).then((res) => {
    if (res.status == 200) {
      window.location.href = "/login.html";
    }
  });
};

export const handleLogin = () => {
  fetch("/users/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      "email": $('#correio').val(),
      "password": $("#senha").val()
    })
  })
    .then((res) => {
      if (!res.ok) {
      }
      return res.json();
    })
    .then((dado) => {
      storage.set("email", $("#correio").val());
      storage.set("usuario", dado.nome);
      storage.set("logado", true);
      window.location.href = "/notifications";
    });
};

export const logout = () => {
  localStorage.clear();
  location.href = "/login";
};
