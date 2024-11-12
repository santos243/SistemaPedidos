package com.example.SistemaPedidos.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRecordDto(@NotNull Long idUsuario,
        List<ItemPedidoRecordDto> itens) {

}
