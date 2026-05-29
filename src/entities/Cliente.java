package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    //atributos
    private int id;
    private String endereco;
    private List<Pedido> historico = new ArrayList<>() ;


    //metodos
    public Cliente(String nome, String telefone, String email, String senha) {
        super(nome, telefone, email, senha);
    }

    public Pedido comprar(Promocao promocao, int quantidade) {

        Pedido pedido = new Pedido(this);

        pedido.adicionarPromocao(promocao, quantidade);
        historico.add(pedido);

        return pedido;
    }

    public List<Pedido> getPedidos() {
        return historico;
    }
}