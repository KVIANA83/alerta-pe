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
public class TratarOcorrenciasDTO {

    @Schema(name = "idOcorrencia", 
            description = "Id da ocorrência registrada", 
            example = "123", 
            required = true)
    private String idOcorrencia;

    @Schema(name = "loginAdmin", 
            description = "Login do Administrador cadastrado", 
            example = "A12345", 
            required = true)
    private String loginAdmin;

    @Schema(name = "descricao", 
            description = "Descrição da tratativa da ocorrência", 
            example = "bac6a814-423a-41f7-a257-7b7b38a7d6c2", 
            required = true)
    private String descricao;

    @Schema(name = "status", 
            description = "Status de atualização da ocorrência", 
            example = "RESOLVIDO", 
            required = true)
    private String status;
    

    public void validarCampos() {
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