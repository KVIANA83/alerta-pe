package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.service.LoginService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    @PostMapping("/admin")
    public ResponseEntity<Administrador> login(@Valid @RequestBody LoginAdminDTO loginAdminDTO) {

        var admin = service.validaLoginAdmin(loginAdminDTO);
    
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginDTO loginDTO) {

        var user = service.validaLoginUsuario(loginDTO);

        return ResponseEntity.ok(user);
    }
}
