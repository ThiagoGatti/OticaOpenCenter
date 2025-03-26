document.addEventListener("DOMContentLoaded", function() {
    if (typeof window.jQuery === 'undefined') {
        console.error("jQuery não está carregado!");
        return;
    }

    if (typeof $.fn.mask === 'undefined') {
        console.error("jQuery Mask não está carregado!");
        return;
    }

    $(document).ready(function() {
        $('#numeroCelular').mask('(00) 0000-00009', {
            onKeyPress: function(val, e, field, options) {
                field.mask(val.length > 14 ? '(00) 00000-0000' : '(00) 0000-00009', options);
            },
            placeholder: '(  ) _____-____',
            clearIfNotMatch: true
        });
    });
});