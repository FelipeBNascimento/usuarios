package com.javanauta.usuario.business;

import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.infraesctruture.entity.EnderecosEntity;
import com.javanauta.usuario.infraesctruture.entity.TelefoneEntity;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import com.javanauta.usuario.infraesctruture.exceptions.ConflictExceptions;
import com.javanauta.usuario.infraesctruture.exceptions.EmailNaoExiste;
import com.javanauta.usuario.infraesctruture.exceptions.IdNaoEncontrado;
import com.javanauta.usuario.infraesctruture.repository.EnderecoRepository;
import com.javanauta.usuario.infraesctruture.repository.TelefoneRepository;
import com.javanauta.usuario.infraesctruture.repository.UsuarioRepository;
import com.javanauta.usuario.infraesctruture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


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

    public UsuarioDTO atualizarUsuario(String token, UsuarioDTO usuarioDTO){

        String email = jwtUtil.extraiToken(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ?
                passwordEncoder.encode(usuarioDTO.getSenha()) : null );

        UsuariosEntity usuarios1 =  usuarioRepository.findByEmail(email).orElseThrow(
                () ->  new EmailNaoExiste("Email não encontrado")
    );

        UsuariosEntity usuarioAtualizado =  usuarioConverter.updateUsuario(usuarioDTO,
                usuarios1);

        UsuariosEntity usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

        return usuarioConverter.parausuarioDTO(usuarioSalvo);


    }

    public EnderecoDTO atualizarEndereco (Long idEndereco, EnderecoDTO enderecoDTO){

        EnderecosEntity enderecoNoBanco =  enderecoRepository.findById(idEndereco).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado " + idEndereco)
        );

        EnderecosEntity enderecoAtualizado =  usuarioConverter.updateEndereco(enderecoDTO, enderecoNoBanco);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(enderecoAtualizado));

    }

    public TelefoneDTO atualizarTelefone (Long idTelefone, TelefoneDTO telefoneDTO){

        TelefoneEntity telefoneNoBanco  = telefoneRepository.findById(idTelefone).orElseThrow(
                () -> new IdNaoEncontrado("Id não encotrado " + idTelefone)
        );

        TelefoneEntity telefoneAtualizado = usuarioConverter.updateTelefone(telefoneDTO, telefoneNoBanco);

        return usuarioConverter.paraTelefoneDTO( telefoneRepository.save(telefoneAtualizado));
    }

    public EnderecoDTO cadastrarEndereco (String token, EnderecoDTO enderecoDTO){

        String email = jwtUtil.extraiToken(token.substring(7));
        UsuariosEntity usuariosEntity = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new EmailNaoExiste("Email não existente " + email)
        );
        EnderecosEntity enderecosEntity = usuarioConverter.parCadastrarEndereco
                (enderecoDTO, usuariosEntity.getId());
        EnderecosEntity enderecoSalvo = enderecoRepository.save(enderecosEntity);
        return usuarioConverter.paraEnderecoDTO(enderecoSalvo);
    }

    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO telefoneDTO){

        String email = jwtUtil.extraiToken(token.substring(7));

        UsuariosEntity usuario = usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new EmailNaoExiste("Email não existente" + email)
        );

        TelefoneEntity telefoneEntity = usuarioConverter.paraCadastrarTelefone(telefoneDTO,
                usuario.getId());

        TelefoneEntity telefoneSalvo = telefoneRepository.save(telefoneEntity);
        return usuarioConverter.paraTelefoneDTO(telefoneSalvo);
    }

}
