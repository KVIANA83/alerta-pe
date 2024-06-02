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
public class AtribuirProcessoDTO {

    private String codProcesso;
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
