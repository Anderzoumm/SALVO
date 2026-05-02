package service;

import entities.Loja;
import entities.Pedido;
import entities.Produto;
import entities.Promocao;

public class LojaService {

    Loja loja;

    public LojaService(Loja loja) {
        this.loja = loja;
    }

    public String CadastrarProduto(String Nome,String Descrição){
        Produto pedido = new Produto(Nome,Descrição);
        loja.adiconarProduto(pedido);
        return "Produto: " + pedido.getNome() + "Cadastrado Com Sucesso";
    }

    public String CadastrarPromoção(Produto produto,String validade,Double valornovo,Double valorvelho,int quantidade){
        if(valornovo >= valorvelho){
            return "Valor Novo é IGUAL ou Maior que o Valor Original";
        }
        Promocao promoção = new Promocao(produto,validade,quantidade,valorvelho,valorvelho);
        loja.adicionarPromocao(promoção);
        return "Promoção Cadastrada Com Sucesso";

    }
}
