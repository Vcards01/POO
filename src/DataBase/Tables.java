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
        table_proposta();
        table_curriculo();
    }
    private static void table_candidato()
    {
        String table = "CREATE TABLE IF NOT EXISTS candidato(cpf VARCHAR(12)," +
                "nome VARCHAR(30)," +
                "email VARCHAR(60)," +
                "usuario VARCHAR(30)," +
                "senha VARCHAR(30)," +
                "PRIMARY KEY('cpf'));";
        String data_1="INSERT OR IGNORE INTO candidato(cpf,nome,email,usuario,senha)VALUES('123123123123','victor','vhcardoso30@gmail.com','usuario','" +Criptografia.Converter("123")+ "');";
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
        String data_1="INSERT OR IGNORE INTO empresa(cnpj,nome,usuario,senha)VALUES('00000000000','No Name S/A','empresa','" +Criptografia.Converter("123")+ "');";
        String data_2="INSERT OR IGNORE INTO empresa(cnpj,nome,usuario,senha)VALUES('11111111111','Facebook','Facebook','" +Criptografia.Converter("facebook")+ "');";
        String data_3="INSERT OR IGNORE INTO empresa(cnpj,nome,usuario,senha)VALUES('22222222222','Google','Google','" +Criptografia.Converter("google")+ "');";
        String data_4="INSERT OR IGNORE INTO empresa(cnpj,nome,usuario,senha)VALUES('33333333333','Apple','Apple','" +Criptografia.Converter("apple")+ "');";


        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.addBatch(data_1);
            stmt.addBatch(data_2);
            stmt.addBatch(data_3);
            stmt.addBatch(data_4);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void table_vaga()
    {
        String table = "CREATE TABLE IF NOT EXISTS vaga(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "nome VARCHAR(30),"+
                "salario FLOAT," +
                "horario DATE," +
                "nVagas INTEGER," +
                "descricao VARCHAR(300)," +
                "area VARCHAR(30)," +
                "subarea VARCHAR(30)," +
                "empresa VARCHAR(30)," +
                "status VARCHAR(30)," +
                "FOREIGN KEY(empresa) REFERENCES empresa(cnpj));";
        String data_1="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar no desenvolvimento de sites',1,1200,'08:00',5,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Desenvolvimento Web','00000000000','Livre');";
        String data_2="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar no desenvolvimento de aplicações',2,1250,'06:00',4,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Desenvolvimento Mobile','00000000000','Livre');";
        String data_3="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar no desenvolvimento de sites',3,3200,'08:00',3,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Desenvolvimento Web','00000000000','Livre');";
        String data_4="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar no desenvolvimento de Programas',4,1400,'05:00',2,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Desenvolvimento Desktop','00000000000','Livre');";
        String data_6="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar na Analise de software',5,12000,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Engenharia de software','00000000000','Livre');";
        String data_7="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Gerenciar funcionarios da empresa',6,1000,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','Administração','RH','11111111111','Livre');";
        String data_8="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Gerenciar projetos na empresa',7,3000,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','Administração','Gerenciamento de projetos','11111111111','Livre');";
        String data_9="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Desenvolver e lidar com componentes eletricos',8,1500,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','Engenharia Eletrica','Eletronica','22222222222','Livre');";
        String data_10="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar na Analise de software',9,1200,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Engenharia de software','22222222222','Livre');";
        String data_11="INSERT OR IGNORE INTO vaga(nome,id,salario,horario,nVagas,descricao,area,subarea,empresa,status)VALUES('Trabalhar na Analise de software',10,17000,'09:00',1,'Ser responsavel,Ter experiecia na area,disponibilidade para viagens','TI','Engenharia de software','33333333333','Livre');";

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.addBatch(data_1);
            stmt.addBatch(data_2);
            stmt.addBatch(data_3);
            stmt.addBatch(data_4);
            stmt.addBatch(data_6);
            stmt.addBatch(data_7);
            stmt.addBatch(data_8);
            stmt.addBatch(data_9);
            stmt.addBatch(data_10);
            stmt.addBatch(data_11);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void table_proposta() {
        String table = "CREATE TABLE IF NOT EXISTS proposta(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "candidato VARCHAR(40)," +
                "vaga INTEGER," +
                "status VARCHAR(20)," +
                "notifica_emp VARCHAR(20)," +
                "notifica_user VARCHAR(20)," +
                "FOREIGN KEY(vaga) REFERENCES vaga(id)," +
                "FOREIGN KEY(candidato) REFERENCES candidato(cpf));";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void table_curriculo() {
        String table = "CREATE TABLE IF NOT EXISTS curriculo(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "candidato VARCHAR(40)," +
                "nome VARCHAR(40)," +
                "cpf VARCHAR(40)," +
                "nascimento VARCHAR(40)," +
                "experiencia VARCHAR(400)," +
                "curso VARCHAR(10)," +
                "nomeCurso VARCHAR(40)," +
                "idCurso INTEGER," +
                "FOREIGN KEY(candidato) REFERENCES candidato(cpf));";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.addBatch(table);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
