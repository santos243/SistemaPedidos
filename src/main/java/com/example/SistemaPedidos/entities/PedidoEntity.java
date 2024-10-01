package com.example.SistemaPedidos.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoEntity implements Serializable {
//Pedido faz a relação entre ItemPedido e Usuario em uma tabela

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id_pedido;

    @ManyToOne
    private UsuarioEntity usuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedidoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ItemPedidoEntity> itens;

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Set<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoEntity> itens) {
        this.itens = itens;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PedidoEntity() {
    }

    public PedidoEntity(Long id_pedido) {
        this.id_pedido = id_pedido;
    }


}
