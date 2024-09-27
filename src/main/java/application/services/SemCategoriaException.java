package application.services;

public class SemCategoriaException extends Exception {

    private int codigoErro;

    public SemCategoriaException(String message, int codigoErro) {
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
