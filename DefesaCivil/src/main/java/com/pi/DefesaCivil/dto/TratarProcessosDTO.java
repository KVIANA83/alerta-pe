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
public class TratarProcessosDTO {

    private String idProcesso;
    private String loginAdmin;
    private String descricao;
    private String status;
    
    public void validarCampos() {
        if (this.getIdProcesso() == null || this.getIdProcesso().isBlank()) {
            throw new ValidacaoException("campo id processo é obrigatório");
        } 
        if (this.getLoginAdmin() == null || this.getLoginAdmin().isBlank()) {
            throw new ValidacaoException("campo login do administrador é obrigatório");
        }
        if (this.getDescricao() == null || this.getDescricao().isBlank()) {
            throw new ValidacaoException("campo descricao é obrigatório");
        } 
        if (this.getStatus() == null || this.getStatus().isBlank()) {
            throw new ValidacaoException("campo status é obrigatório");
        }
    }
}