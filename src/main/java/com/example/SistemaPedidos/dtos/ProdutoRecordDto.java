package com.example.SistemaPedidos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRecordDto(@NotBlank String nome, @NotBlank String categoria,@NotNull double valor) {

}
