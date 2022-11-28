package com.example.databasesql.Controllers;

import java.io.IOException;

import com.example.databasesql.client.Client;
import com.example.databasesql.client.ClientApplication;
import com.example.databasesql.model.Furniture;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.databasesql.client.RequestName.*;

public class ClientController {
    static Client client;
    private static ObservableList<Furniture> list;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label furnitureLabel;
    @FXML
    private TableView<Furniture> tableView;
    @FXML
    private TableColumn<Furniture, String> nameColumn;
    @FXML
    private TableColumn<Furniture, Double> priceColumn;
    @FXML
    private TableColumn<Furniture, String> materialColumn1;
    @FXML
    private TableColumn<Furniture, Double> weightColumn;
    @FXML
    private TableColumn<Furniture, Double> widthColumn;
    @FXML
    private TableColumn<Furniture, Double> lengthColumn;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        client = new Client(4456);

        list = client.getList();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        materialColumn1.setCellValueFactory(new PropertyValueFactory<>("material"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        tableView.setItems(list);

        addButton.setOnAction(addButtonAction());
        deleteButton.setOnAction(deleteButtonAction());
        editButton.setOnAction(editButtonAction());
    }

    private EventHandler<ActionEvent> editButtonAction() {
        return (actionEvent -> {
            try {
                Furniture furniture = tableView.getSelectionModel().getSelectedItem();

                FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource("/com/example/databasesql/edit.fxml"));
                EditController controller = new EditController(furniture);
                loader.setController(controller);
                Scene scene = new Scene(loader.load(), 250, 350);
                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                tableView.setItems(list);
            } catch (IOException e) {
                System.out.println("Выберите элемент");
            }
        });
    }

    private EventHandler<ActionEvent> deleteButtonAction() {
        return (actionEvent -> {
            Furniture furniture = tableView.getSelectionModel().getSelectedItem();
            System.out.println(furniture);
            client.sendRequest(DELETE, furniture);
            list = client.getList();
            tableView.setItems(list);
            System.out.println("Запись удалена");
        });
    }

    private EventHandler<ActionEvent> addButtonAction() {
        return (actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/com/example/databasesql/add.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 250, 350);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.showAndWait();
                tableView.setItems(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void addFurniture(Furniture furniture) {
        client.sendRequest(ADD, furniture);
        list = client.getList();
        System.out.println("Список обновлён");
    }

    public static void editFurniture(Furniture furniture) {
        client.sendRequest(UPDATE, furniture);
        list = client.getList();
        System.out.println("изменено");
    }

}
