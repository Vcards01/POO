package Controller.Controllers_Empresa;

import Controller.Controllers_Candidato.UI_candidato_DadosEditarController;
import DataBase.propostaDAO;
import DataBase.vagasDAO;
import Model.Empresa;
import Model.Proposta;
import Model.Vaga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_empresaPropostasController implements Initializable {
    public TableColumn column_nome_candidato;
    public TableColumn column_email_candidato;
    public TableColumn column_nome_vaga;
    public TableColumn column_nvagas;
    public TableView table_propostas;
    public AnchorPane panel_propostas;
    private Empresa e;
    private propostaDAO DAO = new propostaDAO();
    private vagasDAO vagasDAO= new vagasDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void start(Empresa e, Double h , Double w)
    {
        this.e=e;
        panel_propostas.setPrefHeight(h);
        panel_propostas.setPrefWidth(w);
        set_table();
    }

    public void set_table()
    {
        column_nome_candidato.setCellValueFactory(new PropertyValueFactory<>("candidato"));
        column_email_candidato.setCellValueFactory(new PropertyValueFactory<>("email_candidato"));
        column_nome_vaga.setCellValueFactory(new PropertyValueFactory<>("vaga"));
        column_nvagas.setCellValueFactory(new PropertyValueFactory<>("n_vagas"));
        table_propostas.getItems().removeAll();
        table_propostas.setItems(getPropostas());
    }
    public ObservableList<Proposta> getPropostas()
    {
        ObservableList<Proposta> propostas = FXCollections.observableArrayList(DAO.getPropostas(e));
        return propostas;
    }
    @FXML
    public void aceitar(ActionEvent event) throws IOException {
        Proposta p = (Proposta) table_propostas.getSelectionModel().getSelectedItem();
        p.setStatus("Positivo");
        p.getVaga().setNum_vagas(p.getN_vagas()-1);
        p.setN_vagas(p.getN_vagas()-1);
        vagasDAO.update(p.getVaga());
        DAO.update(p);
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Proposta aceita");
        aviso.setHeaderText(null);
        aviso.setContentText("Agora use o email do candidato para entrar em contato com ele.");
        aviso.show();
        set_table();
    }
    @FXML
    public void recusar(ActionEvent event) throws IOException {
        Proposta p = (Proposta) table_propostas.getSelectionModel().getSelectedItem();
        p.setStatus("Negativo");
        p.getVaga().setNum_vagas(p.getN_vagas()-1);
        p.setN_vagas(p.getN_vagas()-1);
        vagasDAO.update(p.getVaga());
        DAO.update(p);
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Proposta recusada");
        aviso.setHeaderText(null);
        aviso.setContentText("O candidato sera notificado.");
        aviso.show();
        set_table();
    }
    @FXML
    public void ver_curriculo(ActionEvent event) throws IOException {
        Proposta p = (Proposta) table_propostas.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaVerCurriculo.fxml"));
        Parent root = null;
        root = loader.load();
        UI_empresaVerCurriculoController controller = loader.getController();
        controller.start(p.getCandidato());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
