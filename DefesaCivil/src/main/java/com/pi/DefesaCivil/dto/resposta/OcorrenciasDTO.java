package com.pi.DefesaCivil.dto.resposta;

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
    
    private String codigo;
    private String descricao;
    private String observacao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private String contato;
    private String status;
    private String nomeSolicitante;
    private String loginAdministrador;
}