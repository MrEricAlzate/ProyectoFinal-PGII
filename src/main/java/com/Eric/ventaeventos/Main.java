package com.Eric.ventaeventos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Título de la ventana
        primaryStage.setTitle("Plataforma de Venta de Entradas - PGII");

        // Mensaje de bienvenida (temporal)
        Label label = new Label("¡Bienvenido al Proyecto Final de Programación II!\n\n" +
                "Estamos construyendo la plataforma de eventos.\n" +
                "Próximamente: Login, selección de eventos, etc.");

        VBox root = new VBox(20, label);
        root.setStyle("-fx-padding: 50; -fx-alignment: center;");

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);   // Inicia JavaFX
    }
}