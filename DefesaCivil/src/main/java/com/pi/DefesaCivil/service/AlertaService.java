package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.AlertaDTO;
import com.pi.DefesaCivil.model.Alerta;

import java.util.List;

public interface AlertaService {

    List<AlertaDTO> listarAlertas();

    AlertaDTO adicionarAlerta(AlertaDTO alertaDTO);

    void removerAlerta(Long id);

    AlertaDTO atualizarAlerta(AlertaDTO alertaDTO);

    // Métodos utilitários para conversão
    AlertaDTO convertToDTO(Alerta alerta);

    Alerta convertToEntity(AlertaDTO alertaDTO);
}
