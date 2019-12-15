package Controller.Controllers_Empresa;

import DataBase.usuarioDAO;
import Model.Empresa;
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

public class UI_empresa_DadosEditarSenhaController implements Initializable {
    //Botão para salvar a nova senha
    @FXML public Button btn_salvar;
    //Campos para as senhas
    @FXML public PasswordField txt_SenhaVelha;
    @FXML public PasswordField txt_NovaSenha;
    @FXML public PasswordField txt_Nova_senhaAgain;
    //Labem de erros
    @FXML public Label txt_senhaIncorreta;
    @FXML public Label txt_naoConfere;
    //Variaveis normais
    private usuarioDAO userDAO = new usuarioDAO();
    private Empresa e;

    @Override//Incia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    //Inicia algumas coisas
    public void start(Empresa e) {
        txt_naoConfere.setVisible(false);
        txt_senhaIncorreta.setVisible(false);
        this.e=e;
    }
    //Salva a senha nova
    @FXML
    public void save(ActionEvent event) {
        if (txt_SenhaVelha.getText().equals(e.getSenha()))
        {
            txt_senhaIncorreta.setVisible(false);
            if(txt_NovaSenha.getText().equals(txt_Nova_senhaAgain.getText()))
            {
                Empresa em=new Empresa(e.getUser(),txt_NovaSenha.getText(),e.getNome(),e.getIdentificador());
                userDAO.update(em);
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
