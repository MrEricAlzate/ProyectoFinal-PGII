package com.Eric.ventaeventos;

import com.Eric.ventaeventos.repository.DataInitializer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Plataforma de Venta de Entradas - PGII");

        // Probamos el DataInitializer
        DataInitializer data = DataInitializer.getInstance();
        data.mostrarDatos();

        Label label = new Label("✅ Sistema iniciado correctamente!\n\n" +
                "Datos de prueba cargados.\n" +
                "Revisa la consola para ver los eventos y usuarios.");

        VBox root = new VBox(20, label);
        root.setStyle("-fx-padding: 50; -fx-alignment: center;");

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}