package Model;

public class Proposta {
    private int id;
    private Candidato candidato;
    private String email_candidato;
    private Vaga vaga;
    private int n_vagas;
    private String status;

    public Proposta(Candidato candidato, Vaga vaga, String status) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
        this.email_candidato=candidato.getEmail();
        this.n_vagas=vaga.getNum_vagas();
    }

    public Proposta(int id, Candidato candidato, Vaga vaga, String status) {
        this.id = id;
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
        this.email_candidato=candidato.getEmail();
        this.n_vagas=vaga.getNum_vagas();
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
