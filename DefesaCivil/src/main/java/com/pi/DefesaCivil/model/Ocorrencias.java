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
@Table(name = "ocorrencias")
public class Ocorrencias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocorrencia")
    private Long idOcorrencia;
    
    @Column
    private String descricao;
    
    @Column(name = "data_abertura")
    private LocalDate dataAbertura;
    
    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;
    
    @Column
    private String contato;
    
    @Column(name = "nome_solicitante")
    private nomeSolicitante;

    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @OneToOne()
    private Administrador administrador;
        
}