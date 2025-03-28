package com.oticaopencenter.controlador.armacao.dtos;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ArmacaoForm {
    private Long id;
    private String marca;
    private String notaFiscal;
    private String referencia;
    private BigDecimal custo;
    private BigDecimal precoVenda;
    private Integer quantidade;
    private String codigo;
}