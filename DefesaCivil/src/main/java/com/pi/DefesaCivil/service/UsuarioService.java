package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.UsuarioDTO;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;


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

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}