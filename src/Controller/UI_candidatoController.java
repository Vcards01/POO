package Controller;

import DataBase.propostaDAO;
import DataBase.vagasDAO;
import Model.Candidato;
import Model.Proposta;
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
import java.util.ArrayList;
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
    public TableColumn column_area_negativa;
    public TableColumn column_subarea_negativa;
    public TableColumn column_empresa_negativa;
    public TableColumn column_salario_negativa;
    public TableColumn column_area_semresposta;
    public TableColumn column_subarea_semresposta;
    public TableColumn column_empresa_semresposta;
    public TableColumn column_salario_semresposta;
    public TableColumn column_area_positivo;
    public TableColumn column_subarea_positivo;
    public TableColumn column_empresa_positivo;
    public TableColumn column_salario_positivo;
    private propostaDAO PropDAO = new propostaDAO();
    private vagasDAO vagasDAO = new vagasDAO();
    private Candidato c;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barra_1.setVisible(false);
        barra_2.setVisible(false);
        barra_3.setVisible(false);
        barra_4.setVisible(false);
        barra_5.setVisible(false);
        fill_table();
        details();
    }
    public ObservableList<Vaga> getPropostas(String status)
    {
        ArrayList<Proposta> list= PropDAO.getPropostas();
        ObservableList<Vaga>propostas=FXCollections.observableArrayList();
        for (Proposta p:list) {
            if (status.equals(p.getStatus()))
            {
                propostas.add(p.getVaga());
            }

        }
        return propostas;
    }
    public void fill_table()
    {
        column_area_semresposta.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_area_positivo.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_area_negativa.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_semresposta.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_subarea_positivo.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_subarea_negativa.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_semresposta.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_empresa_positivo.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_empresa_negativa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_semresposta.setCellValueFactory(new PropertyValueFactory<>("salario"));
        column_salario_positivo.setCellValueFactory(new PropertyValueFactory<>("salario"));
        column_salario_negativa.setCellValueFactory(new PropertyValueFactory<>("salario"));
        System.out.println("dasdasd");
        table_semResposta.setItems(getPropostas("Em espera"));
        table_positiva.setItems(getPropostas("Positivo"));
        table_negativa.setItems(getPropostas("Negativo"));
    }
    public void get_user(Candidato c)
    {
        this.c=c;
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
        panel_principal.getChildren().setAll(header_sem_resposta,header_negativa,header_positiva,table_semResposta,table_positiva,table_negativa);
        table_semResposta.getItems().removeAll();
        table_positiva.getItems().removeAll();
        table_negativa.getItems().removeAll();
        fill_table();
    }
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_vagas.fxml"));
            AnchorPane pane = loader.load();
            UI_candidadoVagasController controller = loader.getController();
            controller.set_medidas(panel_principal.getHeight(),panel_principal.getWidth());
            controller.get_user(c);
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
