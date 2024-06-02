package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.exceptions.BodyMensagem;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Usuario;
import com.pi.DefesaCivil.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Alerta-PE Login Controller API")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    
     @Operation(
        description = "Endpoint para realizar login de administrador",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
        parameters = {
            @Parameter(name = "login", 
                        description = "Login do Administrador cadastrado", 
                        example = "A1234"),
            @Parameter(name = "password", 
                        description = "Senha cadastrada pelo Administrador", 
                        example = "sEnhA123.")
        }
    )
    @PostMapping("/admin")
    public ResponseEntity<Administrador> login(@RequestBody LoginAdminDTO loginAdminDTO) {
        loginAdminDTO.validarCampos();
        log.info("Iniciando login de Administrador: {}", loginAdminDTO.getLogin());
        var admin = service.validaLoginAdmin(loginAdminDTO);
    
        return ResponseEntity.ok(admin);
    }


    @Operation(
        description = "Endpoint para realizar login de usuário",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a requisição", 
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodyMensagem.class))})
            },
            parameters = {
                @Parameter(name = "login", 
                            description = "Login do usuário cadastrado", 
                            example = "A1234"),
                @Parameter(name = "senha", 
                            description = "Senha cadastrada pelo usuário", 
                            example = "SeNHa.123")
            }
    )
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> login(@RequestBody LoginDTO loginDTO) {
        loginDTO.validarCampos();
        log.info("Iniciando login de Administrador: {}", loginDTO.getLogin());
        var user = service.validaLoginUsuario(loginDTO);

        return ResponseEntity.ok(user);
    }
}
