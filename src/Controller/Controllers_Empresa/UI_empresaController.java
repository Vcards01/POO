package Controller.Controllers_Empresa;


import DataBase.propostaDAO;
import DataBase.usuarioDAO;
import DataBase.vagasDAO;
import Model.Empresa;
import Model.Proposta;
import Model.Vaga;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class UI_empresaController implements Initializable {

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
    @FXML public AnchorPane btn_propostas;
    @FXML public AnchorPane btn_perfil;
    @FXML public AnchorPane btn_home;
    @FXML public AnchorPane btn_sair;
    //Faixas de titulos
    @FXML public AnchorPane header_positiva;
    @FXML public AnchorPane header_notificacao;
    @FXML public AnchorPane header_negativa;
    //Tabelas de notificação
    @FXML public TableView table_aceitas;
    @FXML public TableView table_encerradas;
    //Colunas das tabelas
    @FXML public TableColumn column_nome_candidato;
    @FXML public TableColumn column_email_candidato;
    @FXML public TableColumn column_nome_vaga;
    @FXML public TableColumn column_nvagas;
    @FXML public TableColumn column_nome;
    @FXML public TableColumn column_area_vaga;
    @FXML public TableColumn column_subarea_vaga;
    @FXML public TableColumn column_salario_vaga;
    //Botões de gerenciamento das notificações
    @FXML public Button btn_apagar;
    @FXML public Button btn_apagar_vaga;
    //Variaveis normais
    private Empresa e ;
    private propostaDAO propDAO = new propostaDAO();
    private vagasDAO vagasDAO = new vagasDAO();
    private usuarioDAO userDAO = new usuarioDAO();

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
    public void start(Empresa e)
    {
        this.e=e;
        fill_table();
    }
    //Atualiza o usuario da pagina
    public void reload_user()
    {
        this.e= (Empresa) userDAO.read(e.getIdentificador());
    }
    //Preenche as tabelas de notificação
    public void fill_table()
    {
        set_tables();
        table_aceitas.setItems(FXCollections.observableArrayList(propDAO.getPropostas(e,"Positivo")));
        table_encerradas.setItems(FXCollections.observableArrayList(vagasDAO.get_by_status("Bloqueado")));
    }
    //Define as configurações da colunas das tabelas
    public void set_tables()
    {
        //Tabela Propostas aceita
        column_nome_candidato.setCellValueFactory(new PropertyValueFactory<>("candidato"));
        column_email_candidato.setCellValueFactory(new PropertyValueFactory<>("email_candidato"));
        column_nome_vaga.setCellValueFactory(new PropertyValueFactory<>("vaga"));
        column_nvagas.setCellValueFactory(new PropertyValueFactory<>("n_vagas"));
        //Tabela resposta negativa
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_area_vaga.setCellValueFactory(new PropertyValueFactory<>("area"));
        column_subarea_vaga.setCellValueFactory(new PropertyValueFactory<>("subArea"));
        column_salario_vaga.setCellValueFactory(new PropertyValueFactory<>("salario"));
    }
    //Apaga as notificaçoẽs de propostas aceitas
    public void visualizar_aceitas(ActionEvent event)
    {
        Proposta p = (Proposta) table_aceitas.getSelectionModel().getSelectedItem();
        p.setNotifica_emp(true);
        propDAO.update(p);
        fill_table();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Tudo certo");
        aviso.setHeaderText(null);
        aviso.setContentText("Não se esqueça de entrar em contato com o candidato!");
        aviso.show();
    }
    //Remove as vagas que foram encerradas
    public void remover_vaga(ActionEvent event)
    {
        Vaga v = (Vaga) table_encerradas.getSelectionModel().getSelectedItem();
        vagasDAO.delete(v);
        fill_table();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Tudo certo");
        aviso.setHeaderText(null);
        aviso.setContentText("Vaga apagada com sucesso!");
        aviso.show();
    }
    //Abre a home
    @FXML
    public void OpenHome(MouseEvent event) throws IOException{
        reload_user();
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(header_negativa,header_positiva,table_aceitas,table_encerradas,btn_apagar,btn_apagar_vaga,header_notificacao);
        table_aceitas.getItems().removeAll();
        table_encerradas.getItems().removeAll();
        fill_table();
    }
    //Abre a lista de vagas
    @FXML
    public void OpenVagas(MouseEvent Event) throws IOException {
        reload_user();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaVagas.fxml"));
        AnchorPane pane = loader.load();
        UI_empresaVagasController controller = loader.getController();
        controller.start(e,panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(pane);
    }
    //Abre as propostas feitas para as vagas da empresa
    @FXML
    public void OpenPropostas(MouseEvent Event) throws IOException {
        reload_user();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresaPropostas.fxml"));
        AnchorPane pane = loader.load();
        UI_empresaPropostasController controller = loader.getController();
        controller.start(e,panel_principal.getHeight(),panel_principal.getWidth());
        panel_principal.getChildren().clear();
        panel_principal.getChildren().setAll(pane);
    }
    //Abre a configuração de dados da empresa
    @FXML
    public void OpenDados(MouseEvent Event) throws IOException {
        reload_user();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresa_dados.fxml"));
        AnchorPane pane = loader.load();
        UI_empresaDadosController controller = loader.getController();
        controller.start(e,panel_principal.getHeight(),panel_principal.getWidth());
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
        btn_propostas.setOnMouseEntered(event -> { barra_3.setVisible(true);});
        btn_propostas.setOnMouseExited(event -> { barra_3.setVisible(false);});
        btn_perfil.setOnMouseEntered(event -> { barra_4.setVisible(true);});
        btn_perfil.setOnMouseExited(event -> { barra_4.setVisible(false);});
        btn_sair.setOnMouseEntered(event -> { barra_5.setVisible(true);});
        btn_sair.setOnMouseExited(event -> { barra_5.setVisible(false);});
    }

}
