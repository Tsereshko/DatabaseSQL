package com.example.databasesql.Controllers;

import com.example.databasesql.Controllers.ClientController;
import com.example.databasesql.model.Furniture;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditController {
    Furniture furniture;

    EditController(Furniture furniture) {
        this.furniture = furniture;
    }

    @FXML
    private VBox labelVBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Label labelName;
    @FXML
    private Button editButton;
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
        editButton.setOnAction(editButtonAction());

        System.out.println(furniture);

        nameTextField.setText(furniture.getName());
        priceTextField.setText(String.valueOf(furniture.getPrice()));
        materialTextField.setText(furniture.getMaterial());
        weightTextField.setText(String.valueOf(furniture.getWeight()));
        widthTextField.setText(String.valueOf(furniture.getWidth()));
        lengthTextField.setText(String.valueOf(furniture.getLength()));
    }

    private EventHandler<ActionEvent> editButtonAction() {
        return (actionEvent -> {
            try {
                if (nameTextField.getText().trim().isEmpty()
                        || priceTextField.getText().trim().isEmpty()
                        || materialTextField.getText().trim().isEmpty()
                        || weightTextField.getText().trim().isEmpty()
                        || widthTextField.getText().trim().isEmpty()
                        || lengthTextField.getText().trim().isEmpty()
                ) {
                    errorLabel.setText("Fields must be filled");
                } else {
                    furniture.setName(nameTextField.getText());
                    furniture.setPrice(Double.parseDouble(priceTextField.getText()));
                    furniture.setMaterial(materialTextField.getText());
                    furniture.setWeight(Double.parseDouble(weightTextField.getText()));
                    furniture.setWidth(Double.parseDouble(widthTextField.getText()));
                    furniture.setLength(Double.parseDouble(lengthTextField.getText()));
                    ClientController.editFurniture(furniture);
                    Stage stage = (Stage) editButton.getScene().getWindow();
                    stage.close();
                }
            }catch(NumberFormatException e){
                errorLabel.setText("wrong format");
            }
        });
    }

}
