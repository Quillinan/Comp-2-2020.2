import java.awt.*;

public class Selo implements Colecionavel{

    private final Image imagem;
    private final int posicao;
    private String pais;
    private float valorNominal;

    public Selo(int posicao, String urlDaImagem) {
        this.imagem = obterImagem(urlDaImagem);
        this.posicao = posicao;
    }

    public void setImagem(String pais){
        this.pais = pais;
    }
    public void setValorNominal(float valor){
        this.valorNominal = valor;
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    public Image getImagem(){
        return imagem;
    }
    public int getPosicao(){
        return posicao;
    }

}
