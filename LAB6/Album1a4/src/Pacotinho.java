import java.util.Random;

public class Pacotinho {

    private final Repositorio repositorioPacote;
    private final int[] figurinhas;
    private static final Random aleatorio = new Random();

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repositorioPacote = repo;
        this.figurinhas = posicoesDesejadas;
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
        this.repositorioPacote = repo;
        this.figurinhas = new int[quantFigurinhas];
        for(int x = 0; x < quantFigurinhas; x++){
            int figurinha = aleatorio.nextInt(repo.getTotalFigurinhas());
            this.figurinhas[x] = figurinha+1;
        }
    }

    public Figurinha[] getFigurinhas() {
        Figurinha[] figurinhas = new Figurinha[this.figurinhas.length];
        for(int x = 0; x < this.figurinhas.length; x++){
            int numero = this.figurinhas[x];
            Figurinha fig = this.repositorioPacote.getFigurinha(numero);
            figurinhas[x] = fig;
        }
        return figurinhas;
    }
}
