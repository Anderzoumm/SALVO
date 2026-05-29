package entities;

public class Item {

    private Promocao promocao;
    private int quantidade;
    private double subtotal;

    public Item(Promocao promocao, int quantidade) {
        this.promocao = promocao;
        this.quantidade = quantidade;
        this.subtotal = promocao.getValorPromocional() * quantidade;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "Item{produto=" + promocao.getProduto().getNome() +
                ", quantidade=" + quantidade +
                ", subtotal=" + subtotal + '}';
    }
}