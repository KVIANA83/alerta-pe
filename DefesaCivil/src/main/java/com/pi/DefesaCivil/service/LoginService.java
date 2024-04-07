package com.pi.DefesaCivil.service;

import org.springframework.stereotype.Service;

import com.pi.DefesaCivil.dto.LoginAdminDTO;
import com.pi.DefesaCivil.dto.LoginDTO;
import com.pi.DefesaCivil.exceptions.ValidacaoException;
import com.pi.DefesaCivil.model.Administrador;
import com.pi.DefesaCivil.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoginService {

    private final AdministradorService administradorService;
    private final UsuarioService usuarioService;

    public Administrador validaLoginAdmin(LoginAdminDTO loginAdminDTO) {

        var adminOpt = administradorService.getAdmin(loginAdminDTO.getUsername());

        if (!adminOpt.isPresent()) {
            throw new ValidacaoException("Administrador não cadastrado!");
        }

        var admin = adminOpt.get();
        if (!loginAdminDTO.getPassword().equals(admin.getSenha())) {
            throw new ValidacaoException("Login ou senha incorretos!");
        }

        return admin;
    }

    public Usuario validaLoginUsuario(LoginDTO loginDTO) {

        var userOpt = usuarioService.findByEmail(loginDTO.getTokenGoogle());

        //TODO: RECEBER O TOKEN DO GOOGLE
        // CHAMAR API DO GOOGLE COM O TOKEN
        // SE DER OK, PEGAR OS DADOS E BATER NO BANCO PARA PEGAR USUARIO
        // VALIDAR SE EXISTE O USUARIO NA BASE
    }

}
