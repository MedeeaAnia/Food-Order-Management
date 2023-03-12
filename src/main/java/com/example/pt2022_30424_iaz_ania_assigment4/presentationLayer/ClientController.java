package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.model.BaseProduct;
import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;
import com.example.pt2022_30424_iaz_ania_assigment4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ClientController {
    @FXML
    private Button logOut;
    @FXML
    private TableView tableProducts;
    @FXML
    private TextField searchKey;
    @FXML
    private TextField minRat;
    @FXML
    private TextField maxRat;
    @FXML
    private TextField minCal;
    @FXML
    private TextField maxCal;
    @FXML
    private TextField minPro;
    @FXML
    private TextField maxPro;
    @FXML
    private TextField minFat;
    @FXML
    private TextField maxFat;
    @FXML
    private TextField minSod;
    @FXML
    private TextField maxSod;
    @FXML
    private TextField minPrice;
    @FXML
    private TextField maxPrice;
    @FXML
    private Button order;

    List<MenuItem> boughtItems = new ArrayList<>();
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
    static User user = new User();

    public void setUser(User user) {
        ClientController.user = user;
    }

    /**
     * validates if the user introduces integer positive numbers
     * @param s
     * @return
     */
    public boolean validateIntNumber(String s) {
        final String VERIFY_NUMBERS = "([0-9]+)";
        Pattern pattern = Pattern.compile(VERIFY_NUMBERS);
        if (pattern.matcher(s).matches()) {
            return true;
        }
        return false;
    }

    /**
     * validates if the user introduced positive real numbers
     * @param s
     * @return
     */
    public boolean validateDoubleNumber(String s) {
        final String VERIFY_NUMBERS = "([0-9]+(\\.[0-9]+)?)";
        Pattern pattern = Pattern.compile(VERIFY_NUMBERS);
        if (pattern.matcher(s).matches()) {
            return true;
        }
        return false;
    }

    public void initialize() {
        List<TableColumn<BaseProduct, Integer>> columnList = new ArrayList<>();
        TableColumn<BaseProduct, Integer> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("Title"));

        TableColumn<BaseProduct, Integer> column2 = new TableColumn<>("Rating");
        column2.setCellValueFactory(new PropertyValueFactory<>("Rating"));

        TableColumn<BaseProduct, Integer> column3 = new TableColumn<>("Calories");
        column3.setCellValueFactory(new PropertyValueFactory<>("Calories"));

        TableColumn<BaseProduct, Integer> column4 = new TableColumn<>("Protein");
        column4.setCellValueFactory(new PropertyValueFactory<>("Protein"));

        TableColumn<BaseProduct, Integer> column5 = new TableColumn<>("Fat");
        column5.setCellValueFactory(new PropertyValueFactory<>("Fat"));

        TableColumn<BaseProduct, Integer> column6 = new TableColumn<>("Sodium");
        column6.setCellValueFactory(new PropertyValueFactory<>("Sodium"));

        TableColumn<BaseProduct, Integer> column7 = new TableColumn<>("Price");
        column7.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnList.add(column1);
        columnList.add(column2);
        columnList.add(column3);
        columnList.add(column4);
        columnList.add(column5);
        columnList.add(column6);
        columnList.add(column7);
        tableProducts.getColumns().addAll(columnList);
        tableProducts.getItems().addAll(deliveryServiceProcessing.getMenuItemList());
        load();
    }

    public void goToLogIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method loads products from a csv file
     */
    public void loadProducts() {
        try {
            deliveryServiceProcessing.loadProducts("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\products.csv");
            tableProducts.getItems().addAll(deliveryServiceProcessing.getMenuItemList());
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method has some validation referring to the fields that are not null
     * a user can perform a search based on one or more criteria
     */
    public void search() {
        ArrayList<MenuItem> foundItems = new ArrayList<>();
        double minR = -100.0, maxR = -100.0;
        int minC = -100, maxC = -100;
        int minP = -100, maxP = -100;
        int minF = -100, maxF = -100;
        int minS = -100, maxS = -100;
        int minPr = -100, maxPr = -100;
        if (searchKey.getText().isEmpty() &&
                minRat.getText().isEmpty() && maxRat.getText().isEmpty() &&
                minCal.getText().isEmpty() && maxCal.getText().isEmpty() &&
                minPro.getText().isEmpty() && maxPro.getText().isEmpty() &&
                minFat.getText().isEmpty() && maxFat.getText().isEmpty() &&
                minSod.getText().isEmpty() && maxSod.getText().isEmpty() &&
                minPrice.getText().isEmpty() && maxPrice.getText().isEmpty()) {
            allNull();
        } else {
            if (!minRat.getText().isEmpty())
                if (validateDoubleNumber(minRat.getText()))
                    minR = Double.valueOf(minRat.getText());
                else
                    negativeNumbers();
            if (!maxRat.getText().isEmpty())
                if (validateDoubleNumber(maxRat.getText()))
                    maxR = Double.valueOf(maxRat.getText());
                else
                    negativeNumbers();
            if (!minCal.getText().isEmpty())
                if (validateIntNumber(minCal.getText()))
                    minC = Integer.parseInt(minCal.getText());
                else
                    negativeNumbers();
            if (!maxCal.getText().isEmpty())
                if (validateIntNumber(maxCal.getText()))
                    maxC = Integer.parseInt(maxCal.getText());
                else
                    negativeNumbers();
            if (!minPro.getText().isEmpty())
                if (validateIntNumber(minPro.getText()))
                    minP = Integer.parseInt(minPro.getText());
                else {
                    negativeNumbers();
                }
            if (!maxPro.getText().isEmpty())
                if (validateIntNumber(maxPro.getText()))
                    maxP = Integer.parseInt(maxPro.getText());
                else
                    negativeNumbers();
            if (!minFat.getText().isEmpty())
                if (validateIntNumber(minFat.getText()))
                    minF = Integer.parseInt(minFat.getText());
                else
                    negativeNumbers();
            if (!maxFat.getText().isEmpty())
                if (validateIntNumber(maxFat.getText()))
                    maxF = Integer.parseInt(maxFat.getText());
            if (!minSod.getText().isEmpty())
                if (validateIntNumber(minSod.getText()))
                    minS = Integer.parseInt(minSod.getText());
                else
                    negativeNumbers();
            if (!maxSod.getText().isEmpty())
                if (validateIntNumber(maxSod.getText()))
                    maxS = Integer.parseInt(maxSod.getText());
                else
                    negativeNumbers();
            if (!minPrice.getText().isEmpty())
                if (validateIntNumber(minPrice.getText()))
                    minPr = Integer.parseInt(minPrice.getText());
                else
                    negativeNumbers();
            if (!maxPrice.getText().isEmpty())
                if (validateIntNumber(maxPrice.getText()))
                    maxPr = Integer.parseInt(maxPrice.getText());
                else
                    negativeNumbers();
            foundItems = (ArrayList<MenuItem>) deliveryServiceProcessing.searchingProduct(searchKey.getText(),
                    minR, maxR, minC, maxC, minP, maxP, minF, maxF, minS, maxS, minPr, maxPr
            );
            System.out.println(foundItems.size());
            tableProducts.getItems().setAll(foundItems);
        }
    }


    /**
     * when a client wants to make an order he must first add it to the cart
     */
    public void addToCart() {
        if (tableProducts.getSelectionModel().getSelectedItem() != null) {
            boughtItems.add((MenuItem) tableProducts.getSelectionModel().getSelectedItem());
        } else {
            notSelected();
        }
    }

    /**
     * with the items selected by the user an order is created and added to the hashMap
     * @throws IOException
     */
    public void addOrder() throws IOException {
        if (boughtItems.size() > 0) {
            Date date = new Date();
            //System.out.println(date);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employee.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 440);
            Stage stage = (Stage) order.getScene().getWindow();
            EmployeeController employeeController = fxmlLoader.getController();
            deliveryServiceProcessing.addObserver(employeeController);
            deliveryServiceProcessing.createOrder(boughtItems, user, date);
            boughtItems = null;
        } else {
            notItem();
        }
    }

    public void allNull() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("All fields are null");
        alert.setHeaderText("Please complete at least one fields then press search");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void notSelected() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Product Not Selected");
        alert.setHeaderText("Please select a product then add it to the cart");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void notItem() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No item in the cart");
        alert.setHeaderText("Please select a product then add it to the cart");
        alert.setContentText("After press order");
        alert.showAndWait();
    }

    public void load() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Be careful!");
        alert.setHeaderText("If you press load some information may be lost");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void negativeNumbers() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Negative numbers");
        alert.setHeaderText("Please insert only positive numbers");
        alert.setContentText("");
        alert.showAndWait();
    }
}
