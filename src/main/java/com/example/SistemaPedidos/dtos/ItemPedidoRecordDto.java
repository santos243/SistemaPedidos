package com.example.SistemaPedidos.dtos;


import jakarta.validation.constraints.NotNull;

public record ItemPedidoRecordDto(@NotNull Long id_produto, @NotNull int quantidade) {

}
