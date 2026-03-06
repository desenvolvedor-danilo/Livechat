import { storage } from "../utils/storage.js";

export const handleCadastro = () => {
  fetch("/users/create", {
    headers: { "Content-Type": "application/json; charset=UTF-8" },
    method: "POST",
    body: JSON.stringify({
      email: $("#email").val(),
      password: $("#password").val(),
      username: $("#usuario").val(),
      nome: $("#name").val()
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
        console.log(res.json())
      }
      return res.json();
    })
    .then((dado) => {
      console.log(dado)
      storage.set("email", $("#correio").val());
      storage.set("authorization", dado.token);
      storage.set("usuario", dado.nome);
      storage.set("logado", true);
      window.location.href = "/notifications";
    });
};

export const logout = () => {
  localStorage.clear();
  location.href = "/login";
};
