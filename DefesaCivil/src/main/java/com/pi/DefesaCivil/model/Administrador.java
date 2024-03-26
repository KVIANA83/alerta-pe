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
@Table(name = "admin")
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long idAdministrador;
    
    @Column
    private String nome;
    
    @Column
    private String email;
    
    @Column
    private String telefone;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @Column
    private String cargo;
    
    @Column
    private String login;
    
    @Column
    private String senha;
    
}