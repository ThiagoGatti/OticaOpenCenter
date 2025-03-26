package com.oticaopencenter.controlador.venda.repository;

import com.oticaopencenter.controlador.venda.domain.VendaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface VendaRepository extends JpaRepository<VendaModel, Long> {

    Page<VendaModel> findByDataVenda(LocalDate dataVenda, Pageable pageable);

    Page<VendaModel> findByNomeCompradorContainingOrCelularContaining(
            String nomeComprador,
            String celular,
            Pageable pageable
    );
}