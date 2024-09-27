package com.oticaopencenter.controlador.produtos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome; // 8415, justin
    private String modelo; // rayban, Rayban
    private float custo;

    private float total;
    private float precoVenda;

    //solar //armação //infantil
    private String tipo;

    //nao obrigatorio
    private String notaFiscal;
    private float imposto;



}
