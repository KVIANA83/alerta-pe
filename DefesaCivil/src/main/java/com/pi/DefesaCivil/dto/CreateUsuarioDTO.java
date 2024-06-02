package com.pi.DefesaCivil.dto;

import java.time.LocalDate;

import com.pi.DefesaCivil.exceptions.ValidacaoException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioDTO {
    
    @Schema(name = "nome", 
            description = "Nome do usuário cadastrado", 
            example = "Maria João da Silva", 
            required = true)
    private String nome;

    @Schema(name = "senha", 
            description = "Senha do usuário cadastrado", 
            example = "SeNHA.123", 
            required = true)
    private String senha;

    @Schema(name = "email", 
            description = "E-mail do usuário cadastrado", 
            example = "maria.joao@email.com", 
            required = true)
    private String email;

    @Schema(name = "telefone", 
            description = "Telefone do usuário cadastrado", 
            example = "81988776655", 
            required = true)
    private String telefone;

    @Schema(name = "dataNascimento", 
            description = "Data de nascimento do usuário cadastrado", 
            example = "2000-01-01", 
            required = true)
    private LocalDate dataNascimento;
    
    @Schema(name = "endereco", 
            description = "Endereço do usuário cadastrado", 
            example = "Rua rua, s/n, Bairro bairro, Cidade - Ci, cep 11220330", 
            required = true)
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
