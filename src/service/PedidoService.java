package service;

import entities.Pedido;
import entities.Promocao;

public class PedidoService {

    Pedido pedido;

    public PedidoService(Pedido pedido) {
        this.pedido = pedido;
    }

    public String AdicionarPromoção(Promocao promocao, int quantidade){
        if (quantidade <= 0) {
            return "Quantidade inválida";
        }
        double valor = 0;
        for(int i = 0;i <= quantidade;i++){
            valor += promocao.getValorPromocional();
        }

        pedido.adicionarPromocao(promocao,valor);

        return "Foi Adicionado" + quantidade +"x da Promoção de" + promocao.getProduto().getNome();

    }
}
