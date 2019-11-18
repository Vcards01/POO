package Controller.Controllers_Empresa;

import DataBase.vagasDAO;
import Model.Empresa;
import Model.Vaga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class UI_empresaVagasController implements Initializable {
    @FXML public TableColumn column_nome;
    @FXML public TableColumn column_area;
    @FXML public TableColumn column_subarea;
    @FXML public TableColumn column_nvagas;
    @FXML public TableColumn column_salario;
    @FXML public TableView table_vagas;
    @FXML public AnchorPane panel_vagas;
    private vagasDAO DAO = new vagasDAO();
    private Empresa e;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void start(Empresa e,Double h , Double w)
    {
        this.e=e;
        panel_vagas.setPrefHeight(h);
        panel_vagas.setPrefWidth(w);
        set_table();
    }

    public void set_table()
    {
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_nvagas.setCellValueFactory(new PropertyValueFactory<>("num_vagas"));
        column_salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        table_vagas.setItems(getVagas());
    }
    public ObservableList<Vaga> getVagas()
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas_by_empresa(e.getIdentificador()));
        return vagas;
    }

    @FXML
    public void adicionar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaVagasEdit.fxml"));
        Parent root = null;
        root = loader.load();
        UI_empresaVagasEditController controller = loader.getController();
        controller.start(e,this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    @FXML
    public void editar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaVagasEdit.fxml"));
        Parent root = null;
        root = loader.load();
        UI_empresaVagasEditController controller = loader.getController();
        controller.start(e, (Vaga) table_vagas.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    @FXML
    public void remove(ActionEvent event)
    {
        DAO.delete((Vaga) table_vagas.getSelectionModel().getSelectedItem());
        set_table();
    }

}
