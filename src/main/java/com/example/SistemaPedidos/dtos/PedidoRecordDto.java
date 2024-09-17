package com.example.SistemaPedidos.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.List;


public record PedidoRecordDto(@NotNull Long id_usuario, List<ItemPedidoRecordDto> itemPedidoRecordDto) {
    
}