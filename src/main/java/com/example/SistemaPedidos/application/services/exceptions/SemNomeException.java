package com.example.SistemaPedidos.application.services.exceptions;

public class SemNomeException extends Exception{

    private int codigoErro;

    public SemNomeException(String mensagem, int codigoErro){
        super(mensagem);
        this.codigoErro = codigoErro;
    }


    public int getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
    }

}
