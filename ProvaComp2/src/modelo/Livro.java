package modelo;

public class Livro<titulo> {

    private String titulo;
    private String autor;
    private final int anoDePublicacao;


    public Livro(String titulo, String autor, int anoDePublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAnoDePublicacao() {
        return this.anoDePublicacao;
    }
}
