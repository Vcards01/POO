package Controller.Controllers_Candidato;

import DataBase.curriculoDAO;
import DataBase.propostaDAO;
import DataBase.usuarioDAO;
import Model.Candidato;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UI_candidatoController implements Initializable {

    //Painel principal que vai trocando as telas
    @FXML public AnchorPane panel_principal;
    //Detalhes que aparecem e somem quando o mouse passa em cima ou sai de cima
    @FXML public AnchorPane barra_1;
    @FXML public AnchorPane barra_2;
    @FXML public AnchorPane barra_3;
    @FXML public AnchorPane barra_4;
    @FXML public AnchorPane barra_5;
    //Botões do menu principal
    @FXML public AnchorPane btn_vagas;
    @FXML public AnchorPane btn_curriculo;
    @FXML public AnchorPane btn_perfil;
    @FXML public AnchorPane btn_sair;
    @FXML public AnchorPane btn_home;
    //Faixas de titulos
    @FXML public AnchorPane header_sem_resposta;
    @FXML public AnchorPane header_positiva;
    @FXML public AnchorPane header_negativa;
    @FXML public AnchorPane header_notificacao;
    //Tabelas de notificação
    @FXML public TableView table_semResposta;
    @FXML public TableView table_positiva;
    @FXML public TableView table_negativa;
    //Colunas das tabelas
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
    @FXML public TableColumn column_nome_negativa;
    @FXML public TableColumn column_nome_semresposta;
    @FXML public TableColumn column_nome_positivo;
    //Botões de gerenciamento das notificações
    @FXML public Button btn_apagar;
    @FXML public Button btn_apagar_visualização;
    @FXML public Button btn_desistir_vaga;
    //Variaveis normais
    private usuarioDAO  userDAO = new usuarioDAO();
    private propostaDAO PropDAO = new propostaDAO();
    private curriculoDAO curriculoDAO = new curriculoDAO();
    private Candidato c;

    @Override//Inicia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barra_1.setVisible(false);
        barra_2.setVisible(false);
        barra_3.setVisible(false);
        barra_4.setVisible(false);
        barra_5.setVisible(false);
        details();
    }
    //Define algumas coisas no inicio
    public void start(Candidato c)
    {
        this.c=c;
        fill_table();
    }
    //Atualiza o usuario da pagina
    public void reload_user()
    {
        this.c= (Candidato) userDAO.read(c.getIdentificador());
    }
    //Preenche as tabelas de notificação
    public void fill_table()
    {
        set_tables();
        table_semResposta.setItems(getVagas("Em espera",c));
        table_positiva.setItems(getVagas("Positivo",c));
        table_negativa.setItems(getVagas("Negativo",c));
    }
    //Define as configurações da colunas das tabelas
    public void set_tables()
    {
        //Tabela sem resposta
        column_nome_semresposta.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area_semresposta.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_semresposta.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_semresposta.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_semresposta.setCellValueFactory(new PropertyValueFactory<>("salario"));
        //Tabela resposta negativa
        column_nome_negativa.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area_negativa.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_negativa.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_negativa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_negativa.setCellValueFactory(new PropertyValueFactory<>("salario"));
        //Tabela resposta positiva
        column_nome_positivo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area_positivo.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_positivo.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_empresa_positivo.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        column_salario_positivo.setCellValueFactory(new PropertyValueFactory<>("salario"));
    }
    //Retorna as vagas de acordo com as propostas
    public ObservableList<Vaga> getVagas(String status, Candidato c)
    {
        ArrayList<Proposta> list= PropDAO.getPropostas(c,status);
        ObservableList<Vaga>propostas=FXCollections.observableArrayList();
        for (Proposta p:list) {
            propostas.add(p.getVaga());
        }
        return propostas;
    }
    //Apaga as notificações de propostas recusadas
    public void delete_recusadas(ActionEvent event)
    {
        Vaga v = (Vaga) table_negativa.getSelectionModel().getSelectedItem();
        Proposta p =PropDAO.read(c.getIdentificador(),v.getId());
        PropDAO.delete(p);
        fill_table();
    }
    //Apaga as notificaçoẽs de propostas aceitas
    public void visualizar_aceitas(ActionEvent event)
    {
        Vaga v = (Vaga) table_positiva.getSelectionModel().getSelectedItem();
        Proposta p =PropDAO.read(c.getIdentificador(),v.getId());
        p.setNotifica_user(true);
        PropDAO.update(p);
        fill_table();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Parabéns");
        aviso.setHeaderText(null);
        aviso.setContentText("A empresa aceitou seu curriculo, aguarde contato por email,fique atento!");
        aviso.show();
    }
    //Remove as propostas que o candidato desiste de concorrer
    public void desistir_vaga(ActionEvent event)
    {
        Vaga v = (Vaga) table_semResposta.getSelectionModel().getSelectedItem();
        Proposta p =PropDAO.read(c.getIdentificador(),v.getId());
        PropDAO.delete(p);
        fill_table();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("=c");
        aviso.setHeaderText(null);
        aviso.setContentText("Uma pena que você desistiu!");
        aviso.show();
    }
    //Abre a home
    @FXML
    public void OpenHome(MouseEvent event) throws IOException{
        reload_user();
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(header_notificacao,header_sem_resposta,header_negativa,header_positiva,table_semResposta,table_positiva,table_negativa,btn_apagar,btn_apagar_visualização,btn_desistir_vaga);
        table_semResposta.getItems().removeAll();
        table_positiva.getItems().removeAll();
        table_negativa.getItems().removeAll();
        fill_table();
    }
    //Abre a lista de vagas
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
        if(curriculoDAO.read(c)==null)
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Acesso negado");
            aviso.setHeaderText(null);
            aviso.setContentText("Para acessar as vagas,primeiro crie um curriculo! ");
            aviso.show();
        }
        else {
            reload_user();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_vagas.fxml"));
            AnchorPane pane = loader.load();
            UI_candidatoVagasController controller = loader.getController();
            controller.start(c, panel_principal.getHeight(), panel_principal.getWidth());
            panel_principal.getChildren().clear();
            panel_principal.getChildren().setAll(pane);
        }
    }
    //Abre o curriculo do candidato
    @FXML
    public void OpenCurriculo(MouseEvent Event) throws IOException {
            reload_user();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_curriculo.fxml"));
            AnchorPane pane = loader.load();
            UI_candidatoCurriculoController controller = loader.getController();
            controller.start(c,panel_principal.getHeight(),panel_principal.getWidth());
            panel_principal.getChildren().clear();
            panel_principal.getChildren().setAll(pane);
    }
    //Abre a configuração de dados do candidato
    @FXML
    public void OpenDados(MouseEvent Event) throws IOException {
        reload_user();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_dados.fxml"));
        AnchorPane pane = loader.load();
        UI_candidatoDadosController controller = loader.getController();
        System.out.println(c.getNome());
        controller.start(c,panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(pane);
    }
    //Encerra a sessão
    @FXML
    public void Close(MouseEvent Event) {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
            Stage login = new Stage();
            Scene scene = new Scene(root);
            login.initStyle(StageStyle.UNDECORATED);
            login.setScene(scene);
            login.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Adiciona eventos ao detalhes do botões do menu
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
    }

}
