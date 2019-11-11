package Controller;

import DataBase.curriculoDAO;
import Model.Candidato;
import Model.Curriculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidatoCurriculoController implements Initializable {
    public AnchorPane pn_curriculo;
    public AnchorPane curriculo;
    public Button btn_criar;
    public Button btn_editar;
    public Label txt_nome;
    public Label txt_cpf;
    public Label txt_nascimento;
    public Label txt_experiencia;
    public Label txt_curso;
    public Label label_curso;
    private Curriculo cur;
    private Candidato c;
    private curriculoDAO DAO = new curriculoDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void set_medidas(Double h , Double w)
    {
        pn_curriculo.setPrefHeight(h);
        pn_curriculo.setPrefWidth(w);
    }
    public void get_user(Candidato c)
    {
        this.c=c;
        if (currilo_exist())
        {
            btn_criar.setVisible(false);
            txt_nome.setText(cur.getNome_candidato());
            txt_cpf.setText(cur.getCpf());
            txt_experiencia.setText(cur.getExperiencia());
            txt_nascimento.setText(cur.getNascimento());
            txt_curso.setText(cur.getNome_curso());
            if (!cur.getCurso())
            {
                txt_curso.setVisible(false);
                label_curso.setVisible(false);
            }
            else
            {
                txt_curso.setVisible(true);
                txt_curso.setText(cur.getNome_curso());
                label_curso.setVisible(true);
            }
        }
        else
        {
            btn_editar.setVisible(false);
        }
    }
    public void reload()
    {
        if (currilo_exist())
        {
            btn_criar.setVisible(false);
            txt_nome.setText(cur.getNome_candidato());
            txt_cpf.setText(cur.getCpf());
            txt_experiencia.setText(cur.getExperiencia());
            txt_nascimento.setText(cur.getNascimento());
            txt_curso.setText(cur.getNome_curso());
            if (!cur.getCurso())
            {
                txt_curso.setVisible(false);
                label_curso.setVisible(false);
            }
            else
            {
                txt_curso.setVisible(true);
                txt_curso.setText(cur.getNome_curso());
                label_curso.setVisible(true);
            }
        }
        else
        {
            btn_editar.setVisible(false);
        }
    }
    public boolean currilo_exist()
    {
        cur = DAO.read(c);
        if(cur==null)
        {
            return false;
        }
        return  true;
    }
    public void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_curriculoCreate.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidatoCurriculoCreateController controller = loader.getController();
        controller.get_user(c,false);
        controller.get_controller(this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_curriculoCreate.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidatoCurriculoCreateController controller = loader.getController();
        controller.get_user(c,true);
        controller.get_controller(this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
