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
    private BigDecimal adicao;
    private String armacao;
    private BigDecimal total;

    // Getters e Setters para todos os campos
    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public String getNomeComprador() { return nomeComprador; }
    public void setNomeComprador(String nomeComprador) { this.nomeComprador = nomeComprador; }

    // Repetir para todos os outros campos...
}