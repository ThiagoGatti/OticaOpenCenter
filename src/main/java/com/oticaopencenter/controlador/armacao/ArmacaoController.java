package com.oticaopencenter.controlador.armacao;

import com.oticaopencenter.controlador.armacao.domain.ArmacaoModel;
import com.oticaopencenter.controlador.armacao.dtos.ArmacaoDto;
import com.oticaopencenter.controlador.armacao.dtos.ArmacaoForm;
import com.oticaopencenter.controlador.armacao.usecases.ArmacaoUsecase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/armacoes")
public class ArmacaoController {

    private final ArmacaoUsecase armacaoService;
    private final int DEFAULT_PAGE_SIZE = 10;

    public ArmacaoController(ArmacaoUsecase armacaoService) {
        this.armacaoService = armacaoService;
    }

    @GetMapping
    public String listArmacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long editStockId,
            Model model) {

        Page<ArmacaoModel> armacoesPage = armacaoService.findAllArmacoes(page, size, search);

        model.addAttribute("armacoes", armacoesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", armacoesPage.getTotalPages());
        model.addAttribute("searchTerm", search);
        model.addAttribute("size", size);
        model.addAttribute("editStockId", editStockId);

        return "armacoes/list";
    }

    @GetMapping("/nova")
    public String newArmacaoForm(Model model) {
        model.addAttribute("armacaoForm", new ArmacaoForm());
        return "armacoes/form";
    }

    @GetMapping("/editar/{id}")
    public String editArmacaoForm(@PathVariable Long id, Model model) {
        ArmacaoModel armacao = armacaoService.findById(id);
        ArmacaoForm form = new ArmacaoForm();
        form.setId(armacao.getId());
        form.setMarca(armacao.getMarca());
        form.setNotaFiscal(armacao.getNotaFiscal());
        form.setReferencia(armacao.getReferencia());
        form.setCusto(armacao.getCusto());
        form.setPrecoVenda(armacao.getPrecoVenda());
        form.setQuantidade(armacao.getQuantidade());
        model.addAttribute("armacaoForm", form);
        return "armacoes/form";
    }
    private ArmacaoForm convertToForm(ArmacaoModel armacao) {
        ArmacaoForm form = new ArmacaoForm();
        form.setId(armacao.getId());
        form.setMarca(armacao.getMarca());
        form.setNotaFiscal(armacao.getNotaFiscal());
        form.setReferencia(armacao.getReferencia());
        form.setCusto(armacao.getCusto());
        form.setPrecoVenda(armacao.getPrecoVenda());
        form.setQuantidade(armacao.getQuantidade());
        return form;
    }

    private ArmacaoDto convertToDto(ArmacaoForm form) {
        return new ArmacaoDto(
                form.getMarca(),
                form.getNotaFiscal(),
                form.getReferencia(),
                form.getCusto(),
                form.getPrecoVenda(),
                form.getQuantidade(),
                form.getCodigo() // Novo campo
        );
    }
    @PostMapping
    public String saveArmacao(@ModelAttribute("armacaoForm") @Valid ArmacaoForm form,
                              BindingResult result, Model model) {


        if (form.getId() == null && armacaoService.existsByCodigo(form.getCodigo())) {
            result.rejectValue("codigo", "duplicate.codigo", "Código já está em uso");
        }

        if (form.getId() != null) {
            ArmacaoModel existing = armacaoService.findById(form.getId());
            if (!existing.getCodigo().equals(form.getCodigo()) &&
                    armacaoService.existsByCodigo(form.getCodigo())) {
                result.rejectValue("codigo", "duplicate.codigo", "Código já está em uso");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("armacaoForm", form);
            return "armacoes/form";
        }

        ArmacaoDto dto = new ArmacaoDto(
                form.getMarca(),
                form.getNotaFiscal(),
                form.getReferencia(),
                form.getCusto(),
                form.getPrecoVenda(),
                form.getQuantidade(),
                form.getCodigo()
        );

        if (form.getId() != null) {
            armacaoService.updateArmacao(form.getId(), dto);
        } else {
            armacaoService.createArmacao(dto);
        }

        return "redirect:/armacoes";
    }
    @PostMapping("/{id}/adicionar-estoque")
    public String adicionarEstoque(
            @PathVariable Long id,
            @RequestParam("quantidade") int quantidade) {

        armacaoService.adicionarEstoque(id, quantidade);
        return "redirect:/armacoes";
    }
    @GetMapping("/excluir/{id}")
    public String deleteArmacao(@PathVariable Long id) {
        armacaoService.deleteArmacao(id);
        return "redirect:/armacoes";
    }
}