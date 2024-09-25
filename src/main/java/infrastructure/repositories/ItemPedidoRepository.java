package infrastructure.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.entities.ItemPedidoEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {

    /**
     * Obs: verificar o motivo da API nao estar apagando os itens do banco de
     * dados utilizando os metodos existentes no JPA.
     * @param id_pedido
     */
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM item_pedido\n"
            + "WHERE pedido_entity_id_pedido=?1;")
    void deleteAllByIdPedido(Long id_pedido);

}
