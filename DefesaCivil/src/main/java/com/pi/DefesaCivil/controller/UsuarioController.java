package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.CreateOcorrenciaDTO;
import com.pi.DefesaCivil.dto.CreateUsuarioDTO;
import com.pi.DefesaCivil.dto.resposta.OcorrenciasDTO;
import com.pi.DefesaCivil.dto.resposta.UsuarioDTO;
import com.pi.DefesaCivil.exceptions.BodyMensagem;
import com.pi.DefesaCivil.model.Ocorrencias;

import lombok.AllArgsConstructor;

import com.pi.DefesaCivil.service.OcorrenciasService;
import com.pi.DefesaCivil.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Alerta-PE Usuario Controller API")
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final OcorrenciasService ocorrenciasService;


    @Operation(
        description = "Endpoint para registrar um novo usuário",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "nome", 
                        description = "Nome do usuário cadastrado", 
                        example = "Maria João da Silva"),
            @Parameter(name = "senha", 
                        description = "Senha do usuário cadastrado", 
                        example = "SeNHA.123"),
            @Parameter(name = "email", 
                        description = "E-mail do usuário cadastrado", 
                        example = "maria.joao@email.com"),
            @Parameter(name = "telefone", 
                        description = "Telefone do usuário cadastrado", 
                        example = "81988776655"),
            @Parameter(name = "dataNascimento", 
                        description = "Data de nascimento do usuário cadastrado", 
                        example = "2000-01-01"),
            @Parameter(name = "endereco", 
                        description = "Endereço do usuário cadastrado", 
                        example = "Rua rua, s/n, Bairro bairro, Cidade - Ci, cep 11220330"),
        }
    )
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
        createUsuarioDTO.validarCampos();
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(createUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @Operation(
        description = "Endpoint para solicitar uma ocorrência",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "email", 
                        description = "E-mail do usuário cadastrado", 
                        example = "maria.joao@email.com"),
            @Parameter(name = "descricao", 
                        description = "Descrição da tratativa da ocorrência", 
                        example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2")
        }
    )
    @PostMapping("/{email}/ocorrencias")
    public ResponseEntity<OcorrenciasDTO> solicitarOcorrencia(@PathVariable("email") String email, @RequestBody CreateOcorrenciaDTO createOcorrenciaDTO) {
        createOcorrenciaDTO.setEmail(email);
        createOcorrenciaDTO.validarCampos();
        OcorrenciasDTO ocorrenciaSalva = ocorrenciasService.registrarOcorrencia(createOcorrenciaDTO);
        //Para solicitar uma ocorrência
        return ResponseEntity.ok(ocorrenciaSalva);
    }


    @Operation(
        description = "Endpoint para acompanhar o andamento das ocorrências",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "email", 
                        description = "E-mail do usuário cadastrado", 
                        example = "maria.joao@email.com")
        }
    )
    @GetMapping("/{email}/ocorrencias")
    public ResponseEntity<List<OcorrenciasDTO>> acompanharOcorrencias(@PathVariable("email") String email) {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.listarOcorrenciasPorUsuario(email);
        //Para recuperar e retornar as ocorrências
        return ResponseEntity.ok(ocorrencias);
    }
    
}