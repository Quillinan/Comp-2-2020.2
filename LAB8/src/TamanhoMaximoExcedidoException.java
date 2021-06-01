public class TamanhoMaximoExcedidoException extends Exception {

    private int tamanhoTexto;

    public TamanhoMaximoExcedidoException(int tamanhoTexto){
        this.tamanhoTexto = tamanhoTexto;
    }

    public int getTamanhoTexto() {
        return this.tamanhoTexto;
    }
}
