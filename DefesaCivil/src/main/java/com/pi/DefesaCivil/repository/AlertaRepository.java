package com.pi.DefesaCivil.repository;

import com.pi.DefesaCivil.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
}