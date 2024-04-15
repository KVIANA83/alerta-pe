package com.pi.DefesaCivil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OcorrenciasDTO {
    
    private Long idOcorrencia;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private String nomeSolicitante;
    private String codigo;
    private String descricao;
    private String contato;
    private StatusEnum status;
}