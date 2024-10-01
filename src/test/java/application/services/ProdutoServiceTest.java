package application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.example.SistemaPedidos.application.services.ProdutoService;
import com.example.SistemaPedidos.application.services.exceptions.SemCategoriaException;
import com.example.SistemaPedidos.application.services.exceptions.SemNomeException;
import com.example.SistemaPedidos.dtos.ProdutoRecordDto;
import com.example.SistemaPedidos.dtos.repositories.ProdutoRepository;
import com.example.SistemaPedidos.entities.ProdutoEntity;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() throws Exception {

        List<ProdutoEntity> p = new ArrayList<>();

        ProdutoEntity produto = new ProdutoEntity((long) 1, "Serra Eletrica", "Marcenaria", 1000.0);
        p.add(produto);

        lenient().when(produtoRepository.findAll()).thenReturn(p);
        lenient().when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        // lenient().when(produtoRepository.save(produto)).thenReturn(produto);
    }

    @Test
    @DisplayName("Dever retornar uma lista com apenas um produto")
    public void deveRetornarUmaListaDeProdutos() {
        List<ProdutoEntity> produtos = produtoService.getAllProdutos();
        assertEquals(1, produtos.size());
        verify(produtoRepository).findAll();
    }

    @Test
    @DisplayName("Deve buscar produto pelo id")
    public void deveRetornarUmProdutoPeloId() throws Exception {
        var produto = produtoService.getProduto(1L);
        assertNotNull(produto);
    }

    @Test
    @DisplayName("Deve lançar uma exception para criação de um produto sem nome")
    public void deveLancarUmaExceptionParaNomeVazio() throws Exception {
        final var produtoCriado = new ProdutoRecordDto("", "Marcenaria", 300.0);
        assertThrows(SemNomeException.class, () -> produtoService.addProduto(produtoCriado));
    }

    @Test
    @DisplayName("Deve lançar uma exception para String categoria, uma vez que ela esta vazia")
    public void deveLancarUmaExceptionParaCategoriaVazia() throws Exception {
        final var produto = new ProdutoRecordDto("Cortador de grama eletrico", "", 1699);
        assertThrows(SemCategoriaException.class, () -> produtoService.addProduto(produto));
    }

}
