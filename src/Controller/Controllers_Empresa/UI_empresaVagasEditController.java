package Controller.Controllers_Empresa;

import DataBase.vagasDAO;
import Model.Empresa;
import Model.Vaga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_empresaVagasEditController implements Initializable {
    @FXML public TextField txt_nome;
    @FXML public TextField txt_subarea;
    @FXML public TextField txt_horario;
    @FXML public TextField txt_area;
    @FXML public TextArea txt_descricao;
    @FXML public TextField txt_salario;
    @FXML public Spinner spn_n_vagas;
    public ComboBox cb_status;
    public Label txt_status;
    private Empresa e;
    private Vaga v;
    private UI_empresaVagasController controller;
    private Boolean editable=false;
    private vagasDAO DAO = new vagasDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void start(Empresa e,UI_empresaVagasController controller)
    {
        set_spinner();
        this.e=e;
        this.controller=controller;
        txt_status.setVisible(false);
        cb_status.setVisible(false);
    }
    public void start(Empresa e, Vaga v,UI_empresaVagasController controller)
    {
        this.e=e;
        this.v=v;
        this.controller=controller;
        editable=true;
        set_spinner();
        txt_nome.setText(v.getNome());
        txt_subarea.setText(v.getSubArea());
        txt_horario.setText(v.getHorario());
        txt_area.setText(v.getArea());
        txt_descricao.setText(v.getDescricao());
        txt_salario.setText(String.valueOf(v.getSalario()));
        if(v.getNum_vagas()>0) {
            spn_n_vagas.getValueFactory().setValue(v.getNum_vagas());
        }
        else
        {
            spn_n_vagas.getValueFactory().setValue(1);

        }
        cb_status.setVisible(true);
        fill_comboBox();
        cb_status.setValue(v.getStatus());

    }
    public void fill_comboBox()
    {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Livre");
        tipos.add("Bloqueado");
        cb_status.setItems(tipos);
    }
    public void set_spinner()
    {
        SpinnerValueFactory<Integer> valueVagas= new SpinnerValueFactory.IntegerSpinnerValueFactory(0,500);
        spn_n_vagas.setValueFactory(valueVagas);
    }

    @FXML
    public void save(ActionEvent event)
    {
        boolean numero = false;
        if(txt_status.getText().equals("")||txt_area.getText().equals("")||txt_descricao.getText().equals("")||txt_horario.getText().equals("")||txt_nome.getText().equals("")||txt_salario.getText().equals("")||txt_subarea.getText().equals(""))
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Preencha todos os campos");
            aviso.setHeaderText(null);
            aviso.setContentText("Para se registrar, preencha todos os campos.");
            aviso.show();
        }
        else
        {
            try
            {
                Double.parseDouble(txt_salario.getText());
                numero=true;
            }
            catch (Exception e)
            {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Formato incorreto");
                aviso.setHeaderText(null);
                aviso.setContentText("Salario apanas numeros");
                aviso.show();
                numero=false;
            }
            if(numero)
            {
                Vaga vaga=null;
                if (editable)
                {
                    if((int)spn_n_vagas.getValue() > 0) {
                        vaga = new Vaga(e,txt_descricao.getText(),v.getId(),Double.parseDouble(txt_salario.getText()),txt_horario.getText(), (Integer) spn_n_vagas.getValue(),txt_area.getText(),txt_subarea.getText(),txt_nome.getText(), "Livre");
                    }
                    else
                    {
                        vaga = new Vaga(e,txt_descricao.getText(),v.getId(),Double.parseDouble(txt_salario.getText()),txt_horario.getText(), (Integer) spn_n_vagas.getValue(),txt_area.getText(),txt_subarea.getText(),txt_nome.getText(), "Bloqueado");
                    }
                    DAO.update(vaga);
                }
                else
                {
                    Vaga v = new Vaga(e,txt_descricao.getText(),txt_nome.getText(),Double.parseDouble(txt_salario.getText()),txt_horario.getText(), (Integer) spn_n_vagas.getValue(),txt_area.getText(),txt_subarea.getText(),"Livre");
                    DAO.create(v);
                }
                controller.set_table();
                Stage close=(Stage)txt_area.getScene().getWindow();
                close.close();
            }
        }
    }
    @FXML
    public void close(MouseEvent Event) {
        Stage close=(Stage)txt_area.getScene().getWindow();
        close.close();
    }
}
