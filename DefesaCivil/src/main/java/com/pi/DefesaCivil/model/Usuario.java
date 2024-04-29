package com.pi.DefesaCivil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "nome_completo")
    private String nome;
    
    @Column
    private String email;
    
    @Column
    private String telefone;
    
    @Column
    private String endereco;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "token_google")
    private String tokenGoogle;
    
}
