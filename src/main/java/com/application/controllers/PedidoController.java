package com.application.controllers;

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

import com.application.entities.entities.PedidoEntity;
import com.application.services.services.PedidoService;
import com.example.SistemaPedidos.dtos.PedidoRecordDto;

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
     * Delete em todos os itens pedidos dentro de um pedido sem afetar o objeto
     * "PEDIDO"
     *
     * @param id_pedido
     * @param pedidoRecordDto
     * @return
     * @throws Exception
     */
    @PutMapping("/restaurarPedido/id/{pedido}")
    public ResponseEntity<String> addItemAoPedido(@PathVariable(value = "pedido") Long id_pedido,
            @RequestBody @Valid PedidoRecordDto pedidoRecordDto) throws Exception {
        pedidoService.addItensAoPedido(id_pedido, pedidoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido do usuario atualizado com sucesso");
    }

    // Delete no pedido inteiro
    @DeleteMapping("/pedido/id/{pedido}")
    public ResponseEntity<String> deletePedido(@PathVariable(value = "pedido") Long id_pedido) {
        pedidoService.deletePedidoById(id_pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Deleção do pedido efetuada com sucesso");
    }

}
