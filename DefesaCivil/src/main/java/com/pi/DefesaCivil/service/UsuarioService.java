package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.CreateUsuarioDTO;
import com.pi.DefesaCivil.dto.resposta.UsuarioDTO;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    // Injeta o PasswordEnconder 
    private final PasswordEncoder passwordEncoder;

    
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public UsuarioDTO registrarUsuario(CreateUsuarioDTO createUsuarioDTO) {
        //Mapear UsuarioDTO para Usuario
        Usuario novoUsuario = Usuario.builder()
                .nome(createUsuarioDTO.getNome())
                .email(createUsuarioDTO.getEmail())
                .senha(createUsuarioDTO.getSenha()) // Senha ainda está em texto plano
                .telefone(createUsuarioDTO.getTelefone())
                .dataNascimento(createUsuarioDTO.getDataNascimento())
                .endereco(createUsuarioDTO.getEndereco())
                .build();

        // Criptografar a senha antes de salvar
        novoUsuario.setSenha(passwordEncoder.encode(createUsuarioDTO.getSenha())); // Utilizar passwordEncoder.encode()
        
        //Salvar o novo usuário no banco de dados
        var entity = usuarioRepository.save(novoUsuario);

        //Mapear Usuario para UsuarioDTO e retornar
        return UsuarioDTO.builder()
                .nome(entity.getNome())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }

    public Usuario findByEmail(String email) {
        var userOpt = usuarioRepository.findByEmail(email);

        if(userOpt.isEmpty()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        return userOpt.get();
    }
}