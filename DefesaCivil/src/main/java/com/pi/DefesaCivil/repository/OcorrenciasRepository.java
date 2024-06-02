package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OcorrenciasRepository extends JpaRepository<Ocorrencias, Long> {
    
    Optional<Ocorrencias> findByDataAbertura(LocalDate dataAbertura);
    
    Optional<Ocorrencias> findByDescricao(String descricao);

    Optional<List<Ocorrencias>> findBySolicitante(Usuario usuario);

    Optional<List<Ocorrencias>> findByAdministrador(Administrador admin);

    @Query(value = "select o.* from ocorrencias o where o.id_admin is null", nativeQuery = true)
    Optional<List<Ocorrencias>> findAllSemAdministrador();

    Optional<Ocorrencias> findByCodigo(String codigo);
}