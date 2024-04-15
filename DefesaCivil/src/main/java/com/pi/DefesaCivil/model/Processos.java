package com.pi.DefesaCivil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.pi.DefesaCivil.dto.StatusEnum;

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
    private String codigo;
    
    @Column
    private String descricao;
    
    @Column(name = "data_abertura")
    private LocalDate dataAbertura;
    
    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column
    private StatusEnum status;
    
    @Column
    private String contato;
    
    @OneToOne
    @JoinColumn(name = "id_ocorrencia")
    private Ocorrencias ocorrencia;

    @OneToOne
    @JoinColumn(name = "id_admin")
    private Administrador administrador;
}