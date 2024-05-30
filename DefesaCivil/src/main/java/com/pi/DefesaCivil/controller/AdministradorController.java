package com.pi.DefesaCivil.controller;

import com.pi.DefesaCivil.dto.StatusEnum;
import com.pi.DefesaCivil.dto.TratarOcorrenciasDTO;
import com.pi.DefesaCivil.model.Ocorrencias;
import com.pi.DefesaCivil.service.AdministradorService;
import com.pi.DefesaCivil.service.ProcessosService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {
    
    private final AdministradorService administradorService;
    private final ProcessosService processosService;

    //todo:
    //criar endpoint para listar processos
    //criar enpoint para listar processos do admin
    //criar endpoint para atualizar processo

    //Endpoint para listar todas as ocorrências sem admin
    @PostMapping("/listar-ocorrencias")
    public ResponseEntity<List<Ocorrencias>> listarOcorrenciasSemAdministrador() {
        List<Ocorrencias> ocorrencias = administradorService.listarOcorrenciasLivres();
        return ResponseEntity.ok(ocorrencias);
    }

    //Endpoint para atribuir uma ocorrencia ao adminstrador
    @PostMapping("/atribuir-ocorrencias")
    public ResponseEntity<String> atribuirOcorrenciasParaAdmin(Long idOcorrencia, String loginAdmin) {
        administradorService.atribuirOcorrencia(idOcorrencia, loginAdmin);
        return ResponseEntity.ok("Ocorrencia atribuída com sucesso!");
    }
    
    //Endpoint para atualizar o status da ocorrencia (vira processo ou não)
    @PostMapping("/tratar-ocorrencias")
    public ResponseEntity<String> tratarOcorrencias(@Valid @RequestBody TratarOcorrenciasDTO tratarOcorrenciasDTO) {
        var ocorrencia = administradorService.tratarOcorrencia(tratarOcorrenciasDTO);

        if (ocorrencia.getStatus().equals(StatusEnum.CRIAR_PROCESSO)){
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
        List<Processos> processos = processosService.listarTodosProcessos();
        return RespondeEntity.ok(processos);
    }

    //Endpoint para listar processos do admin
    @GetMapping("/{loginAdmin}/processos")
    public ResponseEntity<List<Processos>> listarProcessosDoAdmin(@PathVariable("loginAdmin") String loginAdmin) {
        List<Processos> processos = processosService.listarProcessosPorAdmin(loginAdmin);
        return ResponseEntity.ok(processos);
    }


    //Endpoint para atualizar um processo
    @PutMapping("/processos/{id}")
    public ResponseEntity<String> atualizarProcesso(@PathVariable Long id, @Valid @RequestBody Processos processoAtualizado) {
        List processosService.atualizarProcesso(id, processoAtualizado);
        return ResponseEntity.ok("Processo atualizado com sucesso.")
    }

    //Outros endpoints para atualizar e excluir processos, se necessário
}
