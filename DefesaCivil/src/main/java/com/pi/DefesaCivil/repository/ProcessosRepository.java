package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.model.Ocorrencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessosRepository extends JpaRepository<Processos, Long> {
    
    Optional<List<Processos>> findAllByOcorrencia(@Param("ocorrencia") Ocorrencias ocorrencia);
}
