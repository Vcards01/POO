package Controller.Controllers_Empresa;

import DataBase.curriculoDAO;
import Model.Candidato;
import Model.Curriculo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UI_empresaVerCurriculoController implements Initializable {
    public Label txt_nome;
    public Label txt_cpf;
    public Label txt_nascimento;
    public Label txt_experiencia;
    public Label label_curso;
    public Label txt_curso;
    private curriculoDAO DAO = new curriculoDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void start(Candidato c)
    {
        Curriculo curriculo = DAO.read(c);
        txt_nome.setText(curriculo.getNome_candidato());
        txt_cpf.setText(curriculo.getCpf());
        txt_experiencia.setText(curriculo.getExperiencia());
        txt_nascimento.setText(curriculo.getNascimento());
        txt_curso.setText(curriculo.getNome_curso());
        if (!curriculo.getCurso())
        {
            txt_curso.setVisible(false);
            label_curso.setVisible(false);
        }
        else
        {
            txt_curso.setVisible(true);
            txt_curso.setText(curriculo.getNome_curso());
            label_curso.setVisible(true);
        }
    }
    @FXML
    public void Close(MouseEvent Event) {
        Stage login = (Stage)txt_curso.getScene().getWindow();
        login.close();
    }
}
