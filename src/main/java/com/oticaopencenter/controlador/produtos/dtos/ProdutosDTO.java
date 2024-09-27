package com.oticaopencenter.controlador.produtos.dtos;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_PRODUTOS")
public class ProdutosDTO {

    private Long id; //

    private String nome; // 8415, justin

    private String modelo; // rayban, Rayban

    private String marca;

    private float custo;

    //solar //armação //infantil
    private String tipo;

    //nao obrigatorio
    private float imposto;

    private float total;
    private float precoVenda;


    //nao obrigatorio
    private String notaFiscal;

}
