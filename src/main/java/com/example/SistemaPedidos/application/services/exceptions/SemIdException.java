package com.example.SistemaPedidos.application.services.exceptions;

public class SemIdException extends Exception {
    private int codigoErro;

    public SemIdException(String message, int codigoErro) {
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
