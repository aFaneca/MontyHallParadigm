package GUI;

import Modelo.Experiencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Modelo.*;

public class Main extends Application {

    Scene janelaPrincipal;
    Scene janelaTestes;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent jPrincipalParent = FXMLLoader.load(getClass().getResource("janelaPrincipal.fxml"));
        Parent jTestesParent = FXMLLoader.load(getClass().getResource("janelaTestes.fxml"));
        janelaPrincipal = new Scene(jPrincipalParent, 1500, 800);
        janelaTestes = new Scene(jTestesParent, 1500, 800);
        primaryStage.setTitle("O Paradigma de Monty Hall - Tira-teimas!");
        primaryStage.setScene(janelaTestes);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}
