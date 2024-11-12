package com.example.SistemaPedidos.dtos;

import jakarta.validation.constraints.NotNull;

public record ItemPedidoRecordDto(@NotNull Long idProduto,
                @NotNull int quantidade) {

}
