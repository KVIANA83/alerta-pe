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
    
    //@Column(name = "nome_solicitante")
    //private String nomeSolicitante;

    @Column
    private StatusEnum status;

    @OneToMany
    @Column(name = "id_solicitante")
    private Usuario idSolicitante;

    @OneToMany
    @Column(name = "id_admin")
    private Administrador administrador;
        
}
