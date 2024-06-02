package com.pi.DefesaCivil.dto;

import java.time.LocalDate;

import com.pi.DefesaCivil.exceptions.ValidacaoException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioDTO {
    
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco; 
    
    public void validarCampos() {
        if (this.getNome() == null || this.getNome().isBlank()) {
            throw new ValidacaoException("campo nome é obrigatório");
        }
        if (this.getEmail() == null || this.getEmail().isBlank()) {
            throw new ValidacaoException("campo e-mail é obrigatório");
        } 
        if (this.getSenha() == null || this.getSenha().isBlank()) {
            throw new ValidacaoException("campo senha é obrigatório");
        }
        if (this.getTelefone() == null || this.getTelefone().isBlank()) {
            throw new ValidacaoException("campo telefone é obrigatório");
        }
        if (this.getDataNascimento() == null) {
            throw new ValidacaoException("campo data de nascimento é obrigatório");
        } 
        if (this.getEndereco() == null || this.getEndereco().isBlank()) {
            throw new ValidacaoException("campo endereço é obrigatório");
        }
    }
}
