package com.javanauta.usuario.infraesctruture.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="endereco")

public class EnderecosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua", length = 120)
    private String rua;

    @Column(name = "numero", length = 6)
    private String numero;

    @Column (name = "complemento", length = 100)
    private String complemento;

    @Column (name = "cidade", length = 100)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column (name = "cep", length = 9)
    private  String cep;

    @Column(name = "usuario_id")
    private Long usuario_id;


}
