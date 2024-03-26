package com.pi.DefesaCivil.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioDTO {
    
    @NotBlank
    private String nome;
    
    @NotBlank(message = "Conta Google é um campo obrigatório!")
    private String email;
    
    @NotBlank(message = "Telefone é um campo obrigatório!")
    private String telefone;
    
    @NotBlank(message = "Endereço é um campo obrigatório!")
    private String endereco;
    
}