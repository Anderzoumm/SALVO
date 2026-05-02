package service;

import entities.Cliente;
import entities.Pedido;
import entities.Promocao;

public class ClienteService {

    Cliente cliente;

    public ClienteService(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido Comprar(Promocao promocao,int quantidade){
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida");
        }

        return cliente.comprar(promocao,quantidade);
    }
}
