package com.javanauta.usuario.infraesctruture.repository;


import com.javanauta.usuario.infraesctruture.entity.EnderecosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecosEntity, Long> {
}
