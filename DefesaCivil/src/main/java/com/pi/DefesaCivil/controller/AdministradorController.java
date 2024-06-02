package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.AtribuirOcorrenciaDTO;
import com.pi.DefesaCivil.dto.AtribuirProcessoDTO;
import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.dto.TratarProcessosDTO;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.model.Processos;
import com.pi.DefesaCivil.service.AdministradorService;
import com.pi.DefesaCivil.service.ProcessosService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {
    
    private final AdministradorService administradorService;
    private final ProcessosService processosService;
    

    //Endpoint para atribuir uma ocorrencia ao adminstrador
    @PostMapping("/atribuir-ocorrencias")
    public ResponseEntity<String> atribuirOcorrenciasParaAdmin(@RequestBody AtribuirOcorrenciaDTO atribuirOcorrenciaDTO) {
        atribuirOcorrenciaDTO.validarCampos();
        administradorService.atribuirOcorrencia(atribuirOcorrenciaDTO);
        return ResponseEntity.ok("Ocorrencia atribuída com sucesso!");
    }
    
    //Endpoint para atualizar o status da ocorrencia (vira processo ou não)
    @PutMapping("/tratar-ocorrencias")
    public ResponseEntity<String> tratarOcorrencias(@RequestBody TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        tratarOcorrenciasDTO.validarCampos();
        var ocorrencia = administradorService.tratarOcorrencia(tratarOcorrenciasDTO);

        if (ocorrencia.getStatus().equals(StatusEnum.CRIAR_PROCESSO.name())){
            //todo: chamar serevice de processo e crair um processo.
            var processoCriado = processosService.criarProcessos(ocorrencia);
            var mensagem = String.format("Ocorrencia virou Processo de código %s", processoCriado.getCodigo());
            return ResponseEntity.ok(mensagem);
        }
        return ResponseEntity.ok("Ocorrencia atualizada com sucesso!");
    }
    
    //Endpoint para acompanhar o andamento das ocorrências
    @GetMapping("/{loginAdmin}/ocorrencias")
    public ResponseEntity<List<Ocorrencias>> acompanharOcorrencias(@PathVariable("loginAdmin") String loginAdmin) {
        List<Ocorrencias> ocorrencias = administradorService.listarOcorrenciasPorAdmin(loginAdmin);
        //Para recuperar e retornar as ocorrências
        return ResponseEntity.ok(ocorrencias);
    }

    //Endpoint para listar todos os processos
    @GetMapping("/processos")
    public ResponseEntity<List<Processos>> listarProcessos() {
        List<Processos> processos = processosService.buscarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    //Endpoint para atribuir uma ocorrencia ao adminstrador
    @PostMapping("/atribuir-processos")
    public ResponseEntity<String> atribuirProcessosParaAdmin(@RequestBody AtribuirProcessoDTO atribuirProcessoDTO) {
        atribuirProcessoDTO.validarCampos();
        administradorService.atribuirProcesso(atribuirProcessoDTO);
        return ResponseEntity.ok("Ocorrencia atribuída com sucesso!");
    }

    //Endpoint para listar processos do admin
    @GetMapping("/{loginAdmin}/processos")
    public ResponseEntity<List<Processos>> listarProcessosDoAdmin(@PathVariable("loginAdmin") String loginAdmin) {
        List<Processos> processos = administradorService.listarProcessosPorAdmin(loginAdmin);
        return ResponseEntity.ok(processos);
    }


    //Endpoint para atualizar um processo
    @PutMapping("/atualizar-processo")
    public ResponseEntity<String> atualizarProcesso(@RequestBody TratarProcessosDTO tratarProcessosDTO) {
        tratarProcessosDTO.validarCampos();
        var mensagemFinal = processosService.atualizarProcesso(tratarProcessosDTO);
        return ResponseEntity.ok(mensagemFinal);
    }
}
