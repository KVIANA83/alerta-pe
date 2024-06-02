package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarProcessosDTO;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.repository.ProcessosRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessosService {

    private final ProcessosRepository processosRepository;


    // Método para criar processos
    public Processos criarProcessos(Ocorrencias ocorrencias) {

        var processo = Processos.builder()
            .codigo(UUID.randomUUID().toString())
            .descricao(ocorrencias.getDescricao())
            .dataAbertura(LocalDateTime.now())
            .status(StatusEnum.CRIADO.name())
            .contato(ocorrencias.getContato())
            .ocorrencia(ocorrencias)
            .build();

        return processosRepository.save(processo);
    }

    // Método para buscar todos os processos
    public List<Processos> buscarTodosProcessos() {
        return processosRepository.findAll();
    }

    public Processos buscarProcessoPorCodigo(String codProcesso) {
        var processoOpt = processosRepository.findByCodigo(codProcesso);

        if (processoOpt.isEmpty()) {
            throw new ValidacaoException(String.format("processo de código %s não foi encontrado!",codProcesso));
        }

        return processoOpt.get();
    }

    // Método para buscar um processo pelo ID
    public Optional<Processos> buscarProcessoPorId(Long idProcesso) {
        return processosRepository.findById(idProcesso);
    }
    
    // Método para listar processos por administrador
    public List<Processos> listarProcessosPorAdmin(Administrador administrador) {
        var processoOpt = processosRepository.findAllByAdministrador(administrador);

        if (processoOpt.isEmpty()) {
            return new ArrayList<>();
        }

        return processoOpt.get();
    }

    // Método para atualizar um processo (apenas administradores podem fazer isso)
    public String atualizarProcesso(TratarProcessosDTO tratarProcessosDTO) {
        var processoOpt = processosRepository.findById(Long.valueOf(tratarProcessosDTO.getIdProcesso()));

        if (processoOpt.isEmpty()) {
            throw new ValidacaoException("Processo não encontrado!");
        }

        var processo = processoOpt.get();

        if (processo.getAdministrador().getLogin() == null) {
            throw new ValidacaoException("Processo não pode ser atualizado porque ainda não está em andamento");
        }
        if (!processo.getAdministrador().getLogin().equals(tratarProcessosDTO.getLoginAdmin())) {
            throw new ValidacaoException("Processo pertence a outro Administrador");
        }

        var statusEncontrado = StatusEnum.pegarEnumPeloStatus(tratarProcessosDTO.getStatus()).name();
        String mensagemFinal = "Processo atualizado com sucesso!";

        processo.setDescricao(tratarProcessosDTO.getDescricao());
        processo.setStatus(statusEncontrado);

        if ((statusEncontrado.equals(StatusEnum.RESOLVIDO.name())) || (statusEncontrado.equals(StatusEnum.CANCELADO.name()))) {
            processo.setDataFechamento(LocalDateTime.now());
            mensagemFinal = "Processo finalizado com sucesso!";
        }
        
        processosRepository.save(processo);

        return mensagemFinal;
    }

    public Processos salvarProcesso(Processos processo) {
        return processosRepository.save(processo);
    }
}
