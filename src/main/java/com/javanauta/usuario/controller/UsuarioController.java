package com.javanauta.usuario.controller;


import com.javanauta.usuario.business.UsuarioDTO;
import com.javanauta.usuario.business.UsuarioSevice;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioSevice usuarioSevice;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario (@RequestBody UsuarioDTO usuarioDTO){

        return ResponseEntity.ok(usuarioSevice.salvarusuario(usuarioDTO));

    }
}
