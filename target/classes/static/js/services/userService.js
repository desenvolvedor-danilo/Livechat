export const findAllUsers = (header) => {
  fetch("/users/findall", { method: "GET", headers: header })
    .then((res) => res.json())
    .then((users) => {
      users.map((user) => {
        if (user.email != localStorage.getItem("email")) {
          $("#contactList").append(`
            <a href="private.html?user=${user.email}" class="contact-item online-contact">
              <div class="contact-avatar"><img src="${user.url}"></div>
              <div class="contact-info">
                <span class="contact-name">${user.nome}</span>
              </div>
            </a>`);
        }
      });
    });
};
