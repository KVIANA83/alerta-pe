package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Ocorrencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OcorrenciasRepository extends JpaRepository<Ocorrencias, Long> {
    
    Optional<Ocorrencias> findById(Long idOcorrencias);
    
    Optional<Ocorrencias> findByDataAbertura(LocalDate dataAbertura);
    
    Optional<Ocorrencias> findByDescricao(String descricao);
    
}