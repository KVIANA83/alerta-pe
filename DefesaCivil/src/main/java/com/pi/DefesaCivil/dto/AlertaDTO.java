package com.pi.DefesaCivil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDTO {
    
    private Long id;
    private String descricao;
    private boolean ativo;
}