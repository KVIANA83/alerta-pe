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

        var admin = administradorService.getAdmin(loginAdminDTO.getUsername());

        if (!loginAdminDTO.getPassword().equals(admin.getSenha())) {
            throw new ValidacaoException("Login ou senha incorretos!");
        }

        return admin;
    }

    public Usuario validaLoginUsuario(LoginDTO loginDTO) {

        var userOpt = usuarioService.findByTokenGoogle(loginDTO.getTokenGoogle());

        if (userOpt.isEmpty()) {
            throw new ValidacaoException("usuário não cadastrado!");
        }

        return userOpt.get();
    }

}
