package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.service.AdministradorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/login")
public class LoginAdminController {

    private final AdministradorService administradorService;

    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginAdminDTO loginAdminDTO) {
        String retorno;

        if (administradorService.authenticate(loginAdminDTO.getUsername(), loginAdminDTO.getPassword())) {
            retorno = " ";
        } else {
            retorno = "Credenciais de administrador inválidas";
        }

        return ResponseEntity.ok(retorno);
    }
}
