package com.example.SistemaPedidos.application.services.exceptions;

public class ProdutoNaoEncontradoException extends Exception {

    private int codigoErro;

    public ProdutoNaoEncontradoException(String message, int codigoErro) {
        super(message);
        this.codigoErro = codigoErro;
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
    }

}
