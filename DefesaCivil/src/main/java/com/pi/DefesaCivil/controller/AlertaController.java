package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.AlertaDTO;
import com.pi.DefesaCivil.service.AlertaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerta")
@Api(value = "Alerta API", tags = "Alerta API")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/listar")
    @ApiOperation(value = "Lista todos os alertas", response = AlertaDTO.class, responseContainer = "List")
    public ResponseEntity<List<AlertaDTO>> listarAlertas() {
        List<AlertaDTO> alertas = alertaService.listarAlertas();
        return ResponseEntity.ok(alertas);
    }

    @PostMapping("/adicionar")
    @ApiOperation(value = "Adiciona um alerta")
    public ResponseEntity<AlertaDTO> adicionarAlerta(@RequestBody AlertaDTO alertaDTO) {
        AlertaDTO novoAlerta = alertaService.adicionarAlerta(alertaDTO);
        return ResponseEntity.ok(novoAlerta);
    }

    @DeleteMapping("/remover/{id}")
    @ApiOperation(value = "Remove um alerta por ID")
    public ResponseEntity<Void> removerAlerta(@PathVariable Long id) {
        alertaService.removerAlerta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    @ApiOperation(value = "Atualiza um alerta")
    public ResponseEntity<AlertaDTO> atualizarAlerta(@RequestBody AlertaDTO alertaDTO) {
        AlertaDTO alertaAtualizado = alertaService.atualizarAlerta(alertaDTO);
        return ResponseEntity.ok(alertaAtualizado);
    }
}
