package entities;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Pedido {


    private String codigo;
    private List<Item> itens;
    private String data;
    private List<Promocao> promocoes;
    private double valorTotal;
    private String emailCliente;

    public Pedido() {
    }
    public Pedido(Cliente cliente) {
        this.emailCliente = cliente.getEmail();
        this.itens = new ArrayList<>();
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

    public String getEmailCliente() {
        return emailCliente;
    }
    public List<Promocao> getPromocoes() {
        return promocoes;
    }


}