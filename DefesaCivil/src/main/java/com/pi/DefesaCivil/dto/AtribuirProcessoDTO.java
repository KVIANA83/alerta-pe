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
public class AtribuirProcessoDTO {

    @Schema(name = "codProcesso", 
            description = "Códico único do processo", 
            example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2", 
            required = true)
    private String codProcesso;

    @Schema(name = "loginAdmin", 
            description = "Login do Administrador cadastrado", 
            example = "A1234", 
            required = true)
    private String loginAdmin;


    public void validarCampos() {
        if (this.getCodProcesso() == null || this.getCodProcesso().isBlank()) {
            throw new ValidacaoException("campo cod processo é obrigatório");
        }
        if (this.getLoginAdmin() == null || this.getLoginAdmin().isBlank()) {
            throw new ValidacaoException("campo login Admin é obrigatório");
        }
    }
}
