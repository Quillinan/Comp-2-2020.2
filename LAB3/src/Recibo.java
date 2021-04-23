public class Recibo {

    private Usuario usuario;
    private float valorDaCompra;
    private Produto produto;

    public Recibo(Usuario usuario, float valorDaCompra, Produto produto) {
        this.usuario = usuario;
        this.valorDaCompra = valorDaCompra;
        this.produto = produto;
    }

    public float getValorTotalDaCompra() {
        return valorDaCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String toString() {
        return String.format("Recibo no valor de R$%.2f para %s referente à compra de %d unidades de %s",
                valorDaCompra, usuario.getNome(), (int) (valorDaCompra / produto.getPrecoEmReais()), produto.getDescricao());
    }
}

