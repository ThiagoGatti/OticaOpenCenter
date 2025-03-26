package com.oticaopencenter.controlador.venda;

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

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaUsecase vendaService;

    public VendaController(VendaUsecase vendaService) {
        this.vendaService = vendaService;
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
                "",              // armacao
                BigDecimal.ZERO  // total
        ));
        return "vendas/form";
    }
    @GetMapping("/{id}")
    public String getVendaDetails(@PathVariable Long id, Model model) {
        VendaModel venda = vendaService.getVendaById(id); // Você precisará implementar este método no usecase
        model.addAttribute("venda", venda);
        return "vendas/detalhes";
    }
    @PostMapping
    public String createVenda(@ModelAttribute("vendaForm") VendaForm form) {
        VendaDto dto = new VendaDto(
                LocalDate.now(), // Data venda automática
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
                form.getArmacao(),
                form.getTotal() != null ? form.getTotal() : BigDecimal.ZERO
        );

        vendaService.createVenda(dto);
        return "redirect:/vendas";
    }
}