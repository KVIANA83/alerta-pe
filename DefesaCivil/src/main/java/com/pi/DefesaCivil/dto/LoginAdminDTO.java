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
    
    @NotBlank(message = "campo username é obrigatório")
    private String username;
    
    @NotBlank(message = "campo password é obrigatório")
    private String password;
}
