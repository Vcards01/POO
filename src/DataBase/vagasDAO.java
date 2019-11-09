package DataBase;

import Model.Candidato;
import Model.Empresa;
import Model.Usuario;
import Model.Vaga;
import Sample.Criptografia;

import java.sql.*;
import java.util.ArrayList;

public class vagasDAO {
    private Connection connection;
    private usuarioDAO UserDAO=new usuarioDAO();
    public vagasDAO(){

    }

    public void create(Vaga v) {

        connection = new DataBase().getConnection();
        String sql = "INSERT OR IGNORE INTO vaga(salario,horario,nVagas,descricao,area,subarea,empresa) " + "VALUES (?, ?, ?,?, ?, ?,?,?);";
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(2, v.getSalario());
            stmt.setString(3, v.getHorario());
            stmt.setInt(4, v.getNum_vagas());
            stmt.setString(5, v.getDescricao());
            stmt.setString(6, v.getArea());
            stmt.setString(7, v.getSubArea());
            stmt.setString(8, v.getEmpresa().getIdentificador());
            stmt.execute();
            stmt.close();
            connection.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Vaga read(int id) {
        try {
            connection = new DataBase().getConnection();
            Vaga v = null;
            String sql = "SELECT * FROM vaga WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
               v= new Vaga((Empresa) UserDAO.read(rs.getString("empresa")),rs.getString("descricao"),rs.getInt("id"),rs.getDouble("salario"),rs.getString("horario"),rs.getInt("nVagas"),rs.getString("area"),rs.getString("subarea"));
            }
            connection.close();
            return v;
        } catch (SQLException e) {
          e.getErrorCode();
        }
        return null;
    }
    public void update(Vaga v) {
        connection = new DataBase().getConnection();
        String sql = "UPDATE vaga SET salario=?,horario=?,nVagas=?,descricao=?,area=?,subarea=?,empresa=?" +
                    " WHERE id= ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, v.getSalario());
            stmt.setString(2, v.getHorario());
            stmt.setInt(3, v.getNum_vagas());
            stmt.setString(4, v.getDescricao());
            stmt.setString(5, v.getArea());
            stmt.setString(6, v.getSubArea());
            stmt.setString(7, v.getEmpresa().getIdentificador());
            stmt.setInt(8,v.getId());;
            stmt.execute();
            stmt.close();
            System.out.println("Gravado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Vaga v) {
        connection = new DataBase().getConnection();
        try {
            String sql = "DELETE FROM vaga WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, v.getId());
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<String> get_area()
    {
        connection = new DataBase().getConnection();
        try {
            String sql = "SELECT area FROM vaga";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> areas= new ArrayList<>();
            while (rs.next()) {
                String s = rs.getString("area");
                if(areas.contains(s))
                {
                    continue;
                }
                else
                {
                    areas.add(s);
                }
            }
            areas.add("ALL");
            return areas;
        }catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> get_subarea()
    {
        connection = new DataBase().getConnection();
        try {
            String sql = "SELECT subarea FROM vaga";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> subareas= new ArrayList<>();
            while (rs.next()) {
                String s = rs.getString("subarea");
                if(subareas.contains(s))
                {
                    continue;
                }
                else
                {
                    subareas.add(s);
                }
            }
            subareas.add("ALL");
            return subareas;
        }catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> get_subarea(String area)
    {
        if (area.equals("ALL"))
        {
            return get_subarea();
        }
        else
        {
            connection = new DataBase().getConnection();
            try {
                String sql = "SELECT subarea FROM vaga where area=?";
                PreparedStatement stmt = this.connection.prepareStatement(sql);
                stmt.setString(1, area);
                ResultSet rs = stmt.executeQuery();
                ArrayList<String> subareas= new ArrayList<>();
                while (rs.next()) {
                    String s = rs.getString("subarea");
                    if(subareas.contains(s))
                    {
                        continue;
                    }
                    else
                    {
                        subareas.add(s);
                    }
                }
                subareas.add("ALL");
                return subareas;
            }catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
            return null;
        }

    }

    public ArrayList<Vaga> getVagas() {
        connection = new DataBase().getConnection();

        try {
            String sql = "SELECT * FROM vaga";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Vaga> vagas= new ArrayList<>();

            while (rs.next()) {
               Vaga v= new Vaga((Empresa) UserDAO.read(rs.getString("empresa")),rs.getString("descricao"),rs.getInt("id"),rs.getDouble("salario"),rs.getString("horario"),rs.getInt("nVagas"),rs.getString("area"),rs.getString("subarea"));
               vagas.add(v);
            }
            rs.close();
            stmt.close();
            connection.close();
            return vagas;

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
    public ArrayList<Vaga> getVagas(String area)
    {
        if(area.equals("ALL"))
        {
            return getVagas();
        }
        else
        {
            connection = new DataBase().getConnection();

            try {
                String sql = "SELECT * FROM vaga where area = ?";
                PreparedStatement stmt = this.connection.prepareStatement(sql);
                stmt.setString(1, area);
                ResultSet rs = stmt.executeQuery();

                ArrayList<Vaga> vagas= new ArrayList<>();

                while (rs.next()) {
                    Vaga v= new Vaga((Empresa) UserDAO.read(rs.getString("empresa")),rs.getString("descricao"),rs.getInt("id"),rs.getDouble("salario"),rs.getString("horario"),rs.getInt("nVagas"),rs.getString("area"),rs.getString("subarea"));
                    vagas.add(v);
                }
                rs.close();
                stmt.close();
                connection.close();
                return vagas;

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

    }
    public ArrayList<Vaga> get_by_subArea(String subarea,String area)
    {
        if(subarea.equals("ALL"))
        {
            return getVagas(area);
        }
        else
        {
            connection = new DataBase().getConnection();

            try {
                String sql = "SELECT * FROM vaga where subarea= ?";
                PreparedStatement stmt = this.connection.prepareStatement(sql);
                stmt.setString(1, subarea);
                ResultSet rs = stmt.executeQuery();

                ArrayList<Vaga> vagas= new ArrayList<>();

                while (rs.next()) {
                    Vaga v= new Vaga((Empresa) UserDAO.read(rs.getString("empresa")),rs.getString("descricao"),rs.getInt("id"),rs.getDouble("salario"),rs.getString("horario"),rs.getInt("nVagas"),rs.getString("area"),rs.getString("subarea"));
                    vagas.add(v);
                }
                rs.close();
                stmt.close();
                connection.close();
                return vagas;

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

    }


}
