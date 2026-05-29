package entities;

public class Funcionario extends Usuario {

    private int id;
    private String cargo;
    private Loja loja;

    public Funcionario(int id, String nome, String telefone, String email, String senha, String cargo) {
        super(nome, telefone, email, senha);
        this.id = id;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Loja getLoja() {
        return loja;
    }

    // chamado internamente por Loja ao adicionar funcionário
    void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "Funcionario{id=" + id + ", nome='" + getNome() + "', cargo='" + cargo + "'}";
    }
}