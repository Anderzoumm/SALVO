package entities;
public class Produto {

    private String codigo;
    private Categoria categoria;
    private String nome;
    private String descricao;
    public Produto() {
    }
    public Produto(String codigo, String nome, String descricao, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome(){
        return nome;
    }
    public String getDescricao(){
        return descricao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
