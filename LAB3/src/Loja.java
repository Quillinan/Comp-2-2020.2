import java.util.ArrayList;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();
    private ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
    private ArrayList<Integer> listaDeQuantidade = new ArrayList<Integer>();
    private int quantidade = 0;
    private ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    protected Loja() {
        // escrevo código normalmente para o construtor
    }

    public static Loja getInstanciaUnica()
    {
        return instanciaUnica;
    }

    public void limparEstado() {
        // recria os atributos, ou os limpa...
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir)
    {
        if(produto.getQuantidadeEmEstoque()==-1) {
            listaDeProdutos.add(produto);
            listaDeQuantidade.add(quantidadeAIncluir);
            produto.setQuantidadeEmEstoque(quantidade);
            quantidade++;
        }
        else{
            int id = produto.getQuantidadeEmEstoque();
            int qntAtual = listaDeQuantidade.get(id);
            listaDeQuantidade.set(id,qntAtual+quantidadeAIncluir);
        }
    }

    public void cadastrarUsuario(Usuario usuario)
    {
        listaDeUsuarios.add(usuario);
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario)
    {
        if (listaDeUsuarios.contains(usuario) && listaDeProdutos.contains(produto))
        {
            if(quantidadeDesejada <= listaDeQuantidade.get(produto.getQuantidadeEmEstoque()))
            {
                float precoTotal = produto.getPrecoEmReais() * quantidadeDesejada;
                Recibo recibo = new Recibo(usuario, precoTotal, produto);
                listaDeQuantidade.set(produto.getQuantidadeEmEstoque(), listaDeQuantidade.get(produto.getQuantidadeEmEstoque()) - quantidadeDesejada);
                return recibo;
            }
        }
        return null;
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto)
    {
        int id = produto.getQuantidadeEmEstoque();
        return listaDeQuantidade.get(id);
    }
}
