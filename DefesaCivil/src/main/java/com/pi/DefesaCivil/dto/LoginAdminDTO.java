package com.pi.DefesaCivil.dto;

import com.pi.DefesaCivil.exceptions.ValidacaoException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginAdminDTO {
    
    @Schema(name = "login", 
            description = "Login do Administrador cadastrado", 
            example = "A1234", 
            required = true)
    private String login;

    @Schema(name = "password", 
            description = "Senha cadastrada pelo Administrador", 
            example = "sEnhA123.", 
            required = true)
    private String password;


    public void validarCampos() {
        if (this.getLogin() == null || this.getLogin().isBlank()) {
            throw new ValidacaoException("campo login é obrigatório");
        } 
        if (this.getPassword() == null || this.getPassword().isBlank()) {
            throw new ValidacaoException("campo password é obrigatório");
        }
    }
}
