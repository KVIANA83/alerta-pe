package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.CreateProcessosDTO;
import com.pi.DefesaCivil.dto.ProcessosDTO;
import com.pi.DefesaCivil.service.ProcessosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/processos")
public class ProcessosController {
    
    private final ProcessosService processosService;
    
    // Endpoint para usuários visualizarem todos os processos
    @GetMapping
    public ResponseEntity<List<ProcessosDTO>> visualizarTodosProcessos() {
        List<ProcessosDTO> processos = processosService.visualizarTodosProcessos();
        return ResponseEntity.ok(processos);
    }
    
    // Endpoint para usuários visualizarem o histórico de processos
    @GetMapping("/historico")
    public ResponseEntity<List<ProcessosDTO>> visualizarHistoricoProcessos() {
        List<ProcessosDTO> historico = processosService.visualizarHistoricoProcessos();
        return ResponseEntity.ok(historico);
    }
    
    // Endpoint para administradores solicitarem novos processos
    @PostMapping
    public ResponseEntity<String> solicitarNovoProcesso(@Valid @RequestBody CreateProcessosDTO createProcessosDTO) {
        processosService.solicitarNovoProcesso(createProcessosDTO);
        return ResponseEntity.ok("Novo processo solicitado com sucesso");
    }
    
    // Endpoint para administradores validarem processos
    @PostMapping("/validar/{idProcesso}")
    public ResponseEntity<String> validarProcesso(@PathVariable Long idProcesso) {
        processosService.validarProcesso(idProcesso);
        return ResponseEntity.ok("Processo validado com sucesso");
    }
    
    // Endpoint para administradores consultarem processos pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProcessosDTO> consultarProcessoPorId(@PathVariable Long id) {
        ProcessosDTO processo = processosService.consultarProcessoPorId(id);
        return ResponseEntity.ok(processo);
    }
    
    // Endpoint para administradores consultarem processos pela data de abertura
    @GetMapping("/data-abertura/{data}")
    public ResponseEntity<List<ProcessosDTO>> consultarProcessosPorDataAbertura(@PathVariable LocalDate data) {
        List<ProcessosDTO> processos = processosService.consultarProcessosPorDataAbertura(data);
        return ResponseEntity.ok(processos);
    }
    
    // Endpoint para administradores consultarem processos pela data de fechamento
    @GetMapping("/data-fechamento/{data}")
    public ResponseEntity<List<ProcessosDTO>> consultarProcessosPorDataFechamento(@PathVariable LocalDate data) {
        List<ProcessosDTO> processos = processosService.consultarProcessosPorDataFechamento(data);
        return ResponseEntity.ok(processos);
    }
    
    // Endpoint para administradores consultarem processos por ID de ocorrência não tratada
    @GetMapping("/ocorrencias-nao-tratadas/{idOcorrencia}")
    public ResponseEntity<List<ProcessosDTO>> consultarProcessosPorOcorrenciasNaoTratadas(@PathVariable Long idOcorrencia) {
        List<ProcessosDTO> processos = processosService.consultarProcessosPorOcorrenciasNaoTratadas(idOcorrencia);
        return ResponseEntity.ok(processos);
    }
    
    // Outros endpoints para atualizar e excluir processos, se necessário
}
