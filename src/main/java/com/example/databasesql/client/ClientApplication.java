package com.example.databasesql.client;

import com.example.databasesql.client.Client;
import com.example.databasesql.model.Furniture;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClientApplication extends Application {
    private static Client client;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/com/example/databasesql/client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}