package com.pi.DefesaCivil.dto.resposta;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDTO {

    private String codigo;
    private String descricao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private String status;
    private String contato;
    private String numeroOcorrencia;
    private String loginAdministrador;
}
