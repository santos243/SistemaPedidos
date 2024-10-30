package com.example.SistemaPedidos.application.services.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaPedidos.application.services.ProdutoService;
import com.example.SistemaPedidos.dtos.ProdutoRecordDto;
import com.example.SistemaPedidos.entities.ProdutoEntity;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoEntity> addProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto)
            throws Exception {
        var produto = produtoService.addProduto(produtoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/produtos/{idProduto}")
    public ResponseEntity<ProdutoEntity> getProduto(@PathVariable(value = "idProduto") Long id_produto)
            throws Exception {
        var produtoEncontrado = produtoService.findProdutoById(id_produto);
        return ResponseEntity.status(HttpStatus.OK).body(produtoEncontrado);
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoEntity>> getAllProdutos() throws Exception {
        var produtos = produtoService.getAllProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @PutMapping("/produtos/{idProduto}")
    public ResponseEntity<ProdutoEntity> updateProduto(@PathVariable(value = "idProduto") Long id_produto,
            @RequestBody @Valid ProdutoRecordDto produtoRecordDto) throws Exception {
        var produto = produtoService.updateProduto(id_produto, produtoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/produtos/{id_produto}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id_produto")Long id_produto) throws Exception {
        produtoService.deleteProdutoById(id_produto);
        return ResponseEntity.status(HttpStatus.OK).body("Deleção efetuada com sucesso");
    }
}
