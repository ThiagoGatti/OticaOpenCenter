package com.oticaopencenter.controlador.armacao.usecases;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import com.oticaopencenter.controlador.armacao.dtos.ArmacaoDto;
import com.oticaopencenter.controlador.armacao.repository.ArmacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ArmacaoUsecaseImpl implements ArmacaoUsecase {

    private final ArmacaoRepository repository;

    public ArmacaoUsecaseImpl(ArmacaoRepository repository) {
        this.repository = repository;
    }
    @Override
    public ArmacaoModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armação não encontrada"));
    }
    @Override
    public Page<ArmacaoModel> findAllArmacoes(int page, int size, String search) {
        PageRequest pageable = PageRequest.of(page, size);
        if(search == null || search.isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.search(search, pageable);
    }
    @Override
    public ArmacaoModel createArmacao(ArmacaoDto dto) {
        ArmacaoModel armacao = new ArmacaoModel();
        updateEntityFromDto(armacao, dto);
        return repository.save(armacao);
    }

    @Override
    public ArmacaoModel updateArmacao(Long id, ArmacaoDto dto) {
        ArmacaoModel armacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armação não encontrada"));
        updateEntityFromDto(armacao, dto);
        return repository.save(armacao);
    }
    @Override
    public boolean existsByCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }
    @Override
    public List<ArmacaoModel> findAvailableArmacoes() {
        return repository.findByQuantidadeGreaterThan(0);
    }
    @Override
    public void adicionarEstoque(Long id, int quantidade) {
        ArmacaoModel armacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armação não encontrada"));

        armacao.setQuantidade(armacao.getQuantidade() + quantidade);
        repository.save(armacao);
    }
    private void updateEntity(ArmacaoModel armacao, ArmacaoDto dto) {
        armacao.setMarca(dto.marca());
        armacao.setNotaFiscal(dto.notaFiscal());
        armacao.setReferencia(dto.referencia());
        armacao.setCusto(dto.custo() != null ? dto.custo() : BigDecimal.ZERO);
        armacao.setPrecoVenda(dto.precoVenda() != null ? dto.precoVenda() : BigDecimal.ZERO);
        armacao.setQuantidade(dto.quantidade() != null ? dto.quantidade() : 0);
    }

    private void updateEntityFromDto(ArmacaoModel armacao, ArmacaoDto dto) {
        armacao.setMarca(dto.marca());              // String → String
        armacao.setCodigo(dto.codigo());            // String → String
        armacao.setReferencia(dto.referencia());    // String → String
        armacao.setNotaFiscal(dto.notaFiscal());    // String → String
        armacao.setCusto(dto.custo());              // BigDecimal → BigDecimal
        armacao.setPrecoVenda(dto.precoVenda());    // BigDecimal → BigDecimal
        armacao.setQuantidade(dto.quantidade());    // Integer → Integer
    }

    @Override
    public void deleteArmacao(Long id) {
        repository.deleteById(id);
    }
}