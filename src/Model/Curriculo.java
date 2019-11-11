package Model;

public class Curriculo {
    private int id;
    private Candidato candidato;
    private String nome_candidato;
    private String cpf;
    private String nascimento;
    private String experiencia;
    private Boolean curso;
    private String nome_curso;
    private int cod_curso;

    public Curriculo(Candidato candidato, String nome_candidato, String cpf, String nascimento, String experiencia, Boolean curso, String nome_curso, int cod_curso) {
        this.candidato=candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.nome_curso = nome_curso;
        this.cod_curso = cod_curso;
        this.curso = curso;
    }

    public Curriculo(Candidato candidato, String nome_candidato, String cpf, String nascimento, String experiencia, Boolean curso) {
        this.candidato=candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.curso = curso;
        this.nome_curso = "-";
        this.cod_curso = 0;
    }

    public Curriculo(int id, Candidato candidato, String nome_candidato, String cpf, String nascimento, String experiencia, Boolean curso) {
        this.id = id;
        this.candidato = candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.curso = curso;
    }

    public Curriculo(int id, Candidato candidato, String nome_candidato, String cpf, String nascimento, String experiencia, Boolean curso, String nome_curso, int cod_curso) {
        this.id = id;
        this.candidato = candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.curso = curso;
        this.nome_curso = nome_curso;
        this.cod_curso = cod_curso;
    }

    public int getId() {
        return id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public String getNome_candidato() {
        return nome_candidato;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public Boolean getCurso() {
        return curso;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public int getCod_curso() {
        return cod_curso;
    }
}
