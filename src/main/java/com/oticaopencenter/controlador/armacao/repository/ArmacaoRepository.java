package com.oticaopencenter.controlador.armacao.repository;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmacaoRepository extends JpaRepository<ArmacaoModel, Long> {

    @Query("SELECT a FROM ArmacaoModel a WHERE " +
            "LOWER(a.marca) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.codigo) LIKE LOWER(CONCAT('%', :search, '%'))") // Adicionado codigo na busca
    Page<ArmacaoModel> search(@Param("search") String search, Pageable pageable);
    List<ArmacaoModel> findByQuantidadeGreaterThan(int quantidade);

    boolean existsByCodigo(String codigo);
}
