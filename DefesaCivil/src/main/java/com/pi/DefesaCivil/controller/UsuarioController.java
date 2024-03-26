package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.CreateUsuarioDTO;
import com.pi.DefesaCivil.dto.UsuarioDTO;
import com.pi.DefesaCivil.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Endpoint para registrar um novo usuário
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuario = usuarioService.registrarNovoUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    //Endpoint para solicitar uma ocorrência
    @PostMapping("/{idUsuario}/ocorrencias")
    public ResponseEntity<String> solicitarOcorrencia(@PathVariable Long idUsuario, @RequestBody String descricaoOcorrencia) {
        
        //Para solicitar uma ocorrência
        return ResponseEntity.ok("Pedido solicitado com sucesso!");
    }

    //Endpoint para acompanhar o andamento das ocorrências
    @GetMapping("/{idUsuario}/ocorrencias")
    public ResponseEntity<String> acompanharOcorrencias(@PathVariable Long idUsuario) {
        
        //Para recuperar e retornar as ocorrências
        return ResponseEntity.ok("Seu histório de pedidos...");
    }
    
}