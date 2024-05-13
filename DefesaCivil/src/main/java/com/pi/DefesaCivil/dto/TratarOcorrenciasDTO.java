package com.pi.DefesaCivil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TratarOcorrenciasDTO {

    private Long idOcorrencia;
    private String loginAdmin;
    private String descricao;
    private String status;
    
    public void validateFields() {
        if (this.getIdOcorrencia() == null || this.getIdOcorrencia().isBlank()) {
            throw new ValidacaoException("campo id ocorrencia é obrigatório");
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