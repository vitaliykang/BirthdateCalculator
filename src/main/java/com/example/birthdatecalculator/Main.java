package com.example.birthdatecalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Нумерологический калькулятор");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}