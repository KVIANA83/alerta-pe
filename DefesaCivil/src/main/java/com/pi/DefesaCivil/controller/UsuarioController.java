package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.CreateUsuarioDTO;
import com.pi.DefesaCivil.dto.OcorrenciasDTO;
import com.pi.DefesaCivil.dto.UsuarioDTO;
import lombok.AllArgsConstructor;

import com.pi.DefesaCivil.service.OcorrenciasService;
import com.pi.DefesaCivil.service.UsuarioService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final OcorrenciasService ocorrenciasService;


    //Endpoint para registrar um novo usuário
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody @Valid CreateUsuarioDTO createUsuarioDTO) {
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(createUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    //Endpoint para solicitar uma ocorrência
    @PostMapping("/{email}/ocorrencias")
    public ResponseEntity<OcorrenciasDTO> solicitarOcorrencia(@PathVariable("email") String email, @RequestBody String descricaoOcorrencia) {
        OcorrenciasDTO ocorrenciaSalva = ocorrenciasService.registrarOcorrencia(email, descricaoOcorrencia);
        //Para solicitar uma ocorrência
        return ResponseEntity.ok(ocorrenciaSalva);
    }

    //Endpoint para acompanhar o andamento das ocorrências
    @GetMapping("/{email}/ocorrencias")
    public ResponseEntity<List<OcorrenciasDTO>> acompanharOcorrencias(@PathVariable("email") String email) {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.listarOcorrenciasPorUsuario(email);
        //Para recuperar e retornar as ocorrências
        return ResponseEntity.ok(ocorrencias);
    }
    
}