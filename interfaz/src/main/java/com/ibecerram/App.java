package com.ibecerram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

/**
 * Clase principal donde se desarrolla el proceso de la aplicación
 *
 */
public class App extends Application
{
    /**
     * Método que permite cargar los datos de la aplicación.
     * @param args String[]
     */
    public static void main( String[] args )
    {
        launch(args);
    }

    /**
     * Carga la escena contenida en el archivo .fxml para su viasualización.
     * @param primaryStage Modificado para agregar una escena.
     * @throws Exception Retorna una Exception en caso de algún error.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:interfaz/src/main/resources/interfazApp.fxml"));
        primaryStage.setTitle("Correos Masivos");
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 1200, 700);
        //scene.getStylesheets().add("styleLight.css");
        //File file2 = new File("/home/user/Desktop/POO/Unidad2/Act1U2/src/main/java/com/ibecerram/styleLight.css");
        //scene.getStylesheets().add("/stylesepia.css");
        scene.getStylesheets().add("file:interfaz/target/classes/estilos.css");
        //scene.getStylesheets().add("file:interfaz/target/classes/dark.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}