package com.javanauta.usuario.infraesctruture.repository;


import com.javanauta.usuario.infraesctruture.entity.UsuariosEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuariosEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UsuariosEntity> findByEmail (String email);

    @Transactional
    void deleteByEmail(String email);
}
