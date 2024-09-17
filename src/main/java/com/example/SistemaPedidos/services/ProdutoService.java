package com.example.SistemaPedidos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SistemaPedidos.controllers.ProdutoController;
import com.example.SistemaPedidos.dtos.ProdutoRecordDto;
import com.example.SistemaPedidos.entities.ProdutoEntity;
import com.example.SistemaPedidos.repositories.ProdutoRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    //metodo find
    public ProdutoEntity findProdutoById(Long id_produto) throws Exception {
        var produto = produtoRepository.findById(id_produto).orElseThrow(() -> new Exception(" "));
        return produto;
    }
    //metodo deleteById
    public void deleteProdutoById(Long id_produto) {
        produtoRepository.deleteById(id_produto);
    }
    //metodo adicionar produto
    public ProdutoEntity addProduto(ProdutoRecordDto produtoRecordDto) {
        var produto = new ProdutoEntity();
        produto.setNome(produtoRecordDto.nome());
        produto.setValor(produtoRecordDto.valor());
        produto.setCategoria(produtoRecordDto.categoria());
        return produtoRepository.save(produto);
    }
    //metodos get para todos e para um
    public List<ProdutoEntity> getAllProdutos() throws Exception {
        var produtos = produtoRepository.findAll();
        if (!produtos.isEmpty()) {
            for (ProdutoEntity produto : produtos) {
                Long id_produto = produto.getId_produto();
                produto.add(linkTo(methodOn(ProdutoController.class).getProduto(id_produto)).withSelfRel());
            }
        }
        return produtos;
    }
    
    /**
     * Este metodo sevre para atualizar produtos, porém os pedidos ficarao desatualizados
     * @param id_produto
     * @param produtoRecordDto
     * @return
     * @throws Exception 
     */
    public ProdutoEntity updateProduto(Long id_produto, ProdutoRecordDto produtoRecordDto) throws Exception {
        var produto = findProdutoById(id_produto);
        produto.setNome(produtoRecordDto.nome());
        produto.setCategoria(produtoRecordDto.categoria());
        produto.setValor(produtoRecordDto.valor());
        return produtoRepository.save(produto);
    }

}
