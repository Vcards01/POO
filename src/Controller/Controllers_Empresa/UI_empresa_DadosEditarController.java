package Controller.Controllers_Empresa;

import DataBase.usuarioDAO;
import Model.Empresa;
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

public class UI_empresa_DadosEditarController implements Initializable {
    //Botão para salvar os dados da empresa
    @FXML public Button btn_salvar;
    //Campos para os dados
    @FXML public TextField txt_nome;
    @FXML public TextField txt_id;
    @FXML public TextField txt_email;
    //Variaveis normais
    private Empresa e;
    private UI_empresaDadosController controller;
    private usuarioDAO DAO = new usuarioDAO();

    @Override//Incia o view
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_id.setEditable(false);
    }
    //Inicia algumas coisas
    public void start(Empresa e, UI_empresaDadosController controller)
    {
        this.e=e;
        this.controller=controller;
        txt_nome.setText(e.getNome());
        txt_email.setVisible(false);
        txt_id.setText(e.getIdentificador());
    }
    //Salva os dados
    @FXML
    public void save(ActionEvent event)
    {
        if(txt_id.getText().equals("")||txt_nome.getText().equals(""))
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Preencha todos os campos");
            aviso.setHeaderText(null);
            aviso.setContentText("Para se registrar, preencha todos os campos.");
            aviso.show();
        }
        else
        {
            Empresa em = new Empresa(e.getUser(),e.getSenha(),txt_nome.getText(),txt_id.getText());
            DAO.update(em);
            controller.reload(em);
            Stage close=(Stage)btn_salvar.getScene().getWindow();
            close.close();
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
