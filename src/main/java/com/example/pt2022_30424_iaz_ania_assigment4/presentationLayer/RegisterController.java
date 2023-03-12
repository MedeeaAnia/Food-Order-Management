package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.model.Role;
import com.example.pt2022_30424_iaz_ania_assigment4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private Button register;
    @FXML
    private Button goBack;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();


    /**check if user already exists
     * @param users
     * @return
     */
    public boolean checkUsername(ArrayList<User> users){
        for(User user: users){
            if(user.getUsername().equals(username.getText()))
                return false;
        }
        return true;
    }

    /**if the user is valid we add it to the list
     * @throws IOException
     */
    public void goToLogIn() throws IOException {
        if(!password.getText().isEmpty() && !password.getText().isEmpty() && !confirmPassword.getText().isEmpty()) {
            if (password.getText().equals(confirmPassword.getText())) {
                if (checkUsername((ArrayList<User>) deliveryServiceProcessing.getUserList()) == false) {
                    usernameExists();
                } else {
                    User user = new User(username.getText(), password.getText(), Role.CLIENT);
                    deliveryServiceProcessing.addClient(user);
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 350);
                    Stage stage = (Stage) register.getScene().getWindow();
                    stage.setTitle("Log In");
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                passwordNotCorresponding();
            }
        }
        else{
            somethingIsMissing();
        }
    }

    public void goBackToLogIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }


    public void passwordNotCorresponding() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect password");
        alert.setHeaderText("Please reinsert the password");
        alert.setContentText("The passwords do not correspond");
        alert.showAndWait();
    }

    public void usernameExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect username");
        alert.setHeaderText("Please reinsert the username");
        alert.setContentText("This username already exists");
        alert.showAndWait();
    }
    public void somethingIsMissing() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Please complete all the fields");
        alert.setContentText("Then register");
        alert.showAndWait();
    }

}
