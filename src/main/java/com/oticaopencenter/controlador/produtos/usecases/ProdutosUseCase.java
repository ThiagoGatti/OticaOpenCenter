package com.oticaopencenter.controlador.produtos.usecases;
import com.oticaopencenter.controlador.produtos.dtos.ProdutosDTO;

public class ProdutosUseCase {

    public float calculoPrecoTotal(float custo, float imposto) {

         ProdutosDTO produto = new ProdutosDTO();
         produto.setCusto(custo);
         produto.setImposto(imposto);

         float total = custo + imposto;

         produto.setTotal(total);

         return total;
    }
}
