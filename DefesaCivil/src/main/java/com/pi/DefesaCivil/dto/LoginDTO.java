package com.pi.DefesaCivil.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    
    @NotBlank
    private String tokenGoogle;
    
    // Outros campos necessários para o login, se houver
}