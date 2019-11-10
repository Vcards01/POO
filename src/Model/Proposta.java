package Model;

public class Proposta {
    private int id;
    private Candidato candidato;
    private Vaga vaga;
    private String status;

    public Proposta(Candidato candidato, Vaga vaga, String status) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
    }

    public Proposta(int id, Candidato candidato, Vaga vaga, String status) {
        this.id = id;
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
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
}
