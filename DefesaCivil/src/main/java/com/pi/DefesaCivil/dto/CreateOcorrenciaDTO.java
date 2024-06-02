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
public class CreateOcorrenciaDTO {

    @Schema(name = "email", 
            description = "E-mail do usuário cadastrado", 
            example = "maria.joao@email.com", 
            required = true)
    private String email;

    @Schema(name = "descricao", 
            description = "Descrição da tratativa da ocorrência", 
            example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2", 
            required = true)
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
