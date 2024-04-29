package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.repository.ProcessosRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            .status(StatusEnum.CRIADO)
            .contato(ocorrencias.getContato())
            .ocorrencia(ocorrencias)
            .build();

        return processosRepository.save(processo);
    }

    // Método para buscar todos os processos
    public List<Processos> buscarTodosProcessos() {
        return processosRepository.findAll();
    }

    // Método para buscar um processo pelo ID
    public Optional<Processos> buscarProcessoPorId(Long idProcesso) {
        return processosRepository.findById(idProcesso);
    }

    // Método para atualizar um processo (apenas administradores podem fazer isso)
    public Processos atualizarProcesso(Long idProcesso, String status) {
        var processoOpt = processosRepository.findById(idProcesso);

        if (processoOpt.isEmpty()) {
            throw new ValidacaoException("Processo não encontrado!");
        }

        var statusEncontrado = StatusEnum.pegarEnumPeloStatus(status);

        var processo = processoOpt.get();
        processo.setStatus(statusEncontrado);

        if ((statusEncontrado.equals(StatusEnum.RESOLVIDO)) || (statusEncontrado.equals(StatusEnum.CANCELADO))) {
            processo.setDataFechamento(LocalDateTime.now());
        }
        
        return processosRepository.save(processo);
    }
}
