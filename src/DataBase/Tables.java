package DataBase;
import Sample.Criptografia;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.*;

public class Tables {
    private static Connection connection=new DataBase().getConnection();
    private Path path = Paths.get("database.db");

    public static void CreateTables()
    {
        table_candidato();
        table_empresa();
        table_vaga();
    }
    private static void table_candidato()
    {
        String table = "CREATE TABLE IF NOT EXISTS candidato(cpf VARCHAR(12)," +
                "nome VARCHAR(30)," +
                "email VARCHAR(60)," +
                "usuario VARCHAR(30)," +
                "senha VARCHAR(30)," +
                "PRIMARY KEY('cpf'));";
        String data_1="INSERT OR IGNORE INTO candidato(cpf,nome,email,usuario,senha)VALUES('47560617883','victor','vhcardoso30@gmail.com','vcards1','" +Criptografia.Converter("victor123")+ "');";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.addBatch(data_1);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void table_empresa()
    {
        String table = "CREATE TABLE IF NOT EXISTS empresa(cnpj VARCHAR(12)," +
                "nome VARCHAR(30)," +
                "usuario VARCHAR(30)," +
                "senha VARCHAR(30)," +
                "PRIMARY KEY('cnpj'));";
        String data_1="INSERT OR IGNORE INTO empresa(cnpj,nome,usuario,senha)VALUES('00000000000','IFSP campus são carlos','IFSP','" +Criptografia.Converter("ifspsaocarlos")+ "');";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.addBatch(data_1);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void table_vaga()
    {
        String table = "CREATE TABLE IF NOT EXISTS vaga(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "salario FLOAT," +
                "horario DATE," +
                "nVagas INTEGER," +
                "descricao VARCHAR(300)," +
                "area VARCHAR(30)," +
                "subarea VARCHAR(30)," +
                "empresa VARCHAR(30)," +
                "FOREIGN KEY(empresa) REFERENCES empresa(cnpj));";
        String data_1="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(1,1200,'08:00',5,'Tabalahar no desenvolvimento de sites','TI','Desenvolvimento Web','00000000000');";
        String data_2="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(2,1250,'06:00',4,'Tabalahar no desenvolvimento de aplicações','TI','Desenvolvimento Mobile','00000000000');";
        String data_3="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(3,3200,'08:00',3,'Tabalahar no desenvolvimento de sites','TI','Desenvolvimento web','00000000000');";
        String data_4="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(4,1400,'05:00',2,'Tabalahar no desenvolvimento de Programas','TI','Desenvolvimento Desktop','00000000000');";
        String data_5="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(5,1500,'08:00',5,'Tabalahar no desenvolvimento de Web Crawlers','TI','Desenvolvimento Web','00000000000');";
        String data_6="INSERT OR IGNORE INTO vaga(id,salario,horario,nVagas,descricao,area,subarea,empresa)VALUES(6,12000,'09:00',1,'Tabalahar na Analise de software','TI','Analise de sistemas','00000000000');";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.addBatch(data_1);
            stmt.addBatch(data_2);
            stmt.addBatch(data_3);
            stmt.addBatch(data_4);
            stmt.addBatch(data_5);
            stmt.addBatch(data_6);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
