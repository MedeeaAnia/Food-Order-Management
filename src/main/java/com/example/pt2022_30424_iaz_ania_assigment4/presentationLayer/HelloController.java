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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Button register;
    @FXML
    private Button logIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();

    public void goToRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = (Stage) register.getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkClient(String username, String password){
        for(User user: deliveryServiceProcessing.getUserList()){
            if(user.checkPassword(password) && user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    /**
     * this method decides depending on the user which screen to be displayed
     * @throws IOException
     */
    public void goToUser() throws IOException {
        User admin = new User("admin@admin","admin123", Role.ADMIN);
        User employee = new User("employee@employee","employee1234",Role.EMPLOYEE);
        if(admin.checkPassword(password.getText()) && admin.getUsername().equals(username.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 780, 440);
            Stage stage = (Stage) logIn.getScene().getWindow();
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
        }
        else if(employee.checkPassword(password.getText()) && employee.getUsername().equals(username.getText())){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employee.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 440);
            Stage stage = (Stage) logIn.getScene().getWindow();
            stage.setTitle("Employee");
            stage.setScene(scene);
            stage.show();
        }
        else if(checkClient(username.getText(),password.getText())){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 820, 510);
            Stage stage = (Stage) logIn.getScene().getWindow();
            ClientController clientController = fxmlLoader.getController();
            User user = new User(username.getText(), password.getText(),Role.CLIENT);
            clientController.setUser(user);
            stage.setTitle("Client");
            stage.setScene(scene);
            stage.show();
        }
        else{
            noUser();
        }
    }

    public void noUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("User does not exist");
        alert.setHeaderText("Please register before you log in");
        alert.setContentText("Or reinsert the username/password");
        alert.showAndWait();
    }
}