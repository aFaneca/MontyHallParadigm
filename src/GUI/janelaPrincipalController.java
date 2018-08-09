package GUI;

import Modelo.Estatistica;
import Modelo.Experiencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class janelaPrincipalController implements Initializable {


    @FXML
    private Spinner<Integer> spinner_NrPortas;
    @FXML
    private Spinner<Integer> spinner_NrCarros;
    @FXML
    private Spinner<Integer> spinner_NrSimulacoes;
    @FXML
    private Button botao_iniciarSimulacao;

    @FXML
    private Label label_NrSucessos_AMudar;

    @FXML
    private Label label_NrInsucessos_AMudar;

    @FXML
    private Label label_TaxaSucesso_AMudar;

    @FXML
    private Label label_NrSucessos_SemMudar;

    @FXML
    private Label label_NrInsucessos_SemMudar;

    @FXML
    private Label label_TaxaSucesso_SemMudar;

    @FXML
    private Rectangle rect_AMudar;

    @FXML
    private Rectangle rectSemMudar;

    @FXML
    private PieChart tabelaCircular;

    @FXML
    private ProgressIndicator ind_Progresso;

    @FXML
    private VBox vBox_tentar;

    @FXML
    private Button botao_tentar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Configuração Spinner Nr. de Portas
        this.spinner_NrPortas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 3));

        // Configuração Spinner Nr. de Carros
        this.spinner_NrCarros.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

        // Configuração Spinner Nr. de Simulações
        this.spinner_NrSimulacoes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10));

        vBox_tentar.setVisible(false);
    }



    @FXML
    private void acaoDoBotao(ActionEvent event){
        Estatistica e = new Estatistica(spinner_NrSimulacoes.getValue(), spinner_NrCarros.getValue(), spinner_NrPortas.getValue());
        atualizaIndicadorDeProgresso(e);
        atualizaLabels(e);
        atualizaTabela(e);
        vBox_tentar.setVisible(true);
    }

    @FXML
    private void acaoDoBotao_tentar() throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) botao_tentar.getScene().getWindow();

        // Mudar para a scene janelaTestes
        Parent jTestesParent = FXMLLoader.load(getClass().getResource("janelaTestes.fxml"));
        Scene janelaTestes = new Scene(jTestesParent, 1500, 800);
        stage.setScene(janelaTestes);

        // fechar janela
        //stage.close();
    }
    private void atualizaIndicadorDeProgresso(Estatistica e) {
        ind_Progresso.setDisable(false);

        ind_Progresso.progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() >= 1.0) {
                Text doneText = (Text) ind_Progresso.lookup(".percentage");
                doneText.setText("Simulação Concluída!");
            }
        });

        ind_Progresso.setProgress(1);
    }

    private void atualizaTabela(Estatistica e) {
       ObservableList<PieChart.Data> dadosDaTabela
        = FXCollections.observableArrayList(
                new PieChart.Data("Hipótese A", e.getMediaTaxaSucessoMudar()),
                new PieChart.Data("Hipótese B", e.getMediaTaxaSucessoSemMudar()));

        tabelaCircular.setData(dadosDaTabela);
        tabelaCircular.setVisible(true);
       /*
        XYChart.Series hA = new XYChart.Series();
        XYChart.Series hB = new XYChart.Series();

        // Série hA - Com mudança de porta
        for(Double exp : e.getListaTaxaSucessoMudar()){
            hA.getData().add(new XYChart.Data(String.valueOf(e.getListaTaxaSucessoMudar().indexOf(exp)), exp));
        }

        //Série hB - Sem mudança de porta
        for(Double exp : e.getListaTaxaSucessoSemMudar()){
            hA.getData().add(new XYChart.Data(String.valueOf(e.getListaTaxaSucessoSemMudar().indexOf(exp)), exp));
        }

        tabela_tlinhas.getData().addAll(hA, hB);
    */
    }

    private void atualizaLabels(Estatistica e) {
        label_NrSucessos_AMudar.setText("" + e.getNrSucessosMudar());
        label_NrInsucessos_AMudar.setText("" + e.getNrInsucessosMudar());
        label_TaxaSucesso_AMudar.setText("" + new DecimalFormat("##.##").format(e.getMediaTaxaSucessoMudar()));

        label_NrSucessos_SemMudar.setText("" + e.getNrSucessosSemMudar());
        label_NrInsucessos_SemMudar.setText("" + e.getNrInsucessosSemMudar());
        label_TaxaSucesso_SemMudar.setText("" + new DecimalFormat("##.##").format(e.getMediaTaxaSucessoSemMudar()));

        if(e.getMediaTaxaSucessoMudar() > e.getMediaTaxaSucessoSemMudar()){ // Se a média da hipótese A ( a mudar ) for superior à outra
            rect_AMudar.setFill(Color.GREEN);
            rectSemMudar.setFill(Color.RED);
        }else{
            rect_AMudar.setFill(Color.RED);
            rectSemMudar.setFill(Color.GREEN);
        }

    }
}
