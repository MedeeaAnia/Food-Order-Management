package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddMenuController {

    private static ArrayList<MenuItem> menuItems = new ArrayList<>();

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        AddMenuController.menuItems = menuItems;
    }

    @FXML
    private Button back;
    @FXML
    private Button add;
    @FXML
    private TextField title;
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();


    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 440);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }
    public boolean validateName(String s){
        for(MenuItem menuItem: deliveryServiceProcessing.getMenuItemList()){
            if(menuItem.title().equals(s))
                return false;
        }
        return true;
    }


    /**
     * name the composed product and add it to the list
     * @throws IOException
     */
    public void addMenuItem() throws IOException {
        if(menuItems != null) {
            if (!title.getText().isEmpty()) {
                if (validateName(title.getText())) {
                    deliveryServiceProcessing.createMenuItem(title.getText(), menuItems);
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 780, 440);
                    Stage stage = (Stage) add.getScene().getWindow();
                    stage.setTitle("Admin");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    nameExists();
                }
            }
            else{
                noName();
            }
        }
    }

    public void noName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid name");
        alert.setHeaderText("Please reinsert the name");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void nameExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid name");
        alert.setHeaderText("Please reinsert the name");
        alert.setContentText("Product already exists");
        alert.showAndWait();
    }
}
