package Controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public ImageView close;
    @FXML
    public ComboBox cb_tipo;
    @FXML
    public TextField txt_user;
    @FXML
    public TextField txt_id;
    @FXML
    public TextField txt_nome;
    @FXML
    public TextField txt_email;
    @FXML
    public PasswordField txt_senha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fill_comboBox();
        check_type();
        txt_email.setVisible(false);
    }
    @FXML
    public void Close(MouseEvent Event) {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    public void back(MouseEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Stage register = (Stage) close.getScene().getWindow();
            register.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Register(ActionEvent actionEvent) {
        if(txt_id.getText().equals("")||txt_nome.getText().equals("")||txt_senha.getText().equals("")||txt_user.getText().equals("")||txt_email.getText().equals(""))
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Preencha todos os campos");
            aviso.setHeaderText(null);
            aviso.setContentText("Para se registrar, preencha todos os campos.");
            aviso.show();
        }
        else
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                Stage register = (Stage) close.getScene().getWindow();
                register.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void fill_comboBox()
    {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Candidato");
        tipos.add("Empresa");
        cb_tipo.setItems(tipos);
    }
    public void check_type()
    {
        cb_tipo.valueProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (s1.equals("Candidato"))
                {
                    txt_email.setText("");
                    txt_email.setVisible(true);
                }
                if (s1.equals("Empresa"))
                {
                    txt_email.setText("None");
                    txt_email.setVisible(false);
                }
            }
        });
    }
}
