package com.oticaopencenter.controlador.armacao.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_ARMACAO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "referencia"),
        @UniqueConstraint(columnNames = "codigo")})
@Getter
@Setter
public class ArmacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(name = "nota_fiscal")
    private String notaFiscal;

    @Column(unique = true, nullable = false)
    private String referencia;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal custo;

    @Column(unique = true, nullable = false, length = 20)
    private String codigo;

    @NotNull(message = "Preço de venda é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deve ser maior que 0")
    private BigDecimal precoVenda;

    private Integer quantidade;
}