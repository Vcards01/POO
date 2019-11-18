package Controller.Controllers_Empresa;

import DataBase.vagasDAO;
import Model.Empresa;
import Model.Vaga;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        spn_n_vagas.getValueFactory().setValue(v.getNum_vagas());

    }
    public void set_spinner()
    {
        SpinnerValueFactory<Integer> valueVagas= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,500);
        spn_n_vagas.setValueFactory(valueVagas);
    }

    @FXML
    public void save(ActionEvent event)
    {
        if (editable)
        {
            System.out.println("Ta editando bro");
            Vaga vaga = new Vaga(e,txt_descricao.getText(),v.getId(),Double.parseDouble(txt_salario.getText()),txt_horario.getText(), (Integer) spn_n_vagas.getValue(),txt_area.getText(),txt_subarea.getText(),txt_nome.getText());
            DAO.update(vaga);
        }
        else
        {
            Vaga v = new Vaga(e,txt_descricao.getText(),txt_nome.getText(),Double.parseDouble(txt_salario.getText()),txt_horario.getText(), (Integer) spn_n_vagas.getValue(),txt_area.getText(),txt_subarea.getText());
            DAO.create(v);
        }
        controller.set_table();
        Stage close=(Stage)txt_area.getScene().getWindow();
        close.close();
    }
    @FXML
    public void close(MouseEvent Event) {
        Stage close=(Stage)txt_area.getScene().getWindow();
        close.close();
    }
}
