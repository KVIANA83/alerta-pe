package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.OcorrenciasDTO;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.service.OcorrenciasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
@AllArgsConstructor
public class OcorrenciasController {

    private final OcorrenciasService ocorrenciasService;

    @PostMapping("/registrar")
    public ResponseEntity<OcorrenciasDTO> registrarOcorrencia(@RequestParam String email,
                                                                @RequestParam String descricao) {
        OcorrenciasDTO ocorrenciaDTO = ocorrenciasService.registrarOcorrencia(email, descricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaDTO);
    }

    @GetMapping("/listarPorUsuario")
    public ResponseEntity<List<OcorrenciasDTO>> listarOcorrenciasPorUsuario(@RequestParam String email) {
        List<OcorrenciasDTO> listaOcorrenciasDTO = ocorrenciasService.listarOcorrenciasPorUsuario(email);
        return ResponseEntity.ok(listaOcorrenciasDTO);
    }

    @GetMapping("/listarPorAdmin")
    public ResponseEntity<List<Ocorrencias>> listarOcorrenciasPorAdmin(@RequestParam String loginAdmin) {
        // Supondo que o login do administrador seja usado para listar as ocorrências
        List<Ocorrencias> listaOcorrencias = ocorrenciasService.listarOcorrenciasPorAdmin(loginAdmin);
        return ResponseEntity.ok(listaOcorrencias);
    }

    @GetMapping("/listarTodas")
    public ResponseEntity<List<Ocorrencias>> listarTodasOcorrencias() {
        List<Ocorrencias> listaOcorrencias = ocorrenciasService.listarOcorrencias();
        return ResponseEntity.ok(listaOcorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencias> pegarOcorrencia(@PathVariable Long id) {
        Ocorrencias ocorrencia = ocorrenciasService.pegarOcorrencia(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Ocorrencias> atualizarOcorrencia(@Valid @RequestBody Ocorrencias ocorrencias) {
        Ocorrencias ocorrenciaAtualizada = ocorrenciasService.atualizarOcorrencia(ocorrencias);
        return ResponseEntity.ok(ocorrenciaAtualizada);
    }

    @PostMapping("/tratar")
    public ResponseEntity<String> tratarOcorrencia(@Valid @RequestBody TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        try {
            ocorrenciasService.tratarOcorrencia(tratarOcorrenciasDTO);
            return ResponseEntity.ok("Ocorrência tratada com sucesso!");

        } catch (ValidacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
