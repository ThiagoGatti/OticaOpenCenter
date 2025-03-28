package com.oticaopencenter.controlador.armacao.dtos;

import java.math.BigDecimal;

public record ArmacaoDto(
        String marca,
        String notaFiscal,
        String referencia,
        BigDecimal custo,
        BigDecimal precoVenda,
        Integer quantidade,
        String codigo
) {
    public ArmacaoDto() {
        this("", "", "", BigDecimal.ZERO, BigDecimal.ZERO, 0, "");
    }
}