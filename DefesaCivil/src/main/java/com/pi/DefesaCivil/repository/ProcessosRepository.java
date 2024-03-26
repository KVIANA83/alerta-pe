package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessosRepository extends JpaRepository<Processos, Long> {
    
    Optional<List<Processos>> findAllByIdOcorrencias(@Param("ocorrencias") Ocorrencias ocorrencias);
    
    Optional<Processos> findById(Long idProcessos);

    Optional<List<Processos>> findAllByUsuario(@Param("usuario") Usuario usuario);

    Optional<List<Processos>> findAllByDataAbertura(@Param("dataAbertura") DataAbertura dataAbertura);

    Optional<List<Processos>> findAllByDataFechamento(@Param("dataFechamento") DataFechamento dataFechamento);

}