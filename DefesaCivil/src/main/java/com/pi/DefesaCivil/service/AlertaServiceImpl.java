package com.pi.DefesaCivil.service;

import com.pi.DefesaCivil.dto.AlertaDTO;
import com.pi.DefesaCivil.model.Alerta;
import com.pi.DefesaCivil.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaServiceImpl implements AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Override
    public List<AlertaDTO> listarAlertas() {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AlertaDTO adicionarAlerta(AlertaDTO alertaDTO) {
        Alerta alerta = convertToEntity(alertaDTO);
        Alerta novoAlerta = alertaRepository.save(alerta);
        return convertToDTO(novoAlerta);
    }

    @Override
    public void removerAlerta(Long id) {
        alertaRepository.deleteById(id);
    }

    @Override
    public AlertaDTO atualizarAlerta(AlertaDTO alertaDTO) {
        Alerta alerta = convertToEntity(alertaDTO);
        Alerta alertaAtualizado = alertaRepository.save(alerta);
        return convertToDTO(alertaAtualizado);
    }

    @Override
    public AlertaDTO convertToDTO(Alerta alerta) {
        return AlertaDTO.builder()
                .id(alerta.getId())
                .descricao(alerta.getDescricao())
                .ativo(alerta.isAtivo())
                .build();
    }

    @Override
    public Alerta convertToEntity(AlertaDTO alertaDTO) {
        return Alerta.builder()
                .id(alertaDTO.getId())
                .descricao(alertaDTO.getDescricao())
                .ativo(alertaDTO.isAtivo())
                .build();
    }
}
