package com.javanauta.usuario.infraesctruture.repository;


import com.javanauta.usuario.infraesctruture.entity.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {

}
