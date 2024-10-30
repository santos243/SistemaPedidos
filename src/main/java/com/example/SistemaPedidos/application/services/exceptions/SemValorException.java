package com.example.SistemaPedidos.application.services.exceptions;

public class SemValorException extends Exception {
    private String codigoErro;

    public SemValorException(String message, String codigoErro) {
        super(message);
        this.codigoErro = codigoErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

}
