package com.example.SistemaPedidos.application.services.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaPedidos.application.services.PedidoService;
import com.example.SistemaPedidos.dtos.PedidoRecordDto;
import com.example.SistemaPedidos.entities.PedidoEntity;

import jakarta.validation.Valid;

@RestController
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    public ResponseEntity<PedidoEntity> addPedido(@RequestBody @Valid PedidoRecordDto pedidoRecordDto)
            throws Exception {
        var pedido = pedidoService.createPedido(pedidoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<PedidoEntity> getPedido(@PathVariable(value = "idPedido") Long id_pedido) throws Exception {
        var pedido = pedidoService.getPedido(id_pedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @GetMapping("/pedido")
    public ResponseEntity<List<PedidoEntity>> getAllPedidos() throws Exception {
        var pedidos = pedidoService.getAllPedido();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    /**
     * Delete em todos os itens pedidos dentro de um pedido sem afetar o pedido em
     * si
     * "PEDIDO"
     *
     * @param id_pedido
     * @param pedidoRecordDto
     * @return
     * @throws Exception
     */
    @PutMapping("/pedido/{pedido}")
    public ResponseEntity<String> addItemAoPedido(@PathVariable(value = "pedido") Long id_pedido,
            @RequestBody @Valid PedidoRecordDto pedidoRecordDto) throws Exception {
        pedidoService.addItensAoPedido(id_pedido, pedidoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body("Itens adicionado ao pedido do usuario");
    }

    // // Apagar itens do pedido
    // @PutMapping("/pedido/{pedido}")
    // public ResponseEntity<String> deleteItemPEdido(@PathVariable(value = "pedido") Long id_pedido) throws Exception {
    //     pedidoService.deleteItensDoPedido(id_pedido);
    //     return ResponseEntity.status(HttpStatus.OK).body("Itens do pedido deletado com sucesso");
    // }

    // Delete no pedido inteiro
    @DeleteMapping("/pedido/{pedido}")
    public ResponseEntity<String> deletePedido(@PathVariable(value = "pedido") Long id_pedido) {
        pedidoService.deletePedidoById(id_pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Deleção do pedido efetuada com sucesso");
    }
}
