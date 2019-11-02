package Model;

public class Candidato extends Usuario {

    private String email;

    public Candidato(String user, String senha, String nome, String identificador,String email)
    {
        super(user, senha, nome, identificador);
        this.email=email;
    }

    public String getEmail() {
        return email;
    }
}
