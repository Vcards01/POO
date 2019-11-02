package Model;

public class Usuario {

    private String user;
    private String senha;
    private String nome;
    private String identificador;

    public Usuario(String user, String senha, String nome, String identificador) {
        this.user = user;
        this.senha = senha;
        this.nome = nome;
        this.identificador = identificador;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }
}
