package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.service.AdministradorService;
import com.pi.DefesaCivil.service.LoginService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.net.http.HttpClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/login")
public class LoginAdminController {

    private final LoginService service;

    @PostMapping
    public ResponseEntity<Administrador> login(@Valid @RequestBody LoginAdminDTO loginAdminDTO) {

        var admin = service.validaLoginAdmin(loginAdminDTO);
    
        return ResponseEntity.ok().body(admin);
    }
}
