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
public class CreateOcorrenciaDTO {

    private String email;
    private String descricao;


    public void validarCampos() {
        if (this.getEmail() == null || this.getEmail().isBlank()) {
            throw new ValidacaoException("campo e-mail é obrigatório");
        }
        if (this.getDescricao() == null || this.getDescricao().isBlank()) {
            throw new ValidacaoException("campo descrição é obrigatório");
        }
    }
}
