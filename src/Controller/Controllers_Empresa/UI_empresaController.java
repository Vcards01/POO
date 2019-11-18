package Controller.Controllers_Empresa;

import Model.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_empresaController implements Initializable {
    @FXML public AnchorPane btn_sair;
    @FXML public AnchorPane panel_principal;
    private Empresa e ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void get_user(Empresa e)
    {
        this.e=e;

    }

    @FXML
    public void OpenHome(MouseEvent event) throws IOException{

    }
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaVagas.fxml"));
        AnchorPane pane = loader.load();
        UI_empresaVagasController controller = loader.getController();
        controller.start(e,panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
          panel_principal.getChildren().setAll(pane);
    }
    @FXML
    public void Close(MouseEvent Event) {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
            Stage login = new Stage();
            Scene scene = new Scene(root);
            login.initStyle(StageStyle.UNDECORATED);
            login.setScene(scene);
            login.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
