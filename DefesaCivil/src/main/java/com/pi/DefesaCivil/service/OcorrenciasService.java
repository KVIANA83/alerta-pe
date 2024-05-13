package com.pi.DefesaCivil.service;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pi.DefesaCivil.dto.OcorrenciasDTO;
import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.repository.OcorrenciasRepository;

@Service
@AllArgsConstructor
public class OcorrenciasService {

    private final OcorrenciasRepository ocorrenciasRepository;
    private final UsuarioService usuarioService;


    public OcorrenciasDTO registrarOcorrencia(String email, String descricao) {
        Usuario user = pegarUsuario(email);
        
        var ocorrencia = Ocorrencias.builder()
                            .descricao(descricao)
                            .dataAbertura(LocalDateTime.now())
                            .contato(user.getTelefone())
                            .status(StatusEnum.CRIADO)
                            .solicitante(user)
                            .build();
        
        var ocorrenciaSalva = ocorrenciasRepository.save(ocorrencia);

        return OcorrenciasDTO.builder()
                    .dataAbertura(ocorrenciaSalva.getDataAbertura())
                    .nomeSolicitante(ocorrenciaSalva.getSolicitante().getNome())
                    .codigo(ocorrenciaSalva.getCodigo())
                    .descricao(ocorrenciaSalva.getDescricao())
                    .contato(ocorrenciaSalva.getContato())
                    .status(ocorrenciaSalva.getStatus())
                    .build();
    }

    public List<OcorrenciasDTO> listarOcorrenciasPorUsuario(String email) {
        Usuario user = pegarUsuario(email);

        var resultOpt = ocorrenciasRepository.findBySolicitante(user);

        if(resultOpt.isEmpty()) {
            return new ArrayList<>();
        }

        var listaDeOcorrencias = new ArrayList<OcorrenciasDTO>();

        for (Ocorrencias ocorrencia : resultOpt.get()) {

            listaDeOcorrencias.add(OcorrenciasDTO.builder()
                                        .dataAbertura(ocorrencia.getDataAbertura())
                                        .nomeSolicitante(ocorrencia.getSolicitante().getNome())
                                        .codigo(ocorrencia.getCodigo())
                                        .descricao(ocorrencia.getDescricao())
                                        .contato(ocorrencia.getContato())
                                        .status(ocorrencia.getStatus())
                                        .build());
        }

        return listaDeOcorrencias;
    }

    public List<Ocorrencias> listarOcorrenciasPorAdmin(Administrador administrador) {
        
        var resultOpt = ocorrenciasRepository.findByAdministrador(administrador);

        if(resultOpt.isEmpty()) {
            return new ArrayList<>();
        }

        return resultOpt.get();
    }

    public List<Ocorrencias> listarOcorrencias() {
        var result = ocorrenciasRepository.findAll();
        return result.isEmpty() ? new ArrayList<>() : result;
    }

    public Ocorrencias pegarOcorrencia(Long id) {
        var ocorrOpt = ocorrenciasRepository.findById(id);

        if(ocorrOpt.isEmpty()) {
            throw new ValidacaoException("Ocorrência não encontrada");
        }

        return ocorrOpt.get();
    }

    public Ocorrencias atualizarOcorrencia(Ocorrencias ocorrencias) {
        return ocorrenciasRepository.save(ocorrencias);
    }

    private Usuario pegarUsuario(String email) {
        Usuario user;
        try {
            user = usuarioService.findByEmail(email);

        } catch (ValidacaoException ex) {
            throw new ValidacaoException("Usuário não encontrado. Por favor, informe seu e-mail de cadastro.");
        }
        return user;
    }

}
