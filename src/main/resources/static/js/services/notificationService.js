import { header } from "../utils/headers.js";
export const notifications = () => {
  fetch("/conversas/private?from=" + localStorage.getItem("email"), {
    method: "GET",
    headers: header()
  })
    .then((res) => res.json())
    .then((data) => {
      $("#notifications").empty();
      data.forEach((msg) => {
        $("#notifications").append(`
          <div>${msg.message}</div>
        `);
      });
    });
};
