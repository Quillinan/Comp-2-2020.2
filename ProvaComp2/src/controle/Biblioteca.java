package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Biblioteca {

    /** quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
        que o livro possa ser emprestado */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /** quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    private static Set<Usuario> usuarioSet;
    private static HashMap<Long, Usuario> usuarioByCPF;
    private static Map<Livro, Integer> quantidadeByLivro;


    public Biblioteca() {
        usuarioSet = new HashSet<>();
        usuarioByCPF = new HashMap<>();
        quantidadeByLivro = new HashMap<>();
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if(usuarioSet.size() == 0){
            usuarioSet.add(usuario);
            usuarioByCPF.put(usuario.getCpf(), usuario);
        }
        else{
            if(usuarioSet.contains(usuario)){
                getUsuario(usuario.getCpf()).setNome(usuario.getNome());
                getUsuario(usuario.getCpf()).setEndereco(usuario.getEndereco());
            }
            else{
                usuarioSet.add(usuario);
                usuarioByCPF.put(usuario.getCpf(), usuario);
            }
        }
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
        return usuarioByCPF.getOrDefault(cpf, null);
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public int getTotalDeUsuariosCadastrados() {
        return usuarioSet.size();
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
        if(quantidadeByLivro.size() == 0){
            quantidadeByLivro.put(livro, quantidade);
        }
        else{
            if(quantidadeByLivro.containsKey(livro)){
                int quantidadeAnterior = quantidadeByLivro.get(livro);
                quantidadeByLivro.put(livro, quantidade + quantidadeAnterior);
            }
            else{
                quantidadeByLivro.put(livro, quantidade);
            }
        }
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     *
     * @return true, se o empréstimo foi bem-sucedido;
     *         false, se o livro não está disponível para empréstimo
     *         (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     *                      isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     *
     * @throws UsuarioNaoCadastradoException se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        if(!usuarioSet.contains(usuario)){
            throw new UsuarioNaoCadastradoException();
        }
        if(!quantidadeByLivro.containsKey(livro)||quantidadeByLivro.get(livro)< MIN_COPIAS_PARA_PODER_EMPRESTAR){
            return false;
        }
        if(usuario.quantidadeAlugados() == Biblioteca.MAX_LIVROS_DEVIDOS){
                throw new LimiteEmprestimosExcedidoException();
        }
        usuario.setLivroAlugado(livro);
        int quantidadeAntiga = quantidadeByLivro.get(livro);
        quantidadeByLivro.put(livro, quantidadeAntiga-1);
        return true;
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     *
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
        if(!usuario.temLivro(livro)){
            throw new DevolucaoInvalidaException();
        }
        else{
            usuario.devolverLivro(livro);
            int quantidadeAnterior = quantidadeByLivro.get(livro);
            quantidadeByLivro.put(livro, quantidadeAnterior+1);
        }
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     *         ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
        return usuario.quantidadeAlugados();
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     *         não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {
        int quantidadeLivros = 0;
        for(Livro livro: quantidadeByLivro.keySet()){
            quantidadeLivros+=quantidadeByLivro.get(livro);
        }
        return quantidadeLivros;
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
        return quantidadeByLivro.getOrDefault(livro,0);
    }


}
