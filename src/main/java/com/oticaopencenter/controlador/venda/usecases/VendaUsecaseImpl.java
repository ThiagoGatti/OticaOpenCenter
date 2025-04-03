package com.oticaopencenter.controlador.venda.usecases;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import com.oticaopencenter.controlador.armacao.dtos.ArmacaoDto;
import com.oticaopencenter.controlador.armacao.usecases.ArmacaoUsecase;
import com.oticaopencenter.controlador.venda.domain.VendaModel;
import com.oticaopencenter.controlador.venda.dtos.VendaDto;
import com.oticaopencenter.controlador.venda.repository.VendaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class VendaUsecaseImpl implements VendaUsecase {

    private final VendaRepository vendaRepository;
    private final ArmacaoUsecase armacaoUsecase;

    public VendaUsecaseImpl(VendaRepository vendaRepository, ArmacaoUsecase armacaoUsecase) {
        this.vendaRepository = vendaRepository;
        this.armacaoUsecase = armacaoUsecase;
    }

    @Override
    public Page<VendaModel> findAllVendas(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);

        if (search == null || search.isEmpty()) {
            return vendaRepository.findAll(pageable);
        }

        try {
            LocalDate data = LocalDate.parse(search);
            return vendaRepository.findByDataVenda(data, pageable);
        } catch (DateTimeParseException e) {
            return vendaRepository.findByNomeCompradorContainingOrCelularContaining(
                    search,
                    search,
                    pageable
            );
        }
    }
    @Override
    public VendaModel getVendaById(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }
    @Override
    public VendaModel createVenda(VendaDto vendaDto) {
        if (vendaDto.armacaoId() != null) {
            ArmacaoModel armacao = armacaoUsecase.findById(vendaDto.armacaoId());

            if (armacao.getQuantidade() <= 0) {
                throw new IllegalStateException("Estoque insuficiente");
            }

            ArmacaoDto updateDto = new ArmacaoDto(
                    armacao.getMarca(),
                    armacao.getNotaFiscal(),
                    armacao.getReferencia(),
                    armacao.getCusto(),
                    armacao.getPrecoVenda(),
                    armacao.getQuantidade() - 1,
                    armacao.getCodigo()
            );

            armacaoUsecase.updateArmacao(armacao.getId(), updateDto);
        }

        VendaModel venda = new VendaModel();
        venda.setDataVenda(vendaDto.dataVenda());
        venda.setDataEntrega(vendaDto.dataEntrega());
        venda.setNomeComprador(vendaDto.nomeComprador());
        venda.setCelular(vendaDto.celular());
        venda.setLente(vendaDto.lente());
        venda.setOdEsferico(vendaDto.odEsferico() != null ? vendaDto.odEsferico() : BigDecimal.ZERO);
        venda.setOdCilindrico(vendaDto.odCilindrico() != null ? vendaDto.odCilindrico() : BigDecimal.ZERO);
        venda.setOeEsferico(vendaDto.oeEsferico() != null ? vendaDto.oeEsferico() : BigDecimal.ZERO);
        venda.setOeCilindrico(vendaDto.oeCilindrico() != null ? vendaDto.oeCilindrico() : BigDecimal.ZERO);
        venda.setDp(vendaDto.dp() != null ? vendaDto.dp() : BigDecimal.ZERO);
        venda.setAdicao(vendaDto.adicao() != null ? vendaDto.adicao() : BigDecimal.ZERO);
        venda.setArmacaoId(vendaDto.armacaoId());
        venda.setTotal(vendaDto.total() != null ? vendaDto.total() : BigDecimal.ZERO);

        return vendaRepository.save(venda);
    }
    @Override
    public VendaModel updateVenda(Long id, VendaDto vendaDto) {
        VendaModel venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setDataEntrega(vendaDto.dataEntrega());
        venda.setNomeComprador(vendaDto.nomeComprador());
        venda.setCelular(vendaDto.celular());
        venda.setLente(vendaDto.lente());
        venda.setOdEsferico(vendaDto.odEsferico());
        venda.setOdCilindrico(vendaDto.odCilindrico());
        venda.setOeEsferico(vendaDto.oeEsferico());
        venda.setOeCilindrico(vendaDto.oeCilindrico());
        venda.setDp(vendaDto.dp());
        venda.setAdicao(vendaDto.adicao());
        venda.setArmacaoId(vendaDto.armacaoId());
        venda.setTotal(vendaDto.total());

        return vendaRepository.save(venda);
    }

    @Override
    public void deleteVenda(Long id) {
        vendaRepository.deleteById(id);
    }
}