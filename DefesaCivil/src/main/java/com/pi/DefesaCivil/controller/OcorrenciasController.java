package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.resposta.OcorrenciasDTO;
import com.pi.DefesaCivil.service.OcorrenciasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
@AllArgsConstructor
public class OcorrenciasController {

    private final OcorrenciasService ocorrenciasService;

    @GetMapping("/listar-todas")
    public ResponseEntity<List<OcorrenciasDTO>> listarTodasOcorrencias() {
        List<OcorrenciasDTO> listaOcorrencias = ocorrenciasService.listarTodasOcorrencias();
        return ResponseEntity.ok(listaOcorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciasDTO> pegarOcorrencia(@PathVariable("id") Long id) {
        OcorrenciasDTO ocorrencia = ocorrenciasService.pegarOcorrencia(id);
        return ResponseEntity.ok(ocorrencia);
    }

    //Endpoint para listar todas as ocorrências sem admin
    @GetMapping("/listar-ocorrencias-sem-adm")
    public ResponseEntity<List<OcorrenciasDTO>> listarOcorrenciasSemAdministrador() {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.listarOcorrenciasLivres();
        return ResponseEntity.ok(ocorrencias);
    }

}
