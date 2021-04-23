public class Usuario {

    private final String nome;
    private final long cpf;
    private String endereco;

    public Usuario(String nome, long cpf, String endereco)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome()
    {
        return this.nome;
    }
}
