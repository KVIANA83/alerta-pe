package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.repository.AdministradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministradorService {

    private final OcorrenciasService ocorrenciasService;
    private final AdministradorRepository administradorRepository;


    public Administrador getAdmin(String login) {
        var adminOpt = administradorRepository.findByLogin(login);

        if(adminOpt.isEmpty()) {
            throw new ValidacaoException("Administrador não encontrado.");
        }
        return adminOpt.get();
    }

    public List<Ocorrencias> listarOcorrenciasLivres() {
        var ocorrencias = ocorrenciasService.listarOcorrencias();

        List<Ocorrencias> listaFiltrada = new ArrayList<>();

        for (Ocorrencias ocorrencia : ocorrencias) {
            if (ocorrencia.getAdministrador() == null) {
                listaFiltrada.add(ocorrencia);
            }
        }

        return listaFiltrada;
    }

    public void atribuirOcorrencia(Long idOcorrencia, String login) {
        var adminOpt = getAdmin(login);
        
        if(adminOpt.isEmpty()) {
            throw new ValidacaoException("Administrador não encontrado.");
        }

        var ocorr = ocorrenciasService.pegarOcorrencia(idOcorrencia);
        ocorr.setAdministrador(adminOpt.get());
        ocorr.setStatus(StatusEnum.EM_ANDAMENTO);

        ocorrenciasService.atualizarOcorrencia(ocorr);
    }

    // método usado para tratar ocorrências já atribuídas ao Administrador
    public Ocorrencias tratarOcorrencia(TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        
        var statusEncontrado = StatusEnum.pegarEnumPeloStatus(tratarOcorrenciasDTO.getStatus());
        var ocorrencia = ocorrenciasService.pegarOcorrencia(tratarOcorrenciasDTO.getIdOcorrencia());
        var admin = getAdmin(tratarOcorrenciasDTO.getLoginAdmin());

        if (ocorrencia.getAdministrador().getLogin() == null) {
            throw new ValidacaoException("Ocorrência não pode ser atualizada porque ainda não está em andamento");
        }
        if (!ocorrencia.getAdministrador().getLogin().equals(admin.getLogin())) {
            throw new ValidacaoException("Ocorrência pertence a outro Administrador");
        }

        ocorrencia.setStatus(statusEncontrado);
        ocorrencia.setObservacao(tratarOcorrenciasDTO.getDescricao());

        if ((statusEncontrado.equals(StatusEnum.RESOLVIDO)) || (statusEncontrado.equals(StatusEnum.CANCELADO))) {
            ocorrencia.setDataFechamento(LocalDateTime.now());
        }

        return ocorrenciasService.atualizarOcorrencia(ocorrencia);
    } 

    public List<Ocorrencias> listarOcorrenciasPorAdmin(String loginAdmin) {
        var admin = getAdmin(loginAdmin);
        return ocorrenciasService.listarOcorrenciasPorAdmin(admin);
    }
}
