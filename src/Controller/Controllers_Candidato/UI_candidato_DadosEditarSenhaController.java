package Controller.Controllers_Candidato;

import DataBase.usuarioDAO;
import Model.Candidato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidato_DadosEditarSenhaController implements Initializable {
    @FXML public Button btn_salvar;
    @FXML public TextField txt_SenhaVelha;
    @FXML public TextField txt_NovaSenha;
    @FXML public TextField txt_Nova_senhaAgain;
    @FXML public Label txt_senhaIncorreta;
    @FXML public Label txt_naoConfere;
    private usuarioDAO userDAO = new usuarioDAO();
    private Candidato c;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void start(Candidato c) {
        txt_naoConfere.setVisible(false);
        txt_senhaIncorreta.setVisible(false);
        this.c =c;
    }

    @FXML
    public void save(ActionEvent event) {
        System.out.println(txt_Nova_senhaAgain.getText());
        System.out.println(c.getSenha());
        if (txt_SenhaVelha.getText().equals(c.getSenha()))
        {
            txt_senhaIncorreta.setVisible(false);
            if(txt_NovaSenha.getText().equals(txt_Nova_senhaAgain.getText()))
            {
                Candidato can=new Candidato(c.getUser(),txt_NovaSenha.getText(),c.getNome(),c.getIdentificador(),c.getEmail());
                userDAO.update(can);
                Stage close=(Stage)btn_salvar.getScene().getWindow();
                close.close();
            }
            else
            {
                txt_naoConfere.setVisible(true);
            }
        }
        else
        {
            txt_senhaIncorreta.setVisible(true);
        }
    }
    @FXML
    public void Close(MouseEvent Event)
    {
        Stage close=(Stage)btn_salvar.getScene().getWindow();
        close.close();
    }

}
