package Model;

public class Proposta {
    private int id;
    private Candidato candidato;
    private String email_candidato;
    private Vaga vaga;
    private int n_vagas;
    private String status;
    private boolean notifica_user;
    private boolean notifica_emp;

    public Proposta(Candidato candidato, Vaga vaga, String status,Boolean notifica_user,Boolean notifica_emp) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
        this.email_candidato=candidato.getEmail();
        this.n_vagas=vaga.getNum_vagas();
        this.notifica_user=notifica_user;
        this.notifica_emp=notifica_emp;
    }

    public Proposta(int id, Candidato candidato, Vaga vaga, String status,Boolean notifica_user,Boolean notifica_emp) {
        this.id = id;
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
        this.email_candidato=candidato.getEmail();
        this.n_vagas=vaga.getNum_vagas();
        this.notifica_user=notifica_user;
        this.notifica_emp=notifica_emp;
    }

    public int getId() {
        return id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail_candidato() {
        return email_candidato;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNotifica_user(boolean notifica_user) {
        this.notifica_user = notifica_user;
    }

    public void setNotifica_emp(boolean notifica_emp) {
        this.notifica_emp = notifica_emp;
    }

    public boolean isNotifica_user() {
        return notifica_user;
    }

    public boolean isNotifica_emp() {
        return notifica_emp;
    }

    public void setN_vagas(int n_vagas) {
        this.n_vagas = n_vagas;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getN_vagas() {
        return n_vagas;
    }
}
