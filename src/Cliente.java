import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Pedido> pedidos;

    public Cliente(String nome, String telefone, String email, String senha) {
        super(nome, telefone, email, senha);
        this.pedidos = new ArrayList<>();
    }

    public Pedido comprar(Promocao promocao, int quantidade) {

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida");
            //return null
        }

        Pedido pedido = new Pedido(this);

        pedido.adicionarPromocao(promocao, quantidade);
        pedidos.add(pedido);

        return pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}