package com.oticaopencenter.controlador.produtos.repository;

import com.oticaopencenter.controlador.produtos.dtos.ProdutosDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutosDTO, Long> {
}
