package Controller;

import DataBase.Tables;
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
    @FXML
    public ImageView close;
    public TextField txt_user;
    public PasswordField txt_senha;
    private usuarioDAO dao = new usuarioDAO();
    private ArrayList<Usuario>usuarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios=dao.getUsuarios();
    }

    public void Login(ActionEvent event)
    {
        try
        {
            for (Usuario u:usuarios) {
                if (txt_user.getText().equals(u.getUser()))
                {
                    if (txt_senha.getText().equals(u.getSenha()))
                    {
                        if (u instanceof Candidato)
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato.fxml"));
                            Parent root = loader.load();
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setFullScreen(true);
                            stage.show();
                            Stage login = (Stage)close.getScene().getWindow();
                            login.close();
                        }
                        else if(u instanceof Empresa)
                        {
                            System.out.println("Empresa");
                        }
                    }
                }

            }

        }catch (Exception e)
        {

        }

    }
    public void Close(MouseEvent Event)
    {
        Platform.exit();
        System.exit(0);
    }
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
