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

        BigDecimal adicao,
        Long armacaoId,
        BigDecimal total,
        BigDecimal odEixo,
        BigDecimal oeEixo,
        String formaPagamento,
        BigDecimal dpEsquerdo,
        BigDecimal dpDireito,
        String ordemServico,
        String observacao
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
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "",
                ""
        );
    }
}
