package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.AtribuirOcorrenciaDTO;
import com.pi.DefesaCivil.dto.AtribuirProcessoDTO;
import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.dto.TratarProcessosDTO;
import com.pi.DefesaCivil.exceptions.BodyMensagem;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.service.AdministradorService;
import com.pi.DefesaCivil.service.ProcessosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Alerta-PE Administrador Controller API")
@RestController
@AllArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {
    
    private final AdministradorService administradorService;
    private final ProcessosService processosService;
    

    @Operation(
        description = "Endpoint para atribuir uma ocorrencia ao adminstrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "codOcorrencia", 
                        description = "Parametros necessários para realizar a requisição", 
                        example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2"),
            @Parameter(name = "loginAdmin", 
                        description = "Parametros necessários para realizar a requisição", 
                        example = "A1234")
        }
    )
    @PostMapping("/atribuir-ocorrencias")
    public ResponseEntity<String> atribuirOcorrenciasParaAdmin(@RequestBody AtribuirOcorrenciaDTO atribuirOcorrenciaDTO) {
        atribuirOcorrenciaDTO.validarCampos();
        administradorService.atribuirOcorrencia(atribuirOcorrenciaDTO);
        return ResponseEntity.ok("Ocorrencia atribuída com sucesso!");
    }
    
    @Operation(
        description = "Endpoint para atualizar o status da ocorrencia (vira processo ou não)",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "idOcorrencia", 
                        description = "Id da ocorrência registrada", 
                        example = "123"),
            @Parameter(name = "loginAdmin", 
                        description = "Login do Administrador cadastrado", 
                        example = "A12345"),
            @Parameter(name = "descricao", 
                        description = "Descrição da tratativa da ocorrência", 
                        example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2"),
            @Parameter(name = "status", 
                        description = "Status de atualização da ocorrência", 
                        example = "RESOLVIDO")
                    }
    )
    @PutMapping("/tratar-ocorrencias")
    public ResponseEntity<String> tratarOcorrencias(@RequestBody TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        tratarOcorrenciasDTO.validarCampos();
        var ocorrencia = administradorService.tratarOcorrencia(tratarOcorrenciasDTO);

        if (ocorrencia.getStatus().equals(StatusEnum.CRIAR_PROCESSO.name())){
            //todo: chamar serevice de processo e crair um processo.
            var processoCriado = processosService.criarProcessos(ocorrencia);
            var mensagem = String.format("Ocorrencia virou Processo de código %s", processoCriado.getCodigo());
            return ResponseEntity.ok(mensagem);
        }
        return ResponseEntity.ok("Ocorrencia atualizada com sucesso!");
    }
    
    @Operation(
        description = "Endpoint para listar todas as ocorrências do administrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ocorrencias.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "loginAdmin", description = "Login do Administrador cadastrado", example = "A12345")
        }
    )
    @GetMapping("/{loginAdmin}/ocorrencias")
    public ResponseEntity<List<Ocorrencias>> acompanharOcorrencias(@PathVariable("loginAdmin") String loginAdmin) {
        List<Ocorrencias> ocorrencias = administradorService.listarOcorrenciasPorAdmin(loginAdmin);
        //Para recuperar e retornar as ocorrências
        return ResponseEntity.ok(ocorrencias);
    }

    @Operation(
        description = "Endpoint para listar todos os processos",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Processos.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            }
    )
    @GetMapping("/processos")
    public ResponseEntity<List<Processos>> listarProcessos() {
        List<Processos> processos = processosService.buscarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    @Operation(
        description = "Endpoint para atribuir um processo ao adminstrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "codProcesso", 
                        description = "Códico único do processo", 
                        example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2"),
            @Parameter(name = "loginAdmin", 
                        description = "Login do Administrador cadastrado", 
                        example = "A1234")
        }
    )
    @PostMapping("/atribuir-processos")
    public ResponseEntity<String> atribuirProcessosParaAdmin(@RequestBody AtribuirProcessoDTO atribuirProcessoDTO) {
        atribuirProcessoDTO.validarCampos();
        administradorService.atribuirProcesso(atribuirProcessoDTO);
        return ResponseEntity.ok("Ocorrencia atribuída com sucesso!");
    }

    @Operation(
        description = "Endpoint para listar processos do administrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Processos.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "loginAdmin", description = "Login do Administrador cadastrado", example = "A12345")
        }
    )
    @GetMapping("/{loginAdmin}/processos")
    public ResponseEntity<List<Processos>> listarProcessosDoAdmin(@PathVariable("loginAdmin") String loginAdmin) {
        List<Processos> processos = administradorService.listarProcessosPorAdmin(loginAdmin);
        return ResponseEntity.ok(processos);
    }

    @Operation(
        description = "Endpoint para atualizar um processo",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "idProcesso", 
                        description = "Id do processo registrado", 
                        example = "12345"),
            @Parameter(name = "loginAdmin", 
                        description = "Login do Administrador cadastrado", 
                        example = "A1234"),
            @Parameter(name = "descricao", 
                        description = "Descrição da tratativa do Processo", 
                        example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2"),
            @Parameter(name = "status", 
                        description = "Status de atualização da ocorrência", 
                        example = "RESOLVIDO")
        }
    )
    @PutMapping("/atualizar-processo")
    public ResponseEntity<String> atualizarProcesso(@RequestBody TratarProcessosDTO tratarProcessosDTO) {
        tratarProcessosDTO.validarCampos();
        var mensagemFinal = processosService.atualizarProcesso(tratarProcessosDTO);
        return ResponseEntity.ok(mensagemFinal);
    }
}
