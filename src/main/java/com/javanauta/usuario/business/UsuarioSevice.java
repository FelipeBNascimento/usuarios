package com.javanauta.usuario.business;

import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import com.javanauta.usuario.infraesctruture.exceptions.ConflictExceptions;
import com.javanauta.usuario.infraesctruture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;


    public UsuarioDTO salvarusuario(UsuarioDTO usuarioDTO) {

        existeEmail(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        UsuariosEntity usuarios = usuarioConverter.parausuario(usuarioDTO);
        usuarios = usuarioRepository.save(usuarios);
        return usuarioConverter.parausuarioDTO(usuarios);

    }

    public boolean buscarEmail(String email) {

        return usuarioRepository.existsByEmail(email);

    }

    public void existeEmail(String email) {

        boolean existe = buscarEmail(email);
        try {

            if (existe) {
                throw new ConflictExceptions("email ja existe");
            }
        } catch (ConflictExceptions e) {
            throw new ConflictExceptions("email ja existe" + e.getCause());

        }
    }

    public void apagarPeloEmail(String email){

        usuarioRepository.deleteByEmail(email);
    }

    public UsuariosEntity BuscarPorEmail(String email){

        return  usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ConflictExceptions("email não existe" + email )
        );
    }
}
