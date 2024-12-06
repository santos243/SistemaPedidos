package com.example.SistemaPedidos.application.services;

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

import com.example.SistemaPedidos.dtos.PedidoRecordDto;
//import com.example.SistemaPedidos.dtos.repositories.ItemPedidoRepository;
import com.example.SistemaPedidos.dtos.repositories.PedidoRepository;
import com.example.SistemaPedidos.entities.ItemPedidoEntity;
import com.example.SistemaPedidos.entities.PedidoEntity;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;
//    @Mock
//    private ItemPedidoRepository itemPedidoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        List<PedidoEntity> p = new ArrayList<>();
        PedidoEntity pedido2 = new PedidoEntity(2L);
        PedidoEntity pedido = new PedidoEntity(1L);
        p.add(pedido);
        p.add(pedido2);

        lenient().when(pedidoRepository.findAll()).thenReturn(p);
        lenient().when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
    }

    @Test
    @DisplayName("Deve retornar uma lista com dois pedidos")
    public void deveRetornarUmaListComDoisPedidos() {
        final List<PedidoEntity> pedidos = pedidoRepository.findAll();
        assertEquals(2, pedidos.size());
        verify(pedidoRepository).findAll();
    }

    @Test
    @DisplayName("Deve retornar um pedido pelo id")
    public void deveRetornarUmPedidoPeloId() throws Exception {
        final var pedido = pedidoService.findPedidoById(1L);
        assertNotNull(pedido);
    }

    @Test
    @DisplayName("Deve lançar uma NullPointerException para falta de itens no pedido")
    public void deveLancarUmaExceptionParaFaltaDeItemPedido() {
        final var pedido = new PedidoRecordDto(1L, null);

        assertThrows(NullPointerException.class, () -> pedidoService.createPedido(pedido));
    }

    @Test
    @DisplayName("Deve lançar um NullPointerException para atributos nulos no pedido")
    public void test() throws Exception{
        final var pedido = new PedidoRecordDto(null, null);
        assertThrows(NullPointerException.class,() -> pedidoService.createPedido(pedido));
    }

    @Test
    @DisplayName("")
    public void test2(){

    }

    @Test
    @DisplayName("")
    public void test3(){

    }

    @Test
    @DisplayName("")
    public void test4(){

    }

}
