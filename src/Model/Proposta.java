package Model;

import java.util.Date;

public class Proposta {

    private Empresa empresa;
    private String descricao;
    private int id;
    private double salario;
    private Date horario;
    private int num_vagas;

    public Proposta(Empresa empresa, String descricao, int id, double salario, Date horario, int num_vagas) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.id = id;
        this.salario = salario;
        this.horario = horario;
        this.num_vagas = num_vagas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public Date getHorario() {
        return horario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public int getNum_vagas() {
        return num_vagas;
    }
}
