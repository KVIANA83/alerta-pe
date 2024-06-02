package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.resposta.OcorrenciasDTO;
import com.pi.DefesaCivil.dto.resposta.UsuarioDTO;
import com.pi.DefesaCivil.exceptions.BodyMensagem;
import com.pi.DefesaCivil.service.OcorrenciasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Alerta-PE Ocorrencias Controller API")
@RestController
@RequestMapping("/ocorrencias")
@AllArgsConstructor
public class OcorrenciasController {

    private final OcorrenciasService ocorrenciasService;

    @Operation(
        description = "Endpoint para listar todas as ocorrências",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            }
    )
    @GetMapping("/listar-todas")
    public ResponseEntity<List<OcorrenciasDTO>> listarTodasOcorrencias() {
        List<OcorrenciasDTO> listaOcorrencias = ocorrenciasService.listarTodasOcorrencias();
        return ResponseEntity.ok(listaOcorrencias);
    }


    @Operation(
        description = "Endpoint para encontrar ocorrência pelo seu ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "id", 
                        description = "ID da ocorrência", 
                        example = "123")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciasDTO> pegarOcorrencia(@PathVariable("id") Long id) {
        OcorrenciasDTO ocorrencia = ocorrenciasService.pegarOcorrencia(id);
        return ResponseEntity.ok(ocorrencia);
    }


    @Operation(
        description = "Endpoint para listar todas as ocorrências sem administrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            }
    )
    @GetMapping("/listar-ocorrencias-sem-adm")
    public ResponseEntity<List<OcorrenciasDTO>> listarOcorrenciasSemAdministrador() {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.listarOcorrenciasLivres();
        return ResponseEntity.ok(ocorrencias);
    }

}
