package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.AtribuirOcorrenciaDTO;
import com.pi.DefesaCivil.dto.AtribuirProcessoDTO;
import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.dto.resposta.OcorrenciasDTO;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.repository.AdministradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdministradorService {

    private final OcorrenciasService ocorrenciasService;
    private final AdministradorRepository administradorRepository;
    private final ProcessosService processosService;


    public Administrador getAdmin(String login) {
        var adminOpt = administradorRepository.findByLogin(login);

        if(adminOpt.isEmpty()) {
            throw new ValidacaoException("Administrador não encontrado.");
        }
        return adminOpt.get();
    }

    public List<OcorrenciasDTO> listarOcorrenciasLivres() {
        return ocorrenciasService.listarOcorrenciasLivres();
    }

    public void atribuirOcorrencia(AtribuirOcorrenciaDTO atribuirOcorrenciaDTO) {
        var admin = getAdmin(atribuirOcorrenciaDTO.getLoginAdmin());

        var ocorr = ocorrenciasService.pegarOcorrenciaPeloCodigo(atribuirOcorrenciaDTO.getCodOcorrencia());
        ocorr.setAdministrador(admin);
        ocorr.setStatus(StatusEnum.EM_ANDAMENTO.name());

        ocorrenciasService.salvarOcorrencia(ocorr);
    }

    public void atribuirProcesso(AtribuirProcessoDTO atribuirProcessoDTO) {
        var admin = getAdmin(atribuirProcessoDTO.getLoginAdmin());

        var proc = processosService.buscarProcessoPorCodigo(atribuirProcessoDTO.getCodProcesso());
        proc.setAdministrador(admin);
        proc.setStatus(StatusEnum.EM_ANDAMENTO.name());

        processosService.salvarProcesso(proc);
    }

    public Ocorrencias tratarOcorrencia(TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        
        var statusEncontrado = StatusEnum.pegarEnumPeloStatus(tratarOcorrenciasDTO.getStatus()).name();
        var ocorrencia = ocorrenciasService.pegarOcorrenciaPeloCodigo(tratarOcorrenciasDTO.getIdOcorrencia());
        var admin = getAdmin(tratarOcorrenciasDTO.getLoginAdmin());

        if (ocorrencia.getAdministrador().getLogin() == null) {
            throw new ValidacaoException("Ocorrência não pode ser atualizada porque ainda não está em andamento");
        }
        if (!ocorrencia.getAdministrador().getLogin().equals(admin.getLogin())) {
            throw new ValidacaoException("Ocorrência pertence a outro Administrador");
        }

        ocorrencia.setStatus(statusEncontrado);
        ocorrencia.setObservacao(tratarOcorrenciasDTO.getDescricao());

        if ((statusEncontrado.equals(StatusEnum.RESOLVIDO.name())) || (statusEncontrado.equals(StatusEnum.CANCELADO.name()))) {
            ocorrencia.setDataFechamento(LocalDateTime.now());
        }

        return ocorrenciasService.salvarOcorrencia(ocorrencia);
    } 

    public List<Ocorrencias> listarOcorrenciasPorAdmin(String loginAdmin) {
        var admin = getAdmin(loginAdmin);
        return ocorrenciasService.listarOcorrenciasPorAdmin(admin);
    }

    public List<Processos> listarProcessosPorAdmin(String loginAdmin) {
        var admin = getAdmin(loginAdmin);
        return processosService.listarProcessosPorAdmin(admin);
    }
}
