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
public class LoginAdminDTO {
    
    private String login;
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
