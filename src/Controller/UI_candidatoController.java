package Controller;

import DataBase.vagasDAO;
import Model.Vaga;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UI_candidatoController implements Initializable {
    @FXML
    public AnchorPane barra_1;
    @FXML
    public AnchorPane barra_2;
    @FXML
    public AnchorPane barra_3;
    @FXML
    public AnchorPane barra_4;
    @FXML
    public AnchorPane barra_5;
    @FXML
    public AnchorPane btn_vagas;
    @FXML
    public AnchorPane btn_curriculo;
    @FXML
    public AnchorPane btn_perfil;
    @FXML
    public AnchorPane btn_sair;
    @FXML
    public AnchorPane btn_home;
    public TableView table_semResposta;
    public TableColumn collum_vagas_semResposta;
    public TableColumn collum_empresa_semResposta;
    public TableColumn collum_salario_semResposta;
    public AnchorPane header_sem_resposta;
    public AnchorPane header_positiva;
    public AnchorPane header_negativa;
    public AnchorPane panel_principal;
    public TableView table_positiva;
    public TableView table_negativa;
    private vagasDAO vagasDAO = new vagasDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barra_1.setVisible(false);
        barra_2.setVisible(false);
        barra_3.setVisible(false);
        barra_4.setVisible(false);
        barra_5.setVisible(false);
        details();
    }
    public ObservableList<Vaga> getVagas()
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(vagasDAO.getVagas());
        System.out.println(vagas.get(0).getEmpresa());
        return vagas;
    }
    @FXML
    public void Close(MouseEvent Event) {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
            Stage login = new Stage();
            Scene scene = new Scene(root);
            login.initStyle(StageStyle.UNDECORATED);
            login.setScene(scene);
            login.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OpenHome(MouseEvent event) throws IOException{
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(header_sem_resposta,table_semResposta,header_negativa,table_negativa,header_positiva,table_positiva);
    }
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_vagas.fxml"));
            AnchorPane pane = loader.load();
            UI_candidadoVagasController controller = loader.getController();
            controller.set_medidas(panel_principal.getHeight(),panel_principal.getWidth());
            panel_principal.getChildren().clear();
            panel_principal.getChildren().setAll(pane);
    }
    private void details()
    {
       btn_home.setOnMouseEntered(event -> { barra_1.setVisible(true);});
       btn_home.setOnMouseExited(event -> { barra_1.setVisible(false);});
       btn_vagas.setOnMouseEntered(event -> { barra_2.setVisible(true);});
       btn_vagas.setOnMouseExited(event -> { barra_2.setVisible(false);});
       btn_curriculo.setOnMouseEntered(event -> { barra_3.setVisible(true);});
       btn_curriculo.setOnMouseExited(event -> { barra_3.setVisible(false);});
       btn_perfil.setOnMouseEntered(event -> { barra_4.setVisible(true);});
       btn_perfil.setOnMouseExited(event -> { barra_4.setVisible(false);});
       btn_sair.setOnMouseEntered(event -> { barra_5.setVisible(true);});
       btn_sair.setOnMouseExited(event -> { barra_5.setVisible(false);});
    }

}
