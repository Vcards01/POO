package Controller;

import DataBase.usuarioDAO;
import Model.Candidato;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidatoDadosController implements Initializable {

    public AnchorPane panel_dados;
    public Label txt_nome;
    public Label txt_email;
    public Label txt_cpf;
    private Candidato c;
    private usuarioDAO DAO = new usuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void get_user(Candidato c)
    {
        this.c= (Candidato) DAO.read(c.getIdentificador());
        txt_nome.setText(c.getNome());
        txt_cpf.setText(c.getIdentificador());
        txt_email.setText(c.getEmail());
    }
    public void reload(Candidato can)
    {
        this.c= (Candidato) DAO.read(can.getIdentificador());
        txt_nome.setText(c.getNome());
        txt_cpf.setText(c.getIdentificador());
        txt_email.setText(c.getEmail());
    }
    public void set_medidas(Double h , Double w)
    {
       panel_dados.setPrefHeight(h);
       panel_dados.setPrefWidth(w);
    }
    public void Open_editar_dados(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_vagasEditar.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidato_DadosEditarController controller = loader.getController();
        controller.get_user(c);
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
