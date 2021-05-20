import java.util.Random;

public class Pacotinho<C extends Colecionavel> {

    private final Repositorio<C> repositorioPacote;
    private final int[] figurinhas;
    private static final Random aleatorio = new Random();

    public Pacotinho(Repositorio<C> repo, int[] posicoesDesejadas) {
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
    public Pacotinho(Repositorio<C> repo, int quantFigurinhas) {
        this.repositorioPacote = repo;
        this.figurinhas = new int[quantFigurinhas];
        for(int x = 0; x < quantFigurinhas; x++){
            int C = aleatorio.nextInt(repo.getTotalFigurinhas());
            this.figurinhas[x] = C+1;
        }
    }

    public C[] getFigurinhas() {
        C[] figurinhas = (C[])(new Figurinha[this.figurinhas.length]);//Tive que fazer typecast aqui :/
        for(int x = 0; x < this.figurinhas.length; x++){
            int numero = this.figurinhas[x];
            C fig = this.repositorioPacote.getFigurinha(numero);
            figurinhas[x] = fig;
        }
        return figurinhas;
    }
}
