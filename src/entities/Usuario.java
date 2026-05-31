package entities;

public abstract class Usuario {
    private String nome;
    private String telefone;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;

    }

    public boolean login(String email, String senha) {
        if (email.equals(this.email) && senha.equals(this.senha)) {
            return true;
        }
        return false;
    }
    public void AtualizarDados(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senha.equals(senhaAtual)) {
            this.senha = novaSenha;
            return true;
        }
        return false;
    }

    public  String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }

}
