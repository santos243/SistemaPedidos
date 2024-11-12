package com.example.SistemaPedidos.application.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.SistemaPedidos.dtos.repositories.ProdutoRepository;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepository;
}
