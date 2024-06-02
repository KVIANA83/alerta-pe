package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.service.LoginService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    @PostMapping("/admin")
    public ResponseEntity<Administrador> login(@RequestBody LoginAdminDTO loginAdminDTO) {
        loginAdminDTO.validarCampos();
        log.info("Iniciando login de Administrador: {}", loginAdminDTO.getLogin());
        var admin = service.validaLoginAdmin(loginAdminDTO);
    
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> login(@RequestBody LoginDTO loginDTO) {
        loginDTO.validarCampos();
        log.info("Iniciando login de Administrador: {}", loginDTO.getLogin());
        var user = service.validaLoginUsuario(loginDTO);

        return ResponseEntity.ok(user);
    }
}
