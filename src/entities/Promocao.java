package entities;

public class Promocao {

    private Produto produto;
    private String dataValidade;
    private int quantidade;
    private double precoOriginal;
    private double valorPromocional;

    public Promocao(Produto produto, String dataValidade, int quantidade, double precoOriginal, double valorPromocional){
        this.produto = produto;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.precoOriginal = precoOriginal;
        this.valorPromocional = valorPromocional;
    }

    public Produto getProduto(){
        return produto;
    }

    public String getDataValidade(){
        return dataValidade;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public double getPrecoOriginal(){
        return precoOriginal;
    }

    public double getValorPromocional(){
        return valorPromocional;
    }

    @Override
    public String toString() {
        return "Promocao{" +
                "produto=" + produto +
                ", dataValidade='" + dataValidade + '\'' +
                ", quantidade=" + quantidade +
                ", precoOriginal=" + precoOriginal +
                ", valorPromocional=" + valorPromocional +
                '}';
    }
}
