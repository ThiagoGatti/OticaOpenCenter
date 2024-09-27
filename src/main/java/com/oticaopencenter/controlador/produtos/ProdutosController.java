package com.oticaopencenter.controlador.produtos;

import com.oticaopencenter.controlador.produtos.dtos.ProdutosDTO;
import com.oticaopencenter.controlador.produtos.repository.ProdutosRepository;
import com.oticaopencenter.controlador.produtos.entity.Produto;
import com.oticaopencenter.controlador.produtos.usecases.ProdutosUseCase;
import com.oticaopencenter.controlador.produtos.converter.ProdutosConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ProdutosUseCase produtosUseCase;
    @Autowired
    private ProdutosConverter produtosConverter;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutosDTO> cadastrarProduto(@RequestBody ProdutosDTO produtoDTO) {
        float total = produtosUseCase.calculoPrecoTotal(produtoDTO.getCusto(), produtoDTO.getImposto());
        produtoDTO.setTotal(total);

        Produto produto = produtosConverter.dtoToEntity(produtoDTO);

        Produto produtoSalvo = produtosRepository.save(produto);

        ProdutosDTO produtoSalvoDTO = produtosConverter.entityToDto(produtoSalvo);

        return new ResponseEntity<>(produtoSalvoDTO, HttpStatus.CREATED);
    }

}
