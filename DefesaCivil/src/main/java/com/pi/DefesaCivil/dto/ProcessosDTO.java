package com.pi.DefesaCivil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessosDTO {
    
    private Long idProcessos;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private String descricao;
}