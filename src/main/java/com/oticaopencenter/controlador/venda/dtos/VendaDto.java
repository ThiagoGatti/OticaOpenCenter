package com.oticaopencenter.controlador.venda.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaDto(
        LocalDate dataVenda,
        LocalDate dataEntrega,
        String nomeComprador,
        String celular,
        String lente,
        BigDecimal odEsferico,
        BigDecimal odCilindrico,
        BigDecimal oeEsferico,
        BigDecimal oeCilindrico,
        BigDecimal dp,
        BigDecimal adicao,
        Long armacaoId,
        BigDecimal total
) {
    public VendaDto() {
        this(
                LocalDate.now(),
                null,
                "",
                "",
                "",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                null,
                BigDecimal.ZERO
        );
    }
}