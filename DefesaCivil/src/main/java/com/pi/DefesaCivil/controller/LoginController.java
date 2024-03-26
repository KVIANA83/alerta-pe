package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.service.UsuarioService;
import com.pi.DefesaCivil.service.EmpreendedorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService usuarioService;
    private final EmpreendedorService empreendedorService;

    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String retorno;

        if (usuarioService.preLoginUsuario(loginDTO.getEmail())) {
            retorno = usuarioService.logarUsuario(loginDTO).toString();
        } else if (empreendedorService.preLoginEmpreendedor(loginDTO.getEmail())) {
            retorno = empreendedorService.logarEmpreendedor(loginDTO).toString();
        } else {
            retorno = "Credenciais não encontradas. Deseja realizar cadastro?";
        }

        return ResponseEntity.ok(retorno);
    }
}
