package com.example.pt2022_30424_iaz_ania_assigment4;

import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.dataLayer.SerializeData;
import com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer.EmployeeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * when the application stops every information is stored with the help of a singleton
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        //serialization
        DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
        SerializeData.writeDataMenuItem(deliveryServiceProcessing.getMenuItemList());
        SerializeData.writeUser(deliveryServiceProcessing.getUserList());
        //System.out.println(deliveryServiceProcessing.getOrders().size());
        SerializeData.writeHashMap(deliveryServiceProcessing.getOrders());
        super.stop();
    }

    /**
     * when the application start the information stored in files is read
     * @param args
     */
    public static void main(String[] args) {
        //deserialization
        DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
        deliveryServiceProcessing.setUserList(SerializeData.readDataUser());
        deliveryServiceProcessing.setMenuItemListCSV(SerializeData.readDataMenuItem());
        deliveryServiceProcessing.setOrders(SerializeData.readDataOrders());
        System.out.println(deliveryServiceProcessing.getOrders().size());
        launch();
    }
}