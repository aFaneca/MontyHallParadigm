package GUI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Modelo.Porta;
import Modelo.Teste;
import Modelo.Testes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class janelaTestesController implements Initializable{
    Testes testes;
    Stage stage;
    boolean testeTerminado = false;
    private Image img_porta;
    private Image img_carro;
    private Image img_cabra;

    @FXML
    private BorderPane bp_painelPrincipal; // Painel Principal

    @FXML
    private HBox hBox_portas; // HBOX adicionada no CENTRO do painel principal

    @FXML
    private HBox hBox_decisaoFinalPorta;

    @FXML
    private VBox vBox_CENTRO;


    @FXML
    private Label label_infoTopo; // Label adicionada no TOPO do painel principal

    @FXML
    private Label label_resultadoDoTeste; // Encontrou porta com carro ou não?

    @FXML
    private Button botao_manterEscolhaInicial;

    @FXML
    private Button botao_alterarEscolhaInicial;

    @FXML
    private Button botao_tentarNovamente;

    @FXML
    private StackPane sp_CentroBaixo;

    private List<ImageView> img_portas;

    private boolean terminarTestes;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        terminarTestes = false;
        img_porta = new Image(new File("Recursos/Imagens/porta_fechada.png").toURI().toString(), true);
        img_carro = new Image(new File("Recursos/Imagens/porta_carro.png").toURI().toString(), true);
        img_cabra = new Image(new File("Recursos/Imagens/porta_cabra.png").toURI().toString(), true);


        testes = new Testes(1, 3);

        img_portas = new ArrayList<>(3);

        for(int i = 0; i < 3; i++){
            img_portas.add(new ImageView(img_porta));
            addPorta(img_portas.get(img_portas.size() - 1));
        }

        hBox_decisaoFinalPorta.setVisible(false);
        label_resultadoDoTeste.setVisible(false);

        verificaPortas();
        processaInteracoesPortas();

    }

    private void novoTeste(){
        testes.addTeste();
        resetDisplay();
    }

    private void resetDisplay() {
        label_resultadoDoTeste.setVisible(false);
        resetDisplayPortas();
    }

    private void resetDisplayPortas() {
        for (ImageView img : img_portas) {
            img.setImage(img_porta);
            img.getStyleClass().clear();
            img.getStyleClass().add("img_porta");
        }
    }

    private void verificaPortas(){

        Thread thread = new Thread(){
            public void run(){
            while(!terminarTestes){
                Porta porta;
                while(!testes.getTesteAtual().isTerminado()) {
                    for (ImageView img : img_portas) {
                        porta = testes.getTesteAtual().getPortas().get(getIndiceDeImg(img));
                        if (porta.isSelecionada()) img.getStyleClass().add("img_portaSelecionada");
                        img.setImage(img_porta);
                    }

                }

                for(ImageView img : img_portas){
                    porta = testes.getTesteAtual().getPortas().get(getIndiceDeImg(img));
                    if(porta.isTemCarro())
                        img.setImage(img_carro);
                    else
                        img.setImage(img_cabra);
                }
            }


                Thread.currentThread().interrupt();
                return;
                }

        };

        thread.start();

    }


    @FXML
    private void acao_botaoTentarNovamente(){
        novoTeste();
    }


    @FXML
    private void acao_botaoManterEscolhaInicial(){
        testes.getTesteAtual().setTerminado(true);
        hBox_decisaoFinalPorta.setVisible(false);
        testes.getTesteAtual().setEscolhaFinal2(true);
        fazerRevelacaoFinal();

        label_resultadoDoTeste.setVisible(true);
    }


    @FXML
    private void acao_botaoAlterarEscolhaInicial(){
        testes.getTesteAtual().setTerminado(true);
        hBox_decisaoFinalPorta.setVisible(false);
        testes.getTesteAtual().setEscolhaFinal2(false);
        fazerRevelacaoFinal();
    }

    private void fazerRevelacaoFinal() {
        hBox_decisaoFinalPorta.setVisible(false);


        if(!testes.getTesteAtual().getEscolhaFinal().isTemCarro())
            label_resultadoDoTeste.setText("Oops! Escolheu a porta errada...");
        label_resultadoDoTeste.setVisible(true);
        //atualizaPortas();
        //System.out.println(testes.getTesteAtual().getPortas());
    }



    private void processaInteracoesPortas() {
        final Porta[] porta = new Porta[1];
        final Teste[] teste = new Teste[1];
        for(ImageView img : img_portas){
            img.setOnMouseClicked((MouseEvent e) -> {
                teste[0] = testes.getTesteAtual();
                porta[0] = teste[0].getPortas().get(getIndiceDeImg(img));

                // INICIO - SELECAO INICAL DE PORTA
                if(teste[0].getEscolhaInicial() == null){
                    if(!porta[0].isSelecionada()){
                        if(porta[0].isTemCarro())
                            System.out.println("Tem Carro.");
                        else
                            System.out.println("Não tem Carro.");
                    }
                    // A porta selecionada é a escolha inicial
                    testes.getTesteAtual().setEscolhaInicial(testes.getTesteAtual().getPortas().get(getIndiceDeImg(img)));



                    // Chamada da ação de seleção de porta pelo apresentador
                    acao_apresentadorAbrePorta(teste[0]);
                }
                //if(porta[0].isSelecionada()) img.getStyleClass().add("img_portaSelecionada");
            });
        }
    }

    private void acao_apresentadorAbrePorta(Teste teste) {
        teste.acao_escolhaDoApresentador(); // Apresentador faz escolha de porta
        acao_DecisaoFinal();
    }

    private void acao_DecisaoFinal() {
        hBox_decisaoFinalPorta.setVisible(true);
    }

    private int getIndiceDeImg(ImageView img){
        return img_portas.indexOf(img);
    }

    private void addPorta(ImageView img){
        img.setPreserveRatio(true);
        img.setFitHeight(200);
        img.getStyleClass().add("img_porta");
        img.setPickOnBounds(true);
        hBox_portas.setMargin(img, new Insets(10, 50, 50, 50));
        hBox_portas.getChildren().add(img);
    }

}