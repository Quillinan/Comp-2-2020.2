import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {

    private int id;

    private List<Usuario> amigos;

    private CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id, CalculadorIntersecao calculador) {
        // instancia um calculador de interseção
        this.id = id;
        this.calculadorIntersecao = calculador;
    }

    public int getId() {
        return id;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    public void gerarListaDeAmigos(int n, int cont)
    {
        List<Usuario> listaDeAmigos = new ArrayList<>();
        Usuario usuario;
        for(int i=this.id + 1; i <= n; i += cont)
        {
            usuario = new Usuario(i, new CalculadorIntersecaoViaBuscaBinaria());
            listaDeAmigos.add(usuario);
        }
        this.amigos = listaDeAmigos;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }
}
