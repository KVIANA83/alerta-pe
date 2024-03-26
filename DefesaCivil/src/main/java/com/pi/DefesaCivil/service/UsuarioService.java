package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.CreateUsuarioDTO;
import com.pi.DefesaCivil.dto.UsuarioDTO;
import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO registrarNovoUsuario(UsuarioDTO usuarioDTO) {
        //Mapear UsuarioDTO para Usuario
        Usuario novoUsuario = Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .telefone(usuarioDTO.getTelefone())
                .build();

        //Salvar o novo usuário no banco de dados
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        //Mapear Usuario para UsuarioDTO e retornar
        return UsuarioDTO.builder()
                .nome(usuarioSalvo.getNome())
                .email(usuarioSalvo.getEmail())
                .telefone(usuarioSalvo.getTelefone())
                .build();
    }
}