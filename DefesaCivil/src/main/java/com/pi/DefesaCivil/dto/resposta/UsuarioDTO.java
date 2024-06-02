package com.pi.DefesaCivil.dto.resposta;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
}