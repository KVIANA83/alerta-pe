package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.AdministradorDTO;
import com.pi.DefesaCivil.model.Administrador;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {
    
    private final AdministradorService administradorService;
    
    // Endpoint para validar todas as ocorrências
    @PostMapping("/validar-ocorrencias")
    public ResponseEntity<String> validarTodasOcorrencias() {
        administradorService.validarTodasOcorrencias();
        return ResponseEntity.ok("Todas as ocorrências foram validadas com sucesso");
    }
    
    // Endpoint para abrir processos para ocorrências não tratadas
    @PostMapping("/abrir-processos")
    public ResponseEntity<String> abrirProcessosParaOcorrenciasNaoTratadas() {
        administradorService.abrirProcessosParaOcorrenciasNaoTratadas();
        return ResponseEntity.ok("Processos abertos para ocorrências não tratadas com sucesso");
    }
    
    // Endpoint para validar um processo
    @PostMapping("/validar-processo/{idProcesso}")
    public ResponseEntity<String> validarProcesso(@PathVariable Long idProcesso) {
        administradorService.validarProcesso(idProcesso);
        return ResponseEntity.ok("Processo validado com sucesso");
    }
    
    // Endpoint para buscar processos por ID de ocorrência não tratada
    @GetMapping("/ocorrencias-nao-tratadas/{idOcorrencia}")
    public ResponseEntity<List<ProcessosDTO>> buscarProcessosPorOcorrenciasNaoTratadas(@PathVariable Long idOcorrencia) {
        List<ProcessosDTO> processos = administradorService.buscarProcessosPorOcorrenciasNaoTratadas(idOcorrencia);
        return ResponseEntity.ok(processos);
    }
    
    // Endpoint para buscar processos por ID
    @GetMapping("/processos/{idProcesso}")
    public ResponseEntity<ProcessosDTO> buscarProcessoPorId(@PathVariable Long idProcesso) {
        ProcessosDTO processo = administradorService.buscarProcessoPorId(idProcesso);
        return ResponseEntity.ok(processo);
    }
    
    // Endpoint para buscar processos por data de abertura
    @GetMapping("/processos/data-abertura/{data}")
    public ResponseEntity<List<ProcessosDTO>> buscarProcessosPorDataAbertura(@PathVariable LocalDate data) {
        List<ProcessosDTO> processos = administradorService.buscarProcessosPorDataAbertura(data);
        return ResponseEntity.ok(processos);
    }
    
    // Endpoint para buscar processos por data de fechamento
    @GetMapping("/processos/data-fechamento/{data}")
    public ResponseEntity<List<ProcessosDTO>> buscarProcessosPorDataFechamento(@PathVariable LocalDate data) {
        List<ProcessosDTO> processos = administradorService.buscarProcessosPorDataFechamento(data);
        return ResponseEntity.ok(processos);
    }
    
    // Outros endpoints para atualizar e excluir processos, se necessário
}
