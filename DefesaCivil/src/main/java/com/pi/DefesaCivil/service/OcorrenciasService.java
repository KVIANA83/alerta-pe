package com.pi.DefesaCivil.service;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pi.DefesaCivil.dto.CreateOcorrenciaDTO;
import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.resposta.OcorrenciasDTO;
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


    public OcorrenciasDTO registrarOcorrencia(CreateOcorrenciaDTO createOcorrenciaDTO) {
        Usuario user = pegarUsuario(createOcorrenciaDTO.getEmail());
        
        var ocorrencia = Ocorrencias.builder()
                            .codigo(UUID.randomUUID().toString())
                            .descricao(createOcorrenciaDTO.getDescricao())
                            .dataAbertura(LocalDateTime.now())
                            .contato(user.getTelefone())
                            .status(StatusEnum.CRIADO.name())
                            .solicitante(user)
                            .build();
        
        var ocorrenciaSalva = ocorrenciasRepository.save(ocorrencia);

        return OcorrenciasDTO.builder()
                    .codigo(ocorrenciaSalva.getCodigo())
                    .descricao(ocorrenciaSalva.getDescricao())
                    .dataAbertura(ocorrenciaSalva.getDataAbertura())
                    .contato(ocorrenciaSalva.getContato())
                    .status(ocorrenciaSalva.getStatus())
                    .nomeSolicitante(ocorrenciaSalva.getSolicitante().getNome())
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
                                        .codigo(ocorrencia.getCodigo())
                                        .descricao(ocorrencia.getDescricao())
                                        .dataAbertura(ocorrencia.getDataAbertura())
                                        .contato(ocorrencia.getContato())
                                        .status(ocorrencia.getStatus())
                                        .nomeSolicitante(ocorrencia.getSolicitante().getNome())
                                        .dataFechamento(ocorrencia.getDataFechamento() == null ? null : ocorrencia.getDataFechamento())
                                        .loginAdministrador(ocorrencia.getAdministrador() == null ? null : ocorrencia.getAdministrador().getLogin())
                                        .observacao(ocorrencia.getObservacao()  == null ? null : ocorrencia.getObservacao())
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

    public List<OcorrenciasDTO> listarOcorrenciasLivres() {
        var result = ocorrenciasRepository.findAllSemAdministrador();

        if (result.isEmpty()) {
            return new ArrayList<>();
        }

        var listaDeOcorrencias = new ArrayList<OcorrenciasDTO>();

        for (Ocorrencias ocorrencia : result.get()) {

            listaDeOcorrencias.add(OcorrenciasDTO.builder()
                                        .codigo(ocorrencia.getCodigo())
                                        .descricao(ocorrencia.getDescricao())
                                        .dataAbertura(ocorrencia.getDataAbertura())
                                        .contato(ocorrencia.getContato())
                                        .status(ocorrencia.getStatus())
                                        .nomeSolicitante(ocorrencia.getSolicitante().getNome())
                                        .dataFechamento(ocorrencia.getDataFechamento() == null ? null : ocorrencia.getDataFechamento())
                                        .loginAdministrador(ocorrencia.getAdministrador() == null ? null : ocorrencia.getAdministrador().getLogin())
                                        .observacao(ocorrencia.getObservacao()  == null ? null : ocorrencia.getObservacao())
                                        .build());
        }

        return listaDeOcorrencias;
    }

    public OcorrenciasDTO pegarOcorrencia(Long id) {
        var ocorrOpt = ocorrenciasRepository.findById(id);

        if(ocorrOpt.isEmpty()) {
            throw new ValidacaoException("Ocorrência não encontrada");
        }

        var ocorr = ocorrOpt.get();

        return OcorrenciasDTO.builder()
                    .codigo(ocorr.getCodigo())
                    .descricao(ocorr.getDescricao())
                    .dataAbertura(ocorr.getDataAbertura())
                    .contato(ocorr.getContato())
                    .status(ocorr.getStatus())
                    .nomeSolicitante(ocorr.getSolicitante().getNome())
                    .dataFechamento(ocorr.getDataFechamento() == null ? null : ocorr.getDataFechamento())
                    .loginAdministrador(ocorr.getAdministrador() == null ? null : ocorr.getAdministrador().getLogin())
                    .observacao(ocorr.getObservacao()  == null ? null : ocorr.getObservacao())
                    .build();
    }

    public Ocorrencias pegarOcorrenciaPeloCodigo(String codOcorrencia) {
        var ocorrOpt = ocorrenciasRepository.findByCodigo(codOcorrencia);

        if(ocorrOpt.isEmpty()) {
            throw new ValidacaoException(
                String.format("Ocorrência de código %s não encontrada!", codOcorrencia));
        }

        return ocorrOpt.get();
    }

    public Ocorrencias salvarOcorrencia(Ocorrencias ocorrencias) {
        return ocorrenciasRepository.save(ocorrencias);
    }

    public List<OcorrenciasDTO> listarTodasOcorrencias() {
        var result = ocorrenciasRepository.findAll();
        
        if (result.isEmpty()) {
            return new ArrayList<>();
        }
        
        var listaDeOcorrencias = new ArrayList<OcorrenciasDTO>();
        
        for (Ocorrencias ocorrencia : result) {
            
            listaDeOcorrencias.add(OcorrenciasDTO.builder()
            .codigo(ocorrencia.getCodigo())
            .descricao(ocorrencia.getDescricao())
            .dataAbertura(ocorrencia.getDataAbertura())
            .contato(ocorrencia.getContato())
            .status(ocorrencia.getStatus())
            .nomeSolicitante(ocorrencia.getSolicitante().getNome())
            .dataFechamento(ocorrencia.getDataFechamento() == null ? null : ocorrencia.getDataFechamento())
            .loginAdministrador(ocorrencia.getAdministrador() == null ? null : ocorrencia.getAdministrador().getLogin())
            .observacao(ocorrencia.getObservacao()  == null ? null : ocorrencia.getObservacao())
            .build());
        }
        
        return listaDeOcorrencias;
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
