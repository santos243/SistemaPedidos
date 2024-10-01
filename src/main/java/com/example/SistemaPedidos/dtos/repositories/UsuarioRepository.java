package com.example.SistemaPedidos.dtos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaPedidos.entities.UsuarioEntity;




@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
