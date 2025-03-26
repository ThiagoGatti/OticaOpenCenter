document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('adminForm');

    if (form) {
        form.addEventListener('submit', function(e) {
            const senha = document.getElementById('senha');
            const confirmarSenha = document.getElementById('confirmarSenha');
            const errorDiv = document.getElementById('senhaError');

            errorDiv.style.display = 'none';

            if (senha.value !== confirmarSenha.value) {
                e.preventDefault();
                errorDiv.style.display = 'block';
                errorDiv.textContent = 'As senhas não coincidem!';
                senha.focus();
                return false;
            }

            const regexSenha = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            if (!regexSenha.test(senha.value)) {
                e.preventDefault();
                errorDiv.style.display = 'block';
                errorDiv.textContent = 'A senha deve ter pelo menos 8 caracteres com letras e números!';
                return false;
            }

            return true;
        });
    }
});