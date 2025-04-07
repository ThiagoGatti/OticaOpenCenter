package com.oticaopencenter.controlador.venda.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_VENDA")
@Getter
@Setter
public class VendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataVenda;
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
    private Long armacaoId;
    private BigDecimal total;
    private BigDecimal odEixo;
    private BigDecimal oeEixo;
    private String formaPagamento;
}