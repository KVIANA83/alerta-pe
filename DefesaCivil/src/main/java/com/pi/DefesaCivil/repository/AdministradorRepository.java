package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    Optional<Administrador> findByNomeCompleto(@Param("nome") String nome);

    Optional<Administrador> findByCargo(@Param("cargo") String cargo);

}