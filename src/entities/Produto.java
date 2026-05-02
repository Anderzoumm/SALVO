package entities;

public class Produto {

    private String nome;
    private String descricao;

    public Produto (String nome, String descricao){
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
