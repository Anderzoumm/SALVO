package entities;

import java.util.ArrayList;
import java.util.List;

public class Loja extends Usuario {

    private String endereço;
    private String cnpj;

    private List<Produto> estoque = new ArrayList<>();
    private List<Promocao> vitrine = new ArrayList<>();

    public Loja() {
    }
    public Loja(String nome, String telefone, String email, String senha, String cnpj, String endereço) {
        super(nome, telefone, email, senha);
        this.cnpj = cnpj;
        this.endereço = endereço;
    }

    public String getNome(){
        return super.getNome();
    }

    public String getEndereco(){
        return endereço;
    }

    public String getTelefone(){
        return super.getTelefone();
    }

    public String getCnpj(){
        return cnpj;
    }

    public List<Produto> getEstoque() {
        return estoque;
    }

    public List<Promocao> getVitrine() {
        return vitrine;
    }

    public void adiconarProduto(Produto produto){
        estoque.add(produto);
    }

    public void adicionarPromocao(Promocao promocao){
        vitrine.add(promocao);
    }
}
