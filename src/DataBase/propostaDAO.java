package DataBase;

import Model.Candidato;
import Model.Empresa;
import Model.Proposta;
import Model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class propostaDAO {

    private Connection connection;
    private usuarioDAO userDAO = new usuarioDAO();
    private vagasDAO vagasDAO = new vagasDAO();
    public void create(Proposta p) {

        connection = new DataBase().getConnection();
        String sql = "INSERT OR IGNORE INTO proposta(candidato,vaga,status,notifica_emp,notifica_user) " + "VALUES (?, ?, ?,?,?);";
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getCandidato().getIdentificador());
            stmt.setInt(2, p.getVaga().getId());
            stmt.setString(3, p.getStatus());
            stmt.setBoolean(4,p.isNotifica_emp() );
            stmt.setBoolean(5, p.isNotifica_user());
            stmt.execute();
            stmt.close();
            connection.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Proposta read(int id) {
        try {
            connection = new DataBase().getConnection();
            Proposta p = null;
            String sql = "SELECT * FROM proposta WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                p=new Proposta(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")),vagasDAO.read(rs.getInt("id")),rs.getString("status"),rs.getBoolean("notifica_user"),rs.getBoolean("notifica_emp"));
            }
            connection.close();
            return p;
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return null;
    }
    public Proposta read(String candidato,int vaga) {
        try {
            connection = new DataBase().getConnection();
            Proposta p = null;
            String sql = "SELECT * FROM proposta WHERE candidato=? and vaga=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,candidato);
            stmt.setInt(2,vaga);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                p=new Proposta(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")),vagasDAO.read(rs.getInt("vaga")),rs.getString("status"),rs.getBoolean("notifica_user"),rs.getBoolean("notifica_emp"));
            }
            System.out.println(p.getEmail_candidato());
            connection.close();
            return p;
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return null;
    }
    public void update(Proposta p) {
        connection = new DataBase().getConnection();
        String sql = "UPDATE proposta SET candidato=?,vaga=?,status=?,notifica_emp=?,notifica_user=?" +
                " WHERE id= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getCandidato().getIdentificador());
            stmt.setInt(2, p.getVaga().getId());
            stmt.setString(3, p.getStatus());
            stmt.setBoolean(4, p.isNotifica_emp());
            stmt.setBoolean(5, p.isNotifica_user());
            stmt.setInt(6,p.getId());
            stmt.execute();
            stmt.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Proposta p) {
        connection = new DataBase().getConnection();
        try {
            String sql = "DELETE FROM Proposta WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Proposta> getPropostas(Empresa e,String status) {
        connection = new DataBase().getConnection();

        try {
            String sql = "SELECT p.id, p.candidato, p.vaga,p.status,p.notifica_emp,p.notifica_user  FROM proposta p JOIN vaga v on p.vaga=v.id JOIN empresa p on v.empresa=? AND p.status=? AND notifica_emp=0 GROUP BY p.id;";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1,e.getIdentificador());
            stmt.setString(2,status);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Proposta> propostas = new ArrayList<>();
            while (rs.next()) {
                Proposta p = new Proposta(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")), vagasDAO.read(rs.getInt("vaga")), rs.getString("status"),rs.getBoolean("notifica_user"),rs.getBoolean("notifica_emp"));
                propostas.add(p);
            }
            rs.close();
            stmt.close();
            connection.close();
            return propostas;

        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }
    public ArrayList<Proposta> getPropostas(Candidato c,String status) {
        connection = new DataBase().getConnection();

        try {
            String sql = "SELECT * FROM proposta where candidato=? AND status= ? AND notifica_user!=1";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1,c.getIdentificador());
            stmt.setString(2,status);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Proposta> propostas = new ArrayList<>();
            while (rs.next()) {
                Proposta p = new Proposta(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")), vagasDAO.read(rs.getInt("vaga")), rs.getString("status"),rs.getBoolean("notifica_user"),rs.getBoolean("notifica_emp"));
                propostas.add(p);
            }
            rs.close();
            stmt.close();
            connection.close();
            return propostas;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Proposta> getPropostas(Empresa e) {
        connection = new DataBase().getConnection();
        try {
            String sql ="SELECT * FROM proposta where status = 'Em espera' AND vaga in (SELECT id from vaga where empresa = ?);";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1,e.getIdentificador());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Proposta> propostas = new ArrayList<>();
            while (rs.next()) {
                Proposta p = new Proposta(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")), vagasDAO.read(rs.getInt("vaga")), rs.getString("status"),rs.getBoolean("notifica_user"),rs.getBoolean("notifica_emp"));
                propostas.add(p);
            }
            rs.close();
            stmt.close();
            connection.close();
            return propostas;
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;

    }

}