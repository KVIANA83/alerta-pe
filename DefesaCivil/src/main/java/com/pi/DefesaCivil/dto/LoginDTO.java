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
public class LoginDTO {
    
    @Schema(name = "login", 
            description = "Login do usuário cadastrado", 
            example = "A1234", 
            required = true)
    private String login;

    @Schema(name = "senha", 
            description = "Senha cadastrada pelo usuário", 
            example = "SeNHa.123", 
            required = true)
    private String senha;
    

    public void validarCampos() {
        if (this.getLogin() == null || this.getLogin().isBlank()) {
            throw new ValidacaoException("campo login é obrigatório");
        } 
        if (this.getSenha() == null || this.getSenha().isBlank()) {
            throw new ValidacaoException("campo senha é obrigatório");
        }
    }
}