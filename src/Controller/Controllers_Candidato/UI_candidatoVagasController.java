package Controller.Controllers_Candidato;

import DataBase.propostaDAO;
import DataBase.vagasDAO;
import Model.Candidato;
import Model.Proposta;
import Model.Vaga;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidatoVagasController implements Initializable {
    @FXML  public AnchorPane panel_vagas;
    @FXML public Label txt_disponiveis;
    @FXML public ComboBox cb_area;
    @FXML public ComboBox cb_subarea;
    @FXML public TableColumn column_area;
    @FXML public TableColumn column_subarea;
    @FXML public TableColumn column_nvagas;
    @FXML public TableColumn column_salario;
    @FXML public TableColumn column_empresa;
    @FXML public TableView table_vagas;
    @FXML public TableColumn column_nome;
    private vagasDAO DAO = new vagasDAO();
    private Candidato c;
    private propostaDAO propDAO = new propostaDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void start(Candidato c,Double h , Double w)
    {
        this.c=c;
        panel_vagas.setPrefHeight(h);
        panel_vagas.setPrefWidth(w);
        fill_comboBox();
        set_table();
        find_by_area();
        find_by_subarea();

    }
    public void set_table()
    {
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_nvagas.setCellValueFactory(new PropertyValueFactory<>("num_vagas"));
        column_salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        column_empresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        table_vagas.setItems(getVagas());
    }
    public void fill_comboBox()
    {
        ObservableList<String> areas = FXCollections.observableArrayList(DAO.get_area());
        ObservableList<String> subareas = FXCollections.observableArrayList(DAO.get_subarea());
        cb_area.setItems(areas);
        cb_subarea.setItems(subareas);

    }
    public void fill_comboBox(String area)
    {
        ObservableList<String> subareas = FXCollections.observableArrayList(DAO.get_subarea(area));
        cb_subarea.setItems(subareas);

    }
    public ObservableList<Vaga> getVagas()
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas());
        return vagas;
    }
    public ObservableList<Vaga> getVagas_by_area(String area)
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas_by_area(area));
        return vagas;
    }
    public ObservableList<Vaga> getVagas_by_subarea(String subarea)
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.get_by_subArea(subarea, (String) cb_area.getSelectionModel().getSelectedItem()));
        return vagas;
    }
    public void find_by_area()
    {
        cb_area.valueProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                table_vagas.getItems().removeAll();
                cb_subarea.setValue(null);
                table_vagas.setItems(getVagas_by_area(s1));
                cb_subarea.getItems().removeAll();
                fill_comboBox(s1);

            }
        });
    }
    public void find_by_subarea()
    {
        cb_subarea.valueProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                table_vagas.getItems().removeAll();
                table_vagas.setItems(getVagas_by_subarea(s1));
            }
        });
    }

    @FXML
    public void show_descricao(ActionEvent event)
    {
        Vaga v = (Vaga)table_vagas.getSelectionModel().getSelectedItem();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Descrição da vaga");
        aviso.setHeaderText(null);
        aviso.setContentText(v.getDescricao());
        aviso.show();
    }
    @FXML
    public void candidatar(ActionEvent event)
    {
        Vaga v = (Vaga)table_vagas.getSelectionModel().getSelectedItem();
        Proposta p = new Proposta(c,v,"Em espera");
        propDAO.create(p);
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Sucesso");
        aviso.setHeaderText(null);
        aviso.setContentText("Você se canditatou para a vaga,fique atento a resposta da empresa");
        aviso.show();
    }
}
