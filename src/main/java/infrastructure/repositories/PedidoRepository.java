package infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.entities.PedidoEntity;



@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

}
