package com.example.SistemaPedidos.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_clientes")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String cpf;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long id_usuario) {
        this.idUsuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id_usuario, String nome, String cpf) {
        this.idUsuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
    }

}
