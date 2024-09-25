package infrastructure.repositories.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.entities.ProdutoEntity;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
