import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {
    private Usuario usuario1;
    private Usuario usuario2;
    private List<Usuario> listaDeUsuarios1;
    private List<Usuario> listaDeUsuarios2;
    private CalculadorIntersecao calculador1;
    private CalculadorIntersecao calculador2;

    @Before
    public void setUp()
    {
        calculador1 = new CalculadorIntersecaoIngenuo();
        calculador2 = new CalculadorIntersecaoIngenuo();
        usuario1 = new Usuario(1, calculador1);
        usuario2 = new Usuario(2, calculador2);
        listaDeUsuarios1 = new ArrayList<>();
        listaDeUsuarios2 = new ArrayList<>();
    }

    @Test
    public void testarIntersecoes()
    {
        Usuario usuario3 = new Usuario(3,calculador1);
        listaDeUsuarios1.add(usuario3);
        listaDeUsuarios2.add(usuario3);
        Usuario usuario4 = new Usuario(4,calculador1);
        listaDeUsuarios1.add(usuario4);
        listaDeUsuarios2.add(usuario4);
        Usuario usuario5 = new Usuario(5,calculador2);
        listaDeUsuarios1.add(usuario5);
        listaDeUsuarios2.add(usuario5);
        Usuario usuario6 = new Usuario(6,calculador2);
        listaDeUsuarios1.add(usuario6);
        Usuario usuario7 = new Usuario(7,calculador2);
        listaDeUsuarios1.add(usuario7);
        Usuario usuario8 = new Usuario(8,calculador2);
        listaDeUsuarios1.add(usuario8);
        listaDeUsuarios2.add(usuario8);
        usuario1.setAmigos(listaDeUsuarios1);
        usuario2.setAmigos(listaDeUsuarios2);
        assertEquals(usuario1.obterQuantidadeDeAmigosEmComum(usuario2),usuario2.obterQuantidadeDeAmigosEmComum(usuario1));
        assertEquals(4,usuario1.obterQuantidadeDeAmigosEmComum(usuario2));
    }

    @Test
    public void testarTempoDeIntersecoes()
    {
        long inicio;
        long duracao;
        int quantidade1;
        int quantidade2;
        usuario1.gerarListaDeAmigos(1000, 1);
        usuario2.gerarListaDeAmigos(1000, 10);
        inicio = System.currentTimeMillis();
        quantidade1 = usuario1.obterQuantidadeDeAmigosEmComum(usuario2);
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo com o ingenuo = %.3f\n", duracao/1000f);
        inicio = System.currentTimeMillis();
        quantidade2 = usuario2.obterQuantidadeDeAmigosEmComum(usuario1);
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo com o esperto = %.3f\n", duracao/1000f);
        assertEquals(quantidade1, quantidade2);
    }
}
