package com.pi.DefesaCivil.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Senha é um campo obrigatório!")
    private String senha;
    
    @NotBlank(message = "Conta Google é um campo obrigatório!")
    private String email;
    
    @NotBlank(message = "Telefone é um campo obrigatório!")
    private String telefone;
    
    @NotNull(message = "Data de nascimento é um campo obrigatório!")
    private LocalDate dataNascimento;

    @NotBlank(message = "Endereço é um campo obrigatório!")
    private String endereco;    
}
