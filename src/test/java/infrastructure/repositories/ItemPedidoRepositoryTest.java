package infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
@DataJpaTest
@ActiveProfiles("test")
public class ItemPedidoRepositoryTest {
    @Test
    void testDeleteAllByIdPedido() {

    }
}
