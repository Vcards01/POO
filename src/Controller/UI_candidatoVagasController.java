package Controller;

import DataBase.propostaDAO;
import DataBase.usuarioDAO;
import DataBase.vagasDAO;
import Model.Candidato;
import Model.Proposta;
import Model.Vaga;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.geom.Area;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class UI_candidatoVagasController implements Initializable {
    public AnchorPane panel_vagas;
    public Label txt_disponiveis;
    public ComboBox cb_area;
    public ComboBox cb_subarea;
    public TableColumn column_area;
    public TableColumn column_subarea;
    public TableColumn column_nvagas;
    public TableColumn column_salario;
    public TableColumn column_empresa;
    public TableView table_vagas;
    private vagasDAO DAO = new vagasDAO();
    private Candidato c;
    private propostaDAO propDAO = new propostaDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fill_comboBox();
        set_table();
        find_by_area();
        find_by_subarea();
    }
    public void get_user(Candidato c)
    {
        this.c=c;

    }
    public void set_medidas(Double h , Double w)
    {
        panel_vagas.setPrefHeight(h);
        panel_vagas.setPrefWidth(w);
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
    public void set_table()
    {
        column_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_nvagas.setCellValueFactory(new PropertyValueFactory<>("num_vagas"));
        column_salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        column_empresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        table_vagas.setItems(getVagas());
    }
    public ObservableList<Vaga> getVagas()
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas());
        return vagas;
    }
    public ObservableList<Vaga> getVagas(String area)
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas(area));
        return vagas;
    }
    public ObservableList<Vaga> getVagas_sub(String subarea)
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
                table_vagas.setItems(getVagas(s1));
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
                table_vagas.setItems(getVagas_sub(s1));
            }
        });
    }
    public void show_descricao(ActionEvent event)
    {
        Vaga v = (Vaga)table_vagas.getSelectionModel().getSelectedItem();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Descrição da vaga");
        aviso.setHeaderText(null);
        aviso.setContentText(v.getDescricao());
        aviso.show();
    }
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
