package com.oticaopencenter.controlador.venda.usecases;

import com.oticaopencenter.controlador.venda.domain.VendaModel;
import com.oticaopencenter.controlador.venda.dtos.VendaDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface VendaUsecase {
    Page<VendaModel> findAllVendas(int page, int size, String search);
    VendaModel createVenda(VendaDto vendaDto);
    VendaModel updateVenda(Long id, VendaDto vendaDto);
    void deleteVenda(Long id);

    VendaModel getVendaById(Long id);

}