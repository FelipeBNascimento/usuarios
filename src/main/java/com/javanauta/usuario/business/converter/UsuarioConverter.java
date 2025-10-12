package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.EnderecoDTO;
import com.javanauta.usuario.business.TelefoneDTO;
import com.javanauta.usuario.business.UsuarioDTO;
import com.javanauta.usuario.infraesctruture.entity.EnderecosEntity;
import com.javanauta.usuario.infraesctruture.entity.TelefoneEntity;
import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public UsuariosEntity parausuario(UsuarioDTO usuarioDTO) {

        UsuariosEntity usuarios = UsuariosEntity.builder()

                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoEntity(usuarioDTO.getEndereco()))
                .telefone(paraListaTelefoneEntity(usuarioDTO.getTelefone()))
                .build();

        return usuarios;
    }


    public EnderecosEntity paraEnderecoEntity(EnderecoDTO enderecoDTO) {

        EnderecosEntity enderecos = EnderecosEntity.builder()

                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();

        return enderecos;
    }

    public List<EnderecosEntity> paraListaEnderecoEntity(List<EnderecoDTO> enderecoDTOS) {

        List<EnderecosEntity> enderecosEntities = new ArrayList<>();

        for (EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecosEntities.add(paraEnderecoEntity(enderecoDTO));
        }
        return enderecosEntities;

    }

    public TelefoneEntity paraTelefoneEntity(TelefoneDTO telefoneDTO) {

        TelefoneEntity telefoneEntity = TelefoneEntity.builder()

                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();

        return telefoneEntity;

    }

    public List<TelefoneEntity> paraListaTelefoneEntity(List<TelefoneDTO> telefoneDTOS) {

        return telefoneDTOS.stream().map(this::paraTelefoneEntity).toList();
    }

    public UsuarioDTO parausuarioDTO(UsuariosEntity usuariosEntity) {

        UsuarioDTO usuarios = UsuarioDTO.builder()

                .nome(usuariosEntity.getNome())
                .email(usuariosEntity.getEmail())
                .senha(usuariosEntity.getSenha())
                .endereco(paraListaEnderecoDTO(usuariosEntity.getEnderecos()))
                .telefone(paraListaTelefoneDTO(usuariosEntity.getTelefone()))
                .build();

        return usuarios;
    }

    public EnderecoDTO paraEnderecoDTO(EnderecosEntity enderecosEntity) {

        EnderecoDTO enderecos = EnderecoDTO.builder()

                .rua(enderecosEntity.getRua())
                .numero(enderecosEntity.getNumero())
                .complemento(enderecosEntity.getComplemento())
                .cidade(enderecosEntity.getCidade())
                .estado(enderecosEntity.getEstado())
                .cep(enderecosEntity.getCep())
                .build();

        return enderecos;
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<EnderecosEntity> enderecosEntities) {

        return enderecosEntities.stream().map(this::paraEnderecoDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(TelefoneEntity telefoneEntity) {

        TelefoneDTO telefoneDTO = TelefoneDTO.builder()
                .ddd(telefoneEntity.getDdd())
                .numero(telefoneEntity.getNumero())
                .build();

        return telefoneDTO;
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<TelefoneEntity> telefoneEntities) {

        List<TelefoneDTO> telefoneDTOS = new ArrayList<>();

        for (TelefoneEntity telefoneEntity : telefoneEntities) {

            telefoneDTOS.add(paraTelefoneDTO(telefoneEntity));
        }

        return telefoneDTOS;
    }

    public UsuariosEntity updateUsuario(UsuarioDTO dto, UsuariosEntity entity) {

        return UsuariosEntity.builder()

                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .senha(dto.getSenha() != null ? dto.getSenha() : entity.getSenha())
                .enderecos(entity.getEnderecos())
                .telefone(entity.getTelefone())
                .build();


    }

    public EnderecosEntity updateEndereco(EnderecoDTO dto, EnderecosEntity enderecos) {

        return EnderecosEntity.builder()

                .id(enderecos.getId())
                .rua(dto.getRua() != null ? dto.getRua() : enderecos.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : enderecos.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : enderecos.getComplemento())
                .cidade(dto.getCidade() != null ? dto.getCidade() : enderecos.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : enderecos.getEstado())
                .cep(dto.getCep() != null ? dto.getCep() : enderecos.getCep())
                .build();
    }

    public TelefoneEntity updateTelefone(TelefoneDTO dto, TelefoneEntity telefone) {

        return TelefoneEntity.builder()

                .id(telefone.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : telefone.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : telefone.getNumero())
                .build();
    }

    public EnderecosEntity parCadastrarEndereco(EnderecoDTO enderecoDTO, Long IdUssuario) {

        return EnderecosEntity.builder()

                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .usuario_id(IdUssuario)
                .build();
    }

    public TelefoneEntity paraCadastrarTelefone(TelefoneDTO telefoneDTO, Long IdUsuario) {

        return TelefoneEntity.builder()

                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .usuario_id(IdUsuario)
                .build();
    }

}
