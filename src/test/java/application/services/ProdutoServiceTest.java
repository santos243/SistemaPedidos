package application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.SistemaPedidos.dtos.ProdutoRecordDto;

import domain.entities.ProdutoEntity;
import infrastructure.repositories.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        ProdutoEntity produto = new ProdutoEntity((long) 1, "Serra Eletrica", "Marcenaria", 1000.0);
        lenient().when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produto));
    }

    @Test
    @DisplayName("Dever retornar uma lista com apenas um produto")
    public void deveRetornarUmaListaDeProdutos() {
        List<ProdutoEntity> produtos = produtoService.getAllProdutos();

        assertEquals(1, produtos.size());
        verify(produtoRepository).findAll();
        verifyNoInteractions(produtoRepository.findAll());
    }

    @Test
    @DisplayName("Deve buscar produto pelo id")
    public void deveRetornarUmProdutoPeloId() throws Exception {
        ProdutoEntity produto = produtoService.findProdutoById((long)1);
        when(produtoService.findProdutoById((long)1)).thenReturn(produto);
        assertNotNull(produto);
    }

    @Test
    @DisplayName("Deve criar um produto")
    public ProdutoEntity deveCriarUmProduto() {
        final var produtoCriado = new ProdutoRecordDto("Cortador de grama", "Marcenaria", 300.0);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setCategoria(produtoCriado.categoria());
        produto.setNome(produtoCriado.nome());
        produto.setValor(produtoCriado.valor());
        produtoRepository.save(produto);

        return produto;
    }

}
