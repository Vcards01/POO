package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
