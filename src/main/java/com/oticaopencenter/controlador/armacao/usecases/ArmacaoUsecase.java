package com.oticaopencenter.controlador.armacao.usecases;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import com.oticaopencenter.controlador.armacao.dtos.ArmacaoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArmacaoUsecase {
    Page<ArmacaoModel> findAllArmacoes(int page, int size, String search);
    ArmacaoModel findById(Long id);
    ArmacaoModel createArmacao(ArmacaoDto armacaoDto);
    ArmacaoModel updateArmacao(Long id, ArmacaoDto dto);
    boolean existsByCodigo(String codigo);
    void adicionarEstoque(Long id, int quantidade);
    List<ArmacaoModel> findAvailableArmacoes();
    void deleteArmacao(Long id);
}