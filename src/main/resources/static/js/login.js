document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    const response = await fetch("/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    const message = await response.text();

    if (message.trim() === "Login bem-sucedido!") {
        window.location.href = "/users";
    } else {
        document.getElementById("mensagem").textContent = message;
        document.getElementById("mensagem").style.color = "red";
    }
});
