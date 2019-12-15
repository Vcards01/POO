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
    //Painel principal de vagas
    @FXML  public AnchorPane panel_vagas;
    //Combo box de area e subarea
    @FXML public ComboBox cb_area;
    @FXML public ComboBox cb_subarea;
    //Tabela das vagas
    @FXML public TableView table_vagas;
    //Colunas da tabela de vagas
    @FXML public TableColumn column_area;
    @FXML public TableColumn column_subarea;
    @FXML public TableColumn column_nvagas;
    @FXML public TableColumn column_salario;
    @FXML public TableColumn column_empresa;
    @FXML public TableColumn column_nome;
    //Variaveis normais
    private vagasDAO DAO = new vagasDAO();
    private Candidato c;
    private propostaDAO propDAO = new propostaDAO();

    @Override//Inicia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //incia algumas coisas
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
    //Define as colunas da tabelas
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
    //Preenche a combobox
    public void fill_comboBox()
    {
        ObservableList<String> areas = FXCollections.observableArrayList(DAO.get_area());
        ObservableList<String> subareas = FXCollections.observableArrayList(DAO.get_subarea());
        cb_area.setItems(areas);
        cb_subarea.setItems(subareas);

    }
    //Preenche a combobox usando uma area
    public void fill_comboBox(String area)
    {
        ObservableList<String> subareas = FXCollections.observableArrayList(DAO.get_subarea(area));
        cb_subarea.setItems(subareas);

    }
    //retorna as vagas
    public ObservableList<Vaga> getVagas()
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas(c));
        return vagas;
    }
    //retorna as vagas por area
    public ObservableList<Vaga> getVagas_by_area(String area)
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.getVagas_by_area(area,c));
        return vagas;
    }
    //retorna as vagas por sub area
    public ObservableList<Vaga> getVagas_by_subarea(String subarea)
    {
        ObservableList<Vaga> vagas =FXCollections.observableArrayList(DAO.get_by_subArea(subarea, (String) cb_area.getSelectionModel().getSelectedItem(),c));
        return vagas;
    }
    //Listener para mudar tudo de acordo com a area
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
    //Listener para mudar tudo de acordo com a subarea
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
    //Mostra a descrição da vaga
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
    //Evento para se candidatar a vaga
    @FXML
    public void candidatar(ActionEvent event)
    {
        Vaga v = (Vaga)table_vagas.getSelectionModel().getSelectedItem();
        Proposta p = new Proposta(c,v,"Em espera",false,false);
        propDAO.create(p);
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Sucesso");
        aviso.setHeaderText(null);
        aviso.setContentText("Você se canditatou para a vaga,fique atento a resposta da empresa");
        aviso.show();
        cb_area.setValue("ALL");
        cb_subarea.setValue("ALL");
        table_vagas.getItems().removeAll();
        set_table();
    }
}
