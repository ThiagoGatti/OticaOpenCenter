package com.oticaopencenter.controlador.venda.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class VendaForm {
    private LocalDate dataEntrega;
    private String nomeComprador;
    private String celular;
    private String lente;
    private BigDecimal odEsferico;
    private BigDecimal odCilindrico;
    private BigDecimal oeEsferico;
    private BigDecimal oeCilindrico;
    private BigDecimal dp;
    private Long armacaoId;
    private BigDecimal adicao;
    private BigDecimal total;

}