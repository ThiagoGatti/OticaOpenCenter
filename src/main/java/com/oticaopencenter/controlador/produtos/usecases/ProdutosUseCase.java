package com.oticaopencenter.controlador.produtos.usecases;

import org.springframework.stereotype.Service;

@Service
public class ProdutosUseCase {

    public float calculoPrecoTotal(float custo, float imposto) {
        return imposto > 0 ? custo + imposto : custo;
    }
}
