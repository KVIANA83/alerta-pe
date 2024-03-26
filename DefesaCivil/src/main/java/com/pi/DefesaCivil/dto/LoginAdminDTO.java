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
public class LoginAdminDTO {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    // Outros campos necessários para o login de administrador, se houver
}
