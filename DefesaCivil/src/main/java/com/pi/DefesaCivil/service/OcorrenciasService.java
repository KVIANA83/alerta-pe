package com.pi.DefesaCivil.service;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OcorrenciaService {

    private final UsuarioRepository usuarioRepository;
    private final OcorrenciasRepository ocorrenciasRepository;

    public void solicitarOcorrencia(Long idUsuario, @NotBlank String descricao) {
        if (idUsuario == null) {
            throw new ValidationException("ID do usuário não pode ser nulo");
        }
        
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Ocorrencias ocorrencias = new Ocorrencias();
            ocorrencias.setDescricao(descricao);
            ocorrencias.setUsuario(usuario);
            ocorrenciasRepository.save(ocorrencias);
        } else {
            throw new UsuarioNotFoundException("Usuário não encontrado com o ID: " + idUsuario);
        }
    }
}
