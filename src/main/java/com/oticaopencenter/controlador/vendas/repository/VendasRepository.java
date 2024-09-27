package com.oticaopencenter.controlador.vendas.repository;
import com.oticaopencenter.controlador.vendas.dto.VendasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<VendasDTO, Long> {

}
