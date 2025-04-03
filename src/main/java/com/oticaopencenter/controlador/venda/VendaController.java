package com.oticaopencenter.controlador.venda;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import com.oticaopencenter.controlador.armacao.usecases.ArmacaoUsecase;
import com.oticaopencenter.controlador.venda.domain.VendaModel;
import com.oticaopencenter.controlador.venda.dtos.VendaDto;
import com.oticaopencenter.controlador.venda.dtos.VendaForm;
import com.oticaopencenter.controlador.venda.usecases.VendaUsecase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaUsecase vendaService;
    private final ArmacaoUsecase armacaoUsecase;

    public VendaController(VendaUsecase vendaService, ArmacaoUsecase armacaoUsecase) {
        this.vendaService = vendaService;
        this.armacaoUsecase = armacaoUsecase;
    }


    @GetMapping
    public String listVendas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            Model model) {

        Page<VendaModel> vendasPage = vendaService.findAllVendas(page, size, search);

        model.addAttribute("vendas", vendasPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vendasPage.getTotalPages());
        model.addAttribute("searchTerm", search);
        model.addAttribute("size", size);

        return "vendas/list";
    }

    @GetMapping("/nova")
    public String newVendaForm(Model model) {
        List<ArmacaoModel> armacoesDisponiveis = armacaoUsecase.findAvailableArmacoes(); // Você precisará criar este método
        model.addAttribute("armacoesDisponiveis", armacoesDisponiveis);
        model.addAttribute("venda", new VendaDto(
                LocalDate.now(), // dataVenda
                null,            // dataEntrega
                "",              // nomeComprador
                "",              // celular
                "",              // lente
                BigDecimal.ZERO, // odEsferico
                BigDecimal.ZERO, // odCilindrico
                BigDecimal.ZERO, // oeEsferico
                BigDecimal.ZERO, // oeCilindrico
                BigDecimal.ZERO, // dp
                BigDecimal.ZERO, // adicao
                null,            // armacao
                BigDecimal.ZERO  // total
        ));
        return "vendas/form";
    }
    @GetMapping("/{id}")
    public String getVendaDetails(@PathVariable Long id, Model model) {

        VendaModel venda = vendaService.getVendaById(id);
        ArmacaoModel armacao = null;
        if (venda.getArmacaoId() != null) {
            armacao = armacaoUsecase.findById(venda.getArmacaoId());
        }
        model.addAttribute("venda", venda);
        model.addAttribute("armacao", armacao);
        return "vendas/detalhes";
    }
    @PostMapping
    public String createVenda(@ModelAttribute("vendaForm") VendaForm form) {
        VendaDto dto = new VendaDto(
                LocalDate.now(),
                form.getDataEntrega(),
                form.getNomeComprador(),
                form.getCelular(),
                form.getLente(),
                form.getOdEsferico() != null ? form.getOdEsferico() : BigDecimal.ZERO,
                form.getOdCilindrico() != null ? form.getOdCilindrico() : BigDecimal.ZERO,
                form.getOeEsferico() != null ? form.getOeEsferico() : BigDecimal.ZERO,
                form.getOeCilindrico() != null ? form.getOeCilindrico() : BigDecimal.ZERO,
                form.getDp() != null ? form.getDp() : BigDecimal.ZERO,
                form.getAdicao() != null ? form.getAdicao() : BigDecimal.ZERO,
                form.getArmacaoId(),
                form.getTotal() != null ? form.getTotal() : BigDecimal.ZERO
        );

        vendaService.createVenda(dto);
        return "redirect:/vendas";
    }

    @GetMapping("/excluir/{id}")
    public String deleteVenda(@PathVariable Long id) {
        vendaService.deleteVenda(id);
        return "redirect:/vendas";
    }

}