package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.CreateOcorrenciasDTO;
import com.pi.DefesaCivil.dto.OcorrenciasDTO;
import com.pi.DefesaCivil.service.OcorrenciasService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ocorrencias")
public class OcorrenciasController {
    
    private final OcorrenciasService ocorrenciasService;
    
    // Endpoint para criar uma nova ocorrência (apenas usuários)
    @PostMapping
    public ResponseEntity<String> criarOcorrencia(@Valid @RequestBody CreateOcorrenciasDTO createOcorrenciasDTO) {
        ocorrenciasService.criarOcorrencia(createOcorrenciasDTO);
        return ResponseEntity.ok("Ocorrência criada com sucesso");
    }
    
    // Endpoint para buscar todas as ocorrências
    @GetMapping
    public ResponseEntity<List<OcorrenciasDTO>> buscarTodasOcorrencias() {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.buscarTodasOcorrencias();
        return ResponseEntity.ok(ocorrencias);
    }
    
    // Endpoint para buscar uma ocorrência pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciasDTO> buscarOcorrenciaPorId(@PathVariable Long id) {
        OcorrenciasDTO ocorrencia = ocorrenciasService.buscarOcorrenciaPorId(id);
        return ResponseEntity.ok(ocorrencia);
    }
    
    // Endpoint para buscar ocorrências por data de abertura (para usuários e administradores)
    @GetMapping("/data-abertura/{data}")
    public ResponseEntity<List<OcorrenciasDTO>> buscarOcorrenciasPorDataAbertura(@PathVariable LocalDate data) {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.buscarOcorrenciasPorDataAbertura(data);
        return ResponseEntity.ok(ocorrencias);
    }
    
    // Endpoint para buscar ocorrências por data de fechamento (apenas para administradores)
    @GetMapping("/data-fechamento/{data}")
    public ResponseEntity<List<OcorrenciasDTO>> buscarOcorrenciasPorDataFechamento(@PathVariable LocalDate data) {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.buscarOcorrenciasPorDataFechamento(data);
        return ResponseEntity.ok(ocorrencias);
    }
    
            }
