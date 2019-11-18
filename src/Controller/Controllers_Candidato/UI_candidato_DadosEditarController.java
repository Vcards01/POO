package Controller.Controllers_Candidato;

import DataBase.usuarioDAO;
import Model.Candidato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidato_DadosEditarController implements Initializable {
    @FXML public Button btn_salvar;
    @FXML public TextField txt_nome;
    @FXML public TextField txt_id;
    @FXML public TextField txt_email;
    @FXML private Candidato c;
    @FXML private UI_candidatoDadosController controller;
    @FXML private usuarioDAO DAO = new usuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void start(Candidato c,UI_candidatoDadosController controller)
    {
        this.c=c;
        this.controller=controller;
        txt_nome.setText(c.getNome());
        txt_email.setText(c.getEmail());
        txt_id.setText(c.getIdentificador());
    }

    @FXML
    public void save(ActionEvent event)
    {
        if(txt_id.getText().equals("")||txt_nome.getText().equals("")||txt_email.getText().equals(""))
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Preencha todos os campos");
            aviso.setHeaderText(null);
            aviso.setContentText("Para se registrar, preencha todos os campos.");
            aviso.show();
        }
        else
        {
            Candidato can = new Candidato(c.getUser(),c.getSenha(),txt_nome.getText(),txt_id.getText(),txt_email.getText());
            DAO.update(can);
            controller.reload(can);
            Stage close=(Stage)btn_salvar.getScene().getWindow();
            close.close();
        }

    }
    @FXML
    public void Close(MouseEvent Event)
    {
        Stage close=(Stage)btn_salvar.getScene().getWindow();
        close.close();
    }
}
