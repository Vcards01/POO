package Model;

import java.util.ArrayList;

public class Vaga {

    private Empresa empresa;
    private String descricao;
    private String nome;
    private int id;
    private double salario;
    private String horario;
    private int num_vagas;
    private String area;
    private String subArea;
    ArrayList<Candidato>candidatos=new ArrayList<>();

    public Vaga(Empresa empresa, String descricao, int id, double salario, String horario, int num_vagas, String area, String subArea, String nome) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.id = id;
        this.salario = salario;
        this.horario = horario;
        this.num_vagas = num_vagas;
        this.area = area;
        this.subArea = subArea;
        this.nome=nome;
    }

    public Vaga(Empresa empresa, String descricao, String nome, double salario, String horario, int num_vagas, String area, String subArea) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.nome = nome;
        this.salario = salario;
        this.horario = horario;
        this.num_vagas = num_vagas;
        this.area = area;
        this.subArea = subArea;
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

    public String getHorario() {
        return horario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getArea() {
        return area;
    }

    public String getSubArea() {
        return subArea;
    }

    public int getNum_vagas() {
        return num_vagas;
    }

    public String getNome() {
        return nome;
    }

    public String toString()
    {
        return getNome();
    }

    public void setNum_vagas(int num_vagas) {
        this.num_vagas = num_vagas;
    }
}
