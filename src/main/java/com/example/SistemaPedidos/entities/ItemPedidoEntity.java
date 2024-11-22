package com.example.SistemaPedidos.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity implements Serializable {
    // item pedido deve ter referencia ao pedido e ao produto, alem da quantidade.

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemPedido;

    @ManyToOne
    @JoinColumn(name = "pedido_entity_id_pedido")
    @JsonBackReference
    private PedidoEntity pedidoEntity;

    @ManyToOne
    private ProdutoEntity produtoEntity;

    private int quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(PedidoEntity pedidoEntity, ProdutoEntity produtoEntity, int quantidade) {
        this.pedidoEntity = pedidoEntity;
        this.produtoEntity = produtoEntity;
        this.quantidade = quantidade;
    }

    public ItemPedidoEntity(ProdutoEntity produtoEntity, int quantidade) {
        this.produtoEntity = produtoEntity;
        this.quantidade = quantidade;
    }

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long id_itemPedido) {
        this.idItemPedido = id_itemPedido;
    }

    public ProdutoEntity getProdutoEntity() {
        return produtoEntity;
    }

    public void setProdutoEntity(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
    }

    public PedidoEntity getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(PedidoEntity pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
