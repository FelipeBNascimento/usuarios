package com.javanauta.usuario.business;

import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import com.javanauta.usuario.infraesctruture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvarusuario(UsuarioDTO usuarioDTO) {

        UsuariosEntity usuarios = usuarioConverter.parausuario(usuarioDTO);
        usuarios = usuarioRepository.save(usuarios);
        return usuarioConverter.parausuarioDTO(usuarios);

    }
}
