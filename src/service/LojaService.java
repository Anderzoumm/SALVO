package service;

import entities.Loja;
import entities.Produto;
import entities.Promocao;

public class LojaService {

    Loja loja;

    public LojaService(Loja loja) {
        this.loja = loja;
    }

    public String CadastrarProduto(String nome, String descricao){
        String codigo = "PROD" + (loja.getEstoque().size() + 1);
        Produto produto = new Produto(codigo,nome,descricao,null);
        loja.adiconarProduto(produto);
        return "Produto: " +
                produto.getNome() +
                " cadastrado com sucesso"; }

    public String CadastrarPromoção(Produto produto,String validade,Double valornovo,Double valorvelho,int quantidade){
        if(valornovo >= valorvelho){
            return "Valor Novo é IGUAL ou Maior que o Valor Original";
        }
        Promocao promoção = new Promocao(produto,validade,quantidade,valorvelho,valornovo);
        loja.adicionarPromocao(promoção);
        return "Promoção Cadastrada Com Sucesso";

    }
}
