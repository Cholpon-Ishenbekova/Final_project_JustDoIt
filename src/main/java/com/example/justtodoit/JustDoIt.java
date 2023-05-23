package com.example.justtodoit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JustDoIt extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JustDoIt.class.getResource("JustDo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 560, 393);
        JustDoItController controller = new JustDoItController();
        fxmlLoader.setController(controller);
        stage.setTitle("Just Do It");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}