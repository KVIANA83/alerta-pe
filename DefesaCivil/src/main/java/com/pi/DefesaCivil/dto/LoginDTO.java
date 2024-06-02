package com.pi.DefesaCivil.dto;

import com.pi.DefesaCivil.exceptions.ValidacaoException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    
    private String login;
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