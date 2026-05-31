package entities;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    //atributos
    private int id;
    private String endereco;


    public Cliente() {
    }
    //metodos
    public Cliente(String nome, String telefone, String email, String senha, String endereco) {
        super(nome, telefone, email, senha);
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}