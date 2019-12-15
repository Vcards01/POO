package Controller.Controllers_Candidato;

import DataBase.usuarioDAO;
import Model.Candidato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidato_DadosEditarSenhaController implements Initializable {
    //Botão para salvar a senha
    @FXML public Button btn_salvar;
    //Password fields para as senhas
    @FXML public PasswordField txt_SenhaVelha;
    @FXML public PasswordField txt_NovaSenha;
    @FXML public PasswordField txt_Nova_senhaAgain;
    //Labels de aviso
    @FXML public Label txt_senhaIncorreta;
    @FXML public Label txt_naoConfere;
    //Variaveis normais
    private usuarioDAO userDAO = new usuarioDAO();
    private Candidato c;

    @Override//Inicia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //Inicia algumas coisas
    public void start(Candidato c) {
        txt_naoConfere.setVisible(false);
        txt_senhaIncorreta.setVisible(false);
        this.c =c;
    }

    //Salva a nova senha no banco
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
    //Fecha a aba
    @FXML
    public void Close(MouseEvent Event)
    {
        Stage close=(Stage)btn_salvar.getScene().getWindow();
        close.close();
    }

}
