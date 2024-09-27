package com.oticaopencenter.controlador.produtos.converter;

import com.oticaopencenter.controlador.produtos.dtos.ProdutosDTO;
import com.oticaopencenter.controlador.produtos.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutosConverter {

    public Produto dtoToEntity(ProdutosDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setModelo(dto.getModelo());
        produto.setCusto(dto.getCusto());
        produto.setTipo(dto.getTipo());
        produto.setPrecoVenda(dto.getPrecoVenda());
        produto.setTotal(dto.getTotal());

        if (dto.getNotaFiscal() != null) {
            produto.setNotaFiscal(dto.getNotaFiscal());
        }
        if (dto.getImposto() != 0) {
            produto.setImposto(dto.getImposto());
        }

        return produto;
    }

    public ProdutosDTO entityToDto(Produto produto) {
        ProdutosDTO dto = new ProdutosDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setModelo(produto.getModelo());
        dto.setCusto(produto.getCusto());
        dto.setTipo(produto.getTipo());
        dto.setPrecoVenda(produto.getPrecoVenda());
        dto.setTotal(produto.getTotal());

        if (produto.getNotaFiscal() != null) {
            dto.setNotaFiscal(produto.getNotaFiscal());
        }
        if (produto.getImposto() != 0) {
            dto.setImposto(produto.getImposto());
        }

        return dto;
    }
}