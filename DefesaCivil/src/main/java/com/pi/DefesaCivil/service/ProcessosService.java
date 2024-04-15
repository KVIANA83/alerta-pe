package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.repository.ProcessosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessosService {

    private final ProcessosRepository processosRepository;

    /* 
    // Método para processar ocorrências não tratadas e gerar processos
    public void processarOcorrencias() {
        List<Ocorrencias> ocorrenciasNaoTratadas = ocorrenciasRepository.findOcorrenciasNaoTratadas();
        for (Ocorrencias ocorrencia : ocorrenciasNaoTratadas) {
            if (ocorrencia.getDescricao().contains("gravidade")) {
                Processos processo = new Processos();
                processo.setDescricao("Processo gerado para ocorrência não tratada");
                processo.setDataAbertura(LocalDate.now());
                // Outras atribuições de processo, se necessário
                processosRepository.save(processo);
            }
        }
    }*/

    // Método para buscar todos os processos
    public List<Processos> buscarTodosProcessos() {
        return processosRepository.findAll();
    }

    // Método para buscar um processo pelo ID
    public Optional<Processos> buscarProcessoPorId(Long idProcesso) {
        return processosRepository.findById(idProcesso);
    }

    // Método para atualizar um processo (apenas administradores podem fazer isso)
    //public Processos atualizarProcesso(Long idProcesso, Processos processoAtualizado) {
        // Lógica para verificar se o usuário autenticado é um administrador
        // Se for um administrador, atualize o processo e salve no banco de dados
        // Caso contrário, lançar uma exceção de autorização
    //}
}
