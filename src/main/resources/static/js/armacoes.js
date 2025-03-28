let currentArmacaoId = null;

function openAddDialog(id) {
    currentArmacaoId = id;
    document.getElementById('addStockModal').style.display = 'block';
}

function closeAddDialog() {
    document.getElementById('addStockModal').style.display = 'none';
    document.getElementById('addQuantity').value = '';
}

function submitAddStock() {
    const quantity = parseInt(document.getElementById('addQuantity').value);

    if (quantity > 0) {
        fetch(`/armacoes/${currentArmacaoId}/adicionar-estoque?quantidade=${quantity}`, {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').content
            }
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert("Erro ao adicionar estoque!");
            }
        }).catch(error => {
            console.error('Erro:', error);
        });
    } else {
        alert("Quantidade inválida!");
    }
    closeAddDialog();
}

window.onclick = function(event) {
    const modal = document.getElementById('addStockModal');
    if (event.target === modal) {
        closeAddDialog();
    }
};