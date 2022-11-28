package com.example.databasesql.Controllers;

import com.example.databasesql.Controllers.ClientController;
import com.example.databasesql.model.Furniture;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
    @FXML
    private Label errorLabel;
    @FXML
    private Label labelName;
    @FXML
    private Button addButton;
    @FXML
    private Label lengthLabel;

    @FXML
    private TextField lengthTextField;

    @FXML
    private Label materialLabel;

    @FXML
    private TextField materialTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField priceTextField;

    @FXML
    private Label weightLabel;

    @FXML
    private TextField weightTextField;

    @FXML
    private Label widthLabel;

    @FXML
    private TextField widthTextField;

    @FXML
    void initialize() {
        addButton.setOnAction(addFurniture());
    }

    private EventHandler<ActionEvent> addFurniture() {
        return (actionEvent -> {
            try{
                if(nameTextField.getText().trim().isEmpty()
                        || priceTextField.getText().trim().isEmpty()
                        || materialTextField.getText().trim().isEmpty()
                        || weightTextField.getText().trim().isEmpty()
                        || widthTextField.getText().trim().isEmpty()
                        || lengthTextField.getText().trim().isEmpty()
                ){
                    errorLabel.setText("Fields must be filled");
                }else{
                    Furniture furniture = new Furniture(
                            nameTextField.getText(),
                            Double.parseDouble(priceTextField.getText()),
                            materialTextField.getText(),
                            Double.parseDouble(weightTextField.getText()),
                            Double.parseDouble(widthTextField.getText()),
                            Double.parseDouble(lengthTextField.getText())
                    );
                    ClientController.addFurniture(furniture);
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                }
            }catch (NumberFormatException e){
                errorLabel.setText("wrong format");
            }
        });
    }

}
