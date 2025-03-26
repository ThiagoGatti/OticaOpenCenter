
package com.oticaopencenter.controlador.security.dtos;

public record AdminFormDto(
        String nome,
        String email,
        String senha,
        String confirmacaoSenha
) {}