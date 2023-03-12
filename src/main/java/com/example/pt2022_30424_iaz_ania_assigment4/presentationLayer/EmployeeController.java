package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.model.Order;
import com.example.pt2022_30424_iaz_ania_assigment4.model.OrderTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EmployeeController implements Observer {
    @FXML
    private Button back;
    @FXML
    private TableView tableOrders;
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();

    /**
     * in this method the table with the orders is completed
     */
    public void initialize(){

        List<TableColumn<Order, Integer>> columnList = new ArrayList<>();
        TableColumn<Order, Integer> column1 = new TableColumn<>("Username");
        column1.setCellValueFactory(new PropertyValueFactory<>("Username"));
        TableColumn<Order, Integer> column2 = new TableColumn<>("Date");
        column2.setCellValueFactory(new PropertyValueFactory<>("Date"));
        TableColumn<Order, Integer> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnList.add(column1);
        columnList.add(column2);
        columnList.add(column3);
        tableOrders.getColumns().addAll(columnList);
        ArrayList<OrderTableView> orders = new ArrayList<>();
        for(Order order: deliveryServiceProcessing.getOrders().keySet()){
           OrderTableView orderTableView = new OrderTableView(order.getUser(), order.getDate(), order.getPrice());
            orders.add(orderTableView);
        }
        tableOrders.getItems().setAll(orders);
    }

    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * message is displayed when an order is made
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        System.out.println("An order was made at "+order.getDate());
    }
}
