package GUI;

import Modelo.Experiencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Modelo.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("janelaPrincipal.fxml"));
        primaryStage.setTitle("O Paradigma de Monty Hall - Tira-teimas!");
        primaryStage.setScene(new Scene(root, 1500, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Experiencia exp = new Experiencia(2,1,3);
        //Estatistica stat = new Estatistica(1,1,3);
        launch(args);


    }
}
