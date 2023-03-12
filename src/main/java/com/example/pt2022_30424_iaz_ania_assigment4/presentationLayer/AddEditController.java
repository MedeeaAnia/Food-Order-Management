package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.model.BaseProduct;
import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class AddEditController {

    private static MenuItem menuItemToEdit = null;

    @FXML
    private Button backToAdmin;
    @FXML
    private Button addPrd;
    @FXML
    private TextField name;
    @FXML
    private TextField rating;
    @FXML
    private TextField calories;
    @FXML
    private TextField protein;
    @FXML
    private TextField sodium;
    @FXML
    private TextField fat;
    @FXML
    private TextField price;
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();

    public MenuItem getMenuItemToEdit() {
        return menuItemToEdit;
    }

    public void setMenuItemToEdit(MenuItem menuItemToEdit) {
        AddEditController.menuItemToEdit = menuItemToEdit;
    }

    /**
     * if we modify a product this method will load the info in the fields
     */
    public void initialize(){
        if(menuItemToEdit != null){
            name.setText(menuItemToEdit.title());
            rating.setText(String.valueOf(menuItemToEdit.computeRating()));
            calories.setText(String.valueOf(menuItemToEdit.computeCalories()));
            protein.setText(String.valueOf(menuItemToEdit.computeProtein()));
            fat.setText(String.valueOf(menuItemToEdit.computeFat()));
            sodium.setText(String.valueOf(menuItemToEdit.computeSodium()));
            price.setText(String.valueOf(menuItemToEdit.computePrice()));
        }
    }

    public boolean validateDoubleNumber(String s){
        final String VERIFY_NUMBERS = "([0-9]+(\\.[0-9]+)?)";
        Pattern pattern = Pattern.compile(VERIFY_NUMBERS);
        if(pattern.matcher(s).matches()){
            return true;
        }
        return false;
    }

    public boolean validateIntNumber(String s){
        final String VERIFY_NUMBERS = "([0-9]+)";
        Pattern pattern = Pattern.compile(VERIFY_NUMBERS);
        if(pattern.matcher(s).matches()){
            return true;
        }
        return false;
    }

    public void goBack(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 780, 440);
            Stage stage = (Stage) backToAdmin.getScene().getWindow();
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateName(String s){
        for(MenuItem menuItem: deliveryServiceProcessing.getMenuItemList()){
            if(menuItem.title().equals(s))
                return false;
        }
        return true;
    }

    /**
     * if validations are passed we either add or modify a product
     * @throws IOException
     */
    public void addProduct() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 440);
        Stage stage = (Stage) addPrd.getScene().getWindow();
        AdminController adminController = fxmlLoader.getController();
        if(!name.getText().isEmpty() && !calories.getText().isEmpty() &&!rating.getText().isEmpty() && !protein.getText().isEmpty()
        &&!fat.getText().isEmpty() && !sodium.getText().isEmpty()
        &&!price.getText().isEmpty() ){
            if(validateName(name.getText()) == false){
                nameExists();
            }
            else if(validateDoubleNumber(rating.getText()) && validateIntNumber(calories.getText()) &&
            validateIntNumber(protein.getText()) && validateIntNumber(fat.getText()) &&
            validateIntNumber(sodium.getText()) && validateIntNumber(price.getText())) {
                BaseProduct baseProduct = new BaseProduct(name.getText(), Double.parseDouble(rating.getText()),
                        Integer.parseInt(calories.getText()), Integer.parseInt(protein.getText()),
                        Integer.parseInt(fat.getText()), Integer.parseInt(sodium.getText()),
                        Integer.parseInt(price.getText()));
                if (adminController.getAddOrEdit() == 0) {
                    deliveryServiceProcessing.addProduct(baseProduct);
                } else if (adminController.getAddOrEdit() == 1) {
                    deliveryServiceProcessing.modifyProduct(baseProduct, menuItemToEdit);
                    menuItemToEdit = null;
                }
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                scene = new Scene(fxmlLoader.load(), 780, 440);
                stage = (Stage) addPrd.getScene().getWindow();
                stage.setTitle("Admin");
                stage.setScene(scene);
                stage.show();
            }
            else{
                negativeNumbers();
            }
        }
    }

    public void negativeNumbers() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Negative numbers");
        alert.setHeaderText("Please insert only positive numbers");
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
