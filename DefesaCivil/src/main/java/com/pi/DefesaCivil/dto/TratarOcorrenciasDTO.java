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

    @NotNull(message = "idOcorrencia é obrigatório")
    private Long idOcorrencia;

    @NotBlank(message = "loginAdmin é obrigatório")
    private String loginAdmin;

    @NotBlank(message = "descrição é obrigatório")
    private String descricao;

    @NotBlank(message = "status é obrigatório")
    private String status;
}