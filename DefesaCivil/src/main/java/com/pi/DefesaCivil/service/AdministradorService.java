package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.repository.AdministradorRepository;
import com.pi.DefesaCivil.repository.OcorrenciasRepository;
import com.pi.DefesaCivil.repository.ProcessosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministradorService {

    private final OcorrenciasRepository ocorrenciasRepository;
    private final ProcessosRepository processosRepository;
    private final AdministradorRepository administradorRepository;

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

    // Método para buscar ocorrências
    public List<Ocorrencias> buscarOcorrencias() {
        return ocorrenciasRepository.findAll();
    }

    // Método para buscar processos
    public List<Processos> buscarProcessos() {
        return processosRepository.findAll();
    }

    public Optional<Administrador> getAdmin(String login) {
        return administradorRepository.findByLogin(login);
    }
}
