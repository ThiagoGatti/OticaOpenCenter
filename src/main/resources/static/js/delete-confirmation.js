document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.acao-excluir').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const confirmacao = confirm('Tem certeza que deseja excluir este administrador?');
            if (confirmacao) {
                window.location.href = this.href;
            }
        });
    });
});