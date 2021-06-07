package modelo;

import controle.Biblioteca;
import excecao.LimiteEmprestimosExcedidoException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Usuario {

    private String nome;
    private final long cpf;
    private String endereco;
    private Map<Livro, Integer> livrosAlugados;

    public Usuario(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
        livrosAlugados = new HashMap<>();

    }

    public long getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean temLivro(Livro livro){
        if (livrosAlugados.size() == 0){
            return false;
        }
        return livrosAlugados.containsKey(livro);
    }

    public int quantidadeAlugados() {
        int quantidadeLivros = 0;
        if (livrosAlugados.size() == 0) {
            return 0;
        }
        for (Livro livro : livrosAlugados.keySet()) {
            quantidadeLivros += livrosAlugados.get(livro);
        }
        return quantidadeLivros;
    }

    public void setLivroAlugado(Livro livro){
        if(livrosAlugados.size() == 0){
            livrosAlugados.put(livro, 1);
        }
        else{
            if(livrosAlugados.containsKey(livro)){
                int quantidadeAntiga = livrosAlugados.get(livro);
                livrosAlugados.put(livro, quantidadeAntiga+1);
            }
            else{
                livrosAlugados.put(livro, 1);
            }
        }
    }

    public void devolverLivro(Livro livro){
        livrosAlugados.remove(livro);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
