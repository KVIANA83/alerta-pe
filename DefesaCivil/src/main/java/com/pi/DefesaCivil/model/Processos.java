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
@Table(name = "processos")
public class Processos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_processos")
    private Long idProcessos;
    
    @Column
    private String descricao;
    
    @Column(name = "data_abertura")
    private LocalDate dataAbertura;
    
    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;
    
    @Column
    private String contato;
    
    @JoinColumn(name = "id_ocorrencia", referencedColumnName = "id_ocorrencia")
    @OneToOne
    private Ocorrencia ocorrencia;

    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @OneToOne()
    private Administrador administrador;
        
}