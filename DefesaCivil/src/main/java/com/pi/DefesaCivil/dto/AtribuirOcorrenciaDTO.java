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
public class AtribuirOcorrenciaDTO {

    private String codOcorrencia;
    private String loginAdmin;


    public void validarCampos() {
        if (this.getCodOcorrencia() == null || this.getCodOcorrencia().isBlank()) {
            throw new ValidacaoException("campo cod ocorrência é obrigatório");
        }
        if (this.getLoginAdmin() == null || this.getLoginAdmin().isBlank()) {
            throw new ValidacaoException("campo login Admin é obrigatório");
        }
    }
}
