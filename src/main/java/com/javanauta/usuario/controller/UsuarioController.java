package com.javanauta.usuario.controller;


import com.javanauta.usuario.business.EnderecoDTO;
import com.javanauta.usuario.business.TelefoneDTO;
import com.javanauta.usuario.business.UsuarioDTO;
import com.javanauta.usuario.business.UsuarioSevice;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import com.javanauta.usuario.infraesctruture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioSevice usuarioSevice;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        return ResponseEntity.ok(usuarioSevice.salvarusuario(usuarioDTO));
    }

    @GetMapping
    public ResponseEntity<UsuariosEntity> buscarUsuarioPeloEmail(@RequestParam("email") String email){

        return ResponseEntity.ok(usuarioSevice.BuscarPorEmail(email));
    }

    @DeleteMapping("{email}")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email){
        usuarioSevice.apagarPeloEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(), usuarioDTO.getSenha()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }

    //  Authentication authentication = authenticationManager.authenticate(
    //                new UsernamePasswordAuthenticationToken(
    //                        usuarioDto.getEmail(), usuarioDto.getSenha()));
    //        return "Bearer " +  jwtUtil.generateToken(authentication.getName());

    @PutMapping

    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                       @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok( usuarioSevice.atualizarUsuario(token, usuarioDTO));

    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualiazarEndereco(@RequestParam Long id,
                                                          @RequestBody EnderecoDTO enderecoDTO){

        return ResponseEntity.ok(usuarioSevice.atualizarEndereco(id, enderecoDTO));

    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualziarTelefone (@RequestParam Long id,
                                                          @RequestBody TelefoneDTO telefoneDTO){

        return ResponseEntity.ok(usuarioSevice.atualizarTelefone(id, telefoneDTO));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader ("Authorization") String token){

        return ResponseEntity.ok(usuarioSevice.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone (@RequestBody TelefoneDTO telefoneDTO,
                                                          @RequestHeader("Authorization") String token){

        return ResponseEntity.ok(usuarioSevice.cadastrarTelefone(token, telefoneDTO));
    }
}
