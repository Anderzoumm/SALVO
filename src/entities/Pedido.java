package entities;

import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private List<Promocao> promocoes;
    private double valorTotal;
    private Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.promocoes = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarPromocao(Promocao promocao, double valortotal) {
        promocoes.add(promocao);
        valorTotal += valortotal;
    }

    public void finalizar() {
            System.out.println("Pedido finalizado!");

    }

    public void cancelar() {
        System.out.println("Pedido Cancelado!");


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


}