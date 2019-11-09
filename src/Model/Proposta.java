package Model;

public class Proposta {
    private Candidato candidato;
    private Vaga vaga;
    private String status;

    public Proposta(Candidato candidato, Vaga vaga, String status) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.status = status;
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
