package com.example.SistemaPedidos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaPedidos.entities.ProdutoEntity;
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
