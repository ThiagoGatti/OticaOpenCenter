package com.oticaopencenter.controlador.produtos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutosDTO {

    private Long id; //
    private String nome; // 8415, justin
    private String modelo; // rayban, Rayban
    private float custo;

    //solar //armação //infantil
    private String tipo;

    private float total;
    private float precoVenda;
    //nao obrigatorio
    private String notaFiscal;
    private float imposto;

}
