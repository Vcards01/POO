package Controller;

import DataBase.propostaDAO;
import DataBase.vagasDAO;
import Model.Candidato;
import Model.Proposta;
import Model.Vaga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UI_candidatoController implements Initializable {
<<<<<<< HEAD
    @FXML public AnchorPane barra_1;
    @FXML public AnchorPane barra_2;
    @FXML public AnchorPane barra_3;
    @FXML public AnchorPane barra_4;
    @FXML public AnchorPane barra_5;
    @FXML public AnchorPane btn_vagas;
    @FXML public AnchorPane btn_curriculo;
    @FXML public AnchorPane btn_perfil;
    @FXML public AnchorPane btn_sair;
    @FXML public AnchorPane btn_home;
    @FXML public TableView table_semResposta;
    @FXML public AnchorPane header_sem_resposta;
    @FXML public AnchorPane header_positiva;
    @FXML public AnchorPane header_negativa;
    @FXML public AnchorPane panel_principal;
    @FXML public TableView table_positiva;
    @FXML public TableView table_negativa;
    @FXML public TableColumn column_area_negativa;
    @FXML public TableColumn column_subarea_negativa;
    @FXML public TableColumn column_empresa_negativa;
    @FXML public TableColumn column_salario_negativa;
    @FXML public TableColumn column_area_semresposta;
    @FXML public TableColumn column_subarea_semresposta;
    @FXML public TableColumn column_empresa_semresposta;
    @FXML public TableColumn column_salario_semresposta;
    @FXML public TableColumn column_area_positivo;
    @FXML public TableColumn column_subarea_positivo;
    @FXML public TableColumn column_empresa_positivo;
    @FXML public TableColumn column_salario_positivo;
          private propostaDAO PropDAO = new propostaDAO();
          private vagasDAO vagasDAO = new vagasDAO();
          private Candidato c;

=======
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
    public ImageView btn_next;
    public Pane panel_buttom_next;

    private propostaDAO PropDAO = new propostaDAO();
    private vagasDAO vagasDAO = new vagasDAO();
    private Candidato c;
>>>>>>> 8b2f47e1fcd1c6ca408562457b1d612227668c4c
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barra_1.setVisible(false);
        barra_2.setVisible(false);
        barra_3.setVisible(false);
        barra_4.setVisible(false);
        barra_5.setVisible(false);
        details();
    }

    public ObservableList<Vaga> getPropostas(String status,Candidato c)
    {
        ArrayList<Proposta> list= PropDAO.getPropostas(c,status);
        ObservableList<Vaga>propostas=FXCollections.observableArrayList();
        for (Proposta p:list) {
                propostas.add(p.getVaga());
        }
        return propostas;
    }
<<<<<<< HEAD
    @FXML
=======
    public String notification(int type, String empresa, String vaga)
    {
            if(type == 1)return "A empresa " + empresa + "recusou seu currículo :(";
            else if(type == 2)return "Você preencheu a vaga de " + vaga + " na " + empresa + "!";
            else if(type == 3)return "As vaga de " + vaga + " na " + empresa + " acabaram :(";
            else return "não há notificações no momento...";
            
    }
>>>>>>> 8b2f47e1fcd1c6ca408562457b1d612227668c4c
    public void fill_table(Candidato c)
    {
        column_area_semresposta.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_semresposta.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_semresposta.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_semresposta.setCellValueFactory(new PropertyValueFactory<>("salario"));
        table_semResposta.setItems(getPropostas("Em espera",c));
        column_area_positivo.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_positivo.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_positivo.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_positivo.setCellValueFactory(new PropertyValueFactory<>("salario"));
        table_positiva.setItems(getPropostas("Positivo",c));
        column_area_negativa.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_negativa.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_negativa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_negativa.setCellValueFactory(new PropertyValueFactory<>("salario"));
        table_negativa.setItems(getPropostas("Negativo",c));
    }

    public void get_user(Candidato c)
    {
        this.c=c;
        fill_table(c);
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
    @FXML
    public void OpenHome(MouseEvent event) throws IOException{
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(header_sem_resposta,header_negativa,header_positiva,table_semResposta,table_positiva,table_negativa);
        table_semResposta.getItems().removeAll();
        table_positiva.getItems().removeAll();
        table_negativa.getItems().removeAll();
        fill_table(c);
    }
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_vagas.fxml"));
            AnchorPane pane = loader.load();
            UI_candidatoVagasController controller = loader.getController();
            controller.set_medidas(panel_principal.getHeight(),panel_principal.getWidth());
            controller.get_user(c);
            panel_principal.getChildren().clear();
            panel_principal.getChildren().setAll(pane);
    }
    @FXML
    public void OpenCurriculo(MouseEvent Event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_curriculo.fxml"));
        AnchorPane pane = loader.load();
        UI_candidatoCurriculoController controller = loader.getController();
        controller.get_user(c);
        controller.set_medidas(panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(pane);
    }
    @FXML
    public void OpenDados(MouseEvent Event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UI_candidato_dados.fxml"));
        AnchorPane pane = loader.load();
        UI_candidatoDadosController controller = loader.getController();
        System.out.println(c.getNome());
        controller.get_user(c);
        controller.set_medidas(panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(pane);
    }
    @FXML
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
       btn_next.setOnMouseEntered(event -> { panel_buttom_next.setStyle("-fx-border-color: white;");});
       btn_next.setOnMouseExited(event -> { panel_buttom_next.setStyle("-fx-border-color: transparent;");});
    }

}
