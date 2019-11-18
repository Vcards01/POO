package DataBase;

import Model.Candidato;
import Model.Empresa;
import Model.Usuario;
import Sample.Criptografia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class usuarioDAO {

    private Connection connection;
    public usuarioDAO(){

    }

    public void create(Usuario u) {
        connection = new DataBase().getConnection();
        if( u instanceof Candidato)
        {

            String sql = "INSERT INTO candidato(cpf,nome,email,usuario,senha) " + "VALUES (?, ?, ?, ?, ?);";

            try {

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getIdentificador());
                stmt.setString(2, u.getNome());
                stmt.setString(3, ((Candidato)u).getEmail());
                stmt.setString(4, u.getUser());
                stmt.setString(5, Criptografia.Converter(u.getSenha()));
                stmt.execute();
                stmt.close();
                connection.close();
                System.out.println("Gravado!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (u instanceof Empresa)
        {
            String sql = "INSERT INTO empresa(cnpj,nome,usuario,senha) " + "VALUES (?, ?, ?, ?);";

            try {

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getIdentificador());
                stmt.setString(2, u.getNome());
                stmt.setString(3, u.getUser());
                stmt.setString(4, Criptografia.Converter(u.getSenha()));
                stmt.execute();
                stmt.close();
                connection.close();
                System.out.println("Gravado!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Usuario read(String id) {
        try {
            connection = new DataBase().getConnection();
            Candidato c = null;
            String sql = "SELECT * FROM candidato WHERE cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                c = new Candidato(rs.getString("usuario"), Criptografia.Reverter(rs.getString("senha")), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"));
            }
            if (c != null) {
                return c;
            } else {
                try {
                    Empresa emp = null;
                    sql = "SELECT * FROM empresa WHERE cnpj = ?";
                    stmt = connection.prepareStatement(sql);
                    stmt.setString(1, id);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        emp = new Empresa(rs.getString("usuario"), Criptografia.Reverter(rs.getString("senha")), rs.getString("nome"), rs.getString("cnpj"));
                    }
                    stmt.close();
                    connection.close();
                    return emp;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {

        }
        return null;
    }
    public void update(Usuario u) {
        connection = new DataBase().getConnection();
        if ( u instanceof Candidato)
        {
            String sql = "UPDATE candidato SET nome = ?, email = ?, usuario = ?, senha = ?" +
                    " WHERE cpf = ?";
            try {

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getNome());
                stmt.setString(2, ((Candidato)u).getEmail());
                stmt.setString(3, u.getUser());
                stmt.setString(4, Criptografia.Converter(u.getSenha()));
                stmt.setString(5, u.getIdentificador());
                stmt.execute();
                stmt.close();
                connection.close();
                System.out.println("Gravado!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(u instanceof Empresa)
        {
            String sql = "UPDATE empresa SET nome = ?, usuario = ?, senha = ?" +
                    " WHERE cnpj = ?";

            try {

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getUser());
                stmt.setString(3, Criptografia.Converter(u.getSenha()));
                stmt.setString(4, u.getIdentificador());
                stmt.execute();
                stmt.close();
                connection.close();
                System.out.println("Gravado!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void delete(Usuario u) {
        connection = new DataBase().getConnection();

        if(u instanceof Candidato)
        {
            try {
                String sql = "DELETE FROM candidato WHERE cpf = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getIdentificador());
                stmt.execute();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(u instanceof Empresa)
        {
            try {
                String sql = "DELETE FROM empresa WHERE cnpj = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, u.getIdentificador());
                stmt.execute();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public ArrayList<Usuario> getCandidatos() {
        connection = new DataBase().getConnection();

        try {
            String sql = "SELECT * FROM candidato";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Usuario> candidatos= new ArrayList<>();

            while (rs.next()) {
               Candidato c = new Candidato(rs.getString("usuario"),Criptografia.Reverter(rs.getString("senha")),rs.getString("nome"),rs.getString("cpf"),rs.getString("email"));
               candidatos.add(c);
            }
            rs.close();
            stmt.close();
            connection.close();
            return candidatos;

        }
        catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Usuario> getEmpresas() {
        connection = new DataBase().getConnection();

        try {
            String sql = "SELECT * FROM empresa";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Usuario> empresas= new ArrayList<>();

            while (rs.next()) {
                Empresa emp = new Empresa(rs.getString("usuario"),Criptografia.Reverter(rs.getString("senha")),rs.getString("nome"),rs.getString("cnpj"));
                empresas.add(emp);
            }
            rs.close();
            stmt.close();
            connection.close();
            return empresas;

        }
        catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Usuario> getUsuarios() {
        try {
            ArrayList<Usuario> usuarios= new ArrayList<>();
            usuarios.addAll(getCandidatos());
            usuarios.addAll(getEmpresas());
            connection.close();
            return usuarios;
        }
        catch (Exception e) {

        }
        return null;
    }
}
