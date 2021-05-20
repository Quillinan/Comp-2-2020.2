import java.util.ArrayList;
import java.util.List;

public class Repositorio<C extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<C> todasAsFigurinhas;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas) {
        todasAsFigurinhas = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            C fig = (C)(new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens));//Tive que fazer typecast aqui :/
            todasAsFigurinhas.add(fig);
        }
    }

    public int getTotalFigurinhas() {
        return this.todasAsFigurinhas.size();
    }

    public C getFigurinha(int numeroFigurinha){
        return this.todasAsFigurinhas.get(numeroFigurinha-1);
    }
}
