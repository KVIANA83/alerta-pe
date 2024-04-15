package com.pi.DefesaCivil.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioDTO {
    
    @NotBlank(message = "Nome é um campo obrigatório!")
    private String nome;
    
    @NotBlank(message = "Conta Google é um campo obrigatório!")
    private String email;
    
    @NotBlank(message = "Telefone é um campo obrigatório!")
    private String telefone;
    
    @NotBlank(message = "Data de nascimento é um campo obrigatório!")
    private LocalDate dataNascimento;
    
}
