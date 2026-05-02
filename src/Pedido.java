import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Promocao> promocoes;
    private double valorTotal;
    private Cliente cliente;
    private String status;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.promocoes = new ArrayList<>();
        this.valorTotal = 0.0;
        this.status = "PENDENTE";
    }

    public void adicionarPromocao(Promocao promocao, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida");
            return;
        }

        for (int i = 0; i < quantidade; i++) {
            promocoes.add(promocao);
            valorTotal += promocao.getValorPromocional();
        }
    }

    public void finalizar() {
        if (status.equals("PENDENTE")) {
            System.out.println("Pedido finalizado!");
            this.status = "FINALIZADO";
        }
    }

    public void cancelar() {
        if (status.equals("PENDENTE")) {
            System.out.println("Pedido cancelado!");
            this.status = "CANCELADO";
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public String getStatus() {
        return status;
    }
}