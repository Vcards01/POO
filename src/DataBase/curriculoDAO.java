package DataBase;

import Model.Candidato;
import Model.Curriculo;
import Model.Proposta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class curriculoDAO {
    private Connection connection;
    private usuarioDAO userDAO = new usuarioDAO();

    public void create(Curriculo c) {

        connection = new DataBase().getConnection();
        String sql = "INSERT OR IGNORE INTO curriculo(candidato,nome,cpf,nascimento,experiencia,curso,nomeCurso,idCurso) " + "VALUES (?, ?, ?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getCandidato().getIdentificador());
            stmt.setString(2, c.getNome_candidato());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getNascimento());
            stmt.setString(5, c.getExperiencia());
            stmt.setString(6, c.getCurso().toString());
            if (c.getCurso()) {
                stmt.setString(7, c.getNome_curso());
                stmt.setInt(8, c.getCod_curso());
            } else {
                stmt.setString(7, "-");
                stmt.setInt(8, 0);
            }
            stmt.execute();
            stmt.close();
            connection.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Curriculo read(int id) {
        try {
            connection = new DataBase().getConnection();
            Curriculo c = null;
            String sql = "SELECT * FROM curriculo WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                c = new Curriculo(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")), rs.getString("nome"), rs.getString("cpf"), rs.getString("nascimento"), rs.getString("experiencia"), rs.getBoolean("curso"), rs.getString("nomeCurso"), rs.getInt("idCurso"));
            }
            connection.close();
            return c;
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return null;
    }
    public Curriculo read(Candidato can) {
        try {
            connection = new DataBase().getConnection();
            Curriculo c = null;
            String sql = "SELECT * FROM curriculo WHERE candidato = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, can.getIdentificador());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                c = new Curriculo(rs.getInt("id"), (Candidato) userDAO.read(rs.getString("candidato")), rs.getString("nome"), rs.getString("cpf"), rs.getString("nascimento"), rs.getString("experiencia"), Boolean.parseBoolean(rs.getString("curso")), rs.getString("nomeCurso"), rs.getInt("idCurso"));
            }
            connection.close();
            return c;
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return null;
    }
    public void update(Curriculo c) {
        connection = new DataBase().getConnection();
        String sql = "UPDATE curriculo SET candidato=?,nome=?,cpf=?,nascimento=?,experiencia=?,curso=?,nomeCurso=?,idCurso=?" +
                " WHERE id= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getCandidato().getIdentificador());
            stmt.setString(2, c.getNome_candidato());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getNascimento());
            stmt.setString(5, c.getExperiencia());
            stmt.setString(6, c.getCurso().toString());
            if (c.getCurso()) {
                stmt.setString(7, c.getNome_curso());
                stmt.setInt(8, c.getCod_curso());
            } else {
                stmt.setString(7, "-");
                stmt.setInt(8, 0);
            }
            stmt.setInt(9,c.getId());
            stmt.execute();
            stmt.close();
            connection.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Curriculo c) {
        connection = new DataBase().getConnection();
        try {
            String sql = "DELETE FROM curriculo WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
