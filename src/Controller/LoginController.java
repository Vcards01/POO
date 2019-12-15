package Controller;

import Controller.Controllers_Candidato.UI_candidatoController;
import Controller.Controllers_Empresa.UI_empresaController;
import DataBase.usuarioDAO;
import Model.Candidato;
import Model.Empresa;
import Model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML public ImageView close;
    @FXML public TextField txt_user;
    @FXML public PasswordField txt_senha;
    @FXML public Label txt_incorreto;
    private usuarioDAO dao = new usuarioDAO();
    private ArrayList<Usuario>candidatos;
    private ArrayList<Usuario>empresas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        candidatos=dao.getCandidatos();
        empresas=dao.getEmpresas();
        txt_incorreto.setVisible(false);
    }

    @FXML
    public void Login(ActionEvent event) throws IOException {
        for (Usuario u : candidatos) {

            if (txt_user.getText().equals(u.getUser())) {
                if (txt_senha.getText().equals(u.getSenha())) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/View_Candidato/UI_candidato.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    UI_candidatoController controller = loader.getController();
                    controller.start((Candidato) u);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setFullScreen(true);
                    stage.show();
                    Stage login = (Stage) close.getScene().getWindow();
                    login.close();
                } else {
                    txt_incorreto.setVisible(true);
                }
            } else {
                txt_incorreto.setVisible(true);
            }
        }
        for (Usuario u : empresas) {

            if (txt_user.getText().equals(u.getUser())) {
                if (txt_senha.getText().equals(u.getSenha())) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresa.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    UI_empresaController controller=loader.getController();
                    controller.start((Empresa) u);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setFullScreen(true);
                    stage.show();
                    Stage login = (Stage) close.getScene().getWindow();
                    login.close();
                } else {
                    txt_incorreto.setVisible(true);
                }
            } else {
                txt_incorreto.setVisible(true);
            }
        }
    }
    @FXML
    public void Close(MouseEvent Event)
    {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    public void Register(MouseEvent Event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Stage login = (Stage)close.getScene().getWindow();
            login.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
