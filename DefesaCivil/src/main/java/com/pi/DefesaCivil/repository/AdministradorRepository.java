package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    Optional<Administrador> findByNome(String nome);

    Optional<Administrador> findByCargo(String cargo);

    Optional<Administrador> findByLogin(String login);

}