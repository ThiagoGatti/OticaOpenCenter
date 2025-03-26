package com.oticaopencenter.controlador.security;

import com.oticaopencenter.controlador.security.dtos.AdminDto;
import com.oticaopencenter.controlador.security.usecases.AdminUsecase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AdminController {
    private final AdminUsecase useCase;

    public AdminController(AdminUsecase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public String login(@RequestBody AdminDto dto) {
        return useCase.authenticate(dto)
                ? "Login bem-sucedido!"
                : "Falha na autenticação. Verifique suas credenciais.";
    }
}