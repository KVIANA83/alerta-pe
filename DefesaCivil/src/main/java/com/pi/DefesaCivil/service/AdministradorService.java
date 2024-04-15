package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.repository.AdministradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministradorService {

    private final OcorrenciasService ocorrenciasService;
    private final AdministradorRepository administradorRepository;

    /* 
    // Método para validar ou tratar ocorrências e gerar processos
    public void processarOcorrencias() {
        List<Ocorrencias> ocorrenciasNaoTratadas = ocorrenciasRepository.findOcorrenciasNaoTratadas();
        for (Ocorrencias ocorrencia : ocorrenciasNaoTratadas) {
            // Lógica para validar ou tratar a ocorrência e gerar processo, se necessário
            // Exemplo:
            if (ocorrencia.getDescricao().contains("gravidade")) {
                Processos processo = new Processos();
                processo.setDescricao("Processo gerado para ocorrência não tratada");
                // Outras atribuições de processo, se necessário
                processosRepository.save(processo);
            }
        }
    }
    */

    public Optional<Administrador> getAdmin(String login) {
        return administradorRepository.findByLogin(login);
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

    public Ocorrencias atualizarOcorrencia(Long idOcorrencia, String login, String status) {
        var adminOpt = getAdmin(login);
        
        if(adminOpt.isEmpty()) {
            throw new ValidacaoException("Administrador não encontrado.");
        }

        var admin = adminOpt.get();
        var ocorrencia = ocorrenciasService.pegarOcorrencia(idOcorrencia);

        if (ocorrencia.getAdministrador().getLogin() == null) {
            throw new ValidacaoException("Ocorrência não pode ser atualizada porque ainda não está em andamento");
        }
        if (!ocorrencia.getAdministrador().getLogin().equals(admin.getLogin())) {
            throw new ValidacaoException("Ocorrência pertence a outro Administrador");
        }

        switch(status) {
            case "CRIAR PROCESSO":
                ocorrencia.setStatus(StatusEnum.CRIAR_PROCESSO);
                break;
            case "RESOLVIDO":
                ocorrencia.setStatus(StatusEnum.RESOLVIDO);
                break;
            case "CANCELADO":
                ocorrencia.setStatus(StatusEnum.CANCELADO);
                break;
            default:
                throw new ValidacaoException("Status inválido");
        }

        return ocorrenciasService.atualizarOcorrencia(ocorrencia);
    } 
}
