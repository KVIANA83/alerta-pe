package com.pi.DefesaCivil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.pi.DefesaCivil.dto.StatusEnum;

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
    private String codigo;
    
    @Column
    private String descricao;
    
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;
    
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;
    
    @Column
    private String contato;
    
    @Column
    private StatusEnum status;

    @OneToOne
    @JoinColumn(name = "id_solicitante")
    private Usuario solicitante;

    @OneToOne
    @JoinColumn(name = "id_admin")
    private Administrador administrador;
        
}
