package Model;

import java.util.Date;

public class Curriculo {

    private Candidato candidato;
    private String nome_candidato;
    private String cpf;
    private Date nascimento;
    private String experiencia;
    private String nome_curso;
    private int cod_curso;

    public Curriculo(Candidato candidato,String nome_candidato, String cpf, Date nascimento, String experiencia, String nome_curso, int cod_curso) {
        this.candidato=candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.nome_curso = nome_curso;
        this.cod_curso = cod_curso;
    }

    public Curriculo(Candidato candidato,String nome_candidato, String cpf, Date nascimento, String experiencia) {
        this.candidato=candidato;
        this.nome_candidato = nome_candidato;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.experiencia = experiencia;
        this.nome_curso = "-";
        this.cod_curso = Integer.parseInt(null);
    }
}
