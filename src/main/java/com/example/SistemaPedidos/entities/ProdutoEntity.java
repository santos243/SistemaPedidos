package com.example.SistemaPedidos.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id_produto;
    private String nome;
    private String categoria;
    private double valor;

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id_produto) {
        this.id_produto = id_produto;
    }

    public ProdutoEntity(Long id_produto, String nome, String categoria, double valor) {
        this.id_produto = id_produto;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }

}
