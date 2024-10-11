package com.example.SistemaPedidos.application.services;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.SistemaPedidos.dtos.repositories.ProdutoRepository;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepository;
}
