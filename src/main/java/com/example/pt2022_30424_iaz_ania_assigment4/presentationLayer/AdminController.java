package com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.HelloApplication;
import com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer.DeliveryServiceProcessing;
import com.example.pt2022_30424_iaz_ania_assigment4.dataLayer.SerializeData;
import com.example.pt2022_30424_iaz_ania_assigment4.model.BaseProduct;
import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class AdminController {

    @FXML
    private TableView tableProducts;
    @FXML
    private Button logOut;
    @FXML
    private Button addPrd;
    @FXML
    private Button modifyPrd;
    @FXML
    private Button createMenu;
    @FXML
    private TextField minTime;
    @FXML
    private TextField maxTime;
    @FXML
    private TextField prodNo;
    @FXML
    private TextField clientNo;
    @FXML
    private TextField day;
    @FXML
    private TextField amount;
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
    private static int addOrEdit = -1;

    public int getAddOrEdit() {
        return addOrEdit;
    }

    public void initialize() {
        tableProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        tableProducts.getItems().setAll(deliveryServiceProcessing.getMenuItemList());

    }

    public boolean validateIntNumber(String s) {
        final String VERIFY_NUMBERS = "([0-9]+)";
        Pattern pattern = Pattern.compile(VERIFY_NUMBERS);
        if (pattern.matcher(s).matches()) {
            return true;
        }
        return false;
    }


    /**
     * loads products from a csv file
     */
    public void loadProducts() {
        try {
            deliveryServiceProcessing.loadProducts("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\products.csv");
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove the selected product
     */
    public void deleteProduct() {
        if (tableProducts.getSelectionModel().getSelectedItem() != null) {
            deliveryServiceProcessing.removeProduct((MenuItem) tableProducts.getSelectionModel().getSelectedItem());
        } else {
            nullSelection();
        }

    }

    /**
     * go to add product screen
     * @throws IOException
     */
    public void addProduct() throws IOException {
        addOrEdit = 0;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEditProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        Stage stage = (Stage) addPrd.getScene().getWindow();
        stage.setTitle("Add or Edit");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * we go to a screen that lets us modify the fields of a product and add it to the list
     * @throws IOException
     */
    public void modifyProduct() throws IOException {
        addOrEdit = 1;
        if (tableProducts.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEditProduct.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 500);
            Stage stage = (Stage) modifyPrd.getScene().getWindow();
            AddEditController addEditController = fxmlLoader.getController();
            addEditController.setMenuItemToEdit((MenuItem) tableProducts.getSelectionModel().getSelectedItem());
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEditProduct.fxml"));
            scene = new Scene(fxmlLoader.load(), 400, 500);
            stage = (Stage) modifyPrd.getScene().getWindow();
            stage.setTitle("Add or Edit");
            stage.setScene(scene);
            stage.show();
        } else {
            nullSelection();
        }

    }

    /**
     * we create a composed product if validations are passed
     * @throws IOException
     */
    public void createMenuItem() throws IOException {
        if (tableProducts.getSelectionModel().getSelectedItem() != null) {
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            menuItems = new ArrayList<>(tableProducts.getSelectionModel().getSelectedItems());
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 200);
            Stage stage = (Stage) createMenu.getScene().getWindow();
            AddMenuController addMenuController = fxmlLoader.getController();
            addMenuController.setMenuItems(menuItems);
            stage.setTitle("Create new menu");
            stage.setScene(scene);
            stage.show();
        } else {
            nullSelection();
        }
    }

    /**
     * generates four different reports based on the information introduced about the orders
     */
    public void generateReport() {
        if ((!minTime.getText().isEmpty() && maxTime.getText().isEmpty()) || (!maxTime.getText().isEmpty() && minTime.getText().isEmpty())) {
            minMax();
        }
        if (minTime.getText().isEmpty() && maxTime.getText().isEmpty()
                && prodNo.getText().isEmpty() && clientNo.getText().isEmpty() && day.getText().isEmpty() && amount.getText().isEmpty()) {
            noField();
        } else {
            if (!minTime.getText().isEmpty() && !maxTime.getText().isEmpty()) {
                if (Integer.valueOf(minTime.getText()) > Integer.valueOf(maxTime.getText())) {
                    minimumBigger();
                } else if (validateIntNumber(minTime.getText()) && validateIntNumber(maxTime.getText()) &&
                        Integer.valueOf(minTime.getText())<=24 && Integer.valueOf(maxTime.getText()) <=24) {
                    //report1
                    deliveryServiceProcessing.reportTimeInterval(Integer.valueOf(minTime.getText()),Integer.valueOf(maxTime.getText()));
                } else {
                    negativeNumbers();
                }
            }
            if (!prodNo.getText().isEmpty()) {
                if (validateIntNumber(prodNo.getText())) {
                    //report2
                    deliveryServiceProcessing.reportProducts(Integer.parseInt(prodNo.getText()));
                } else {
                    negativeNumbers();
                }
            }
            if (!clientNo.getText().isEmpty()) {
                if (validateIntNumber(clientNo.getText()) && validateIntNumber(amount.getText())) {
                    //report3
                    deliveryServiceProcessing.reportClients(Integer.parseInt(clientNo.getText()),Integer.valueOf(amount.getText()));
                } else {
                    negativeNumbers();
                }
            }
            if (!day.getText().isEmpty()) {
                    //report4
                    LocalDate localDate = LocalDate.parse(day.getText());
                    deliveryServiceProcessing.reportDay(localDate);
            }
        }
    }

    public void goToLogIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    public void nullSelection() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No selected product");
        alert.setHeaderText("Please select a product");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void noField() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No field complete");
        alert.setHeaderText("Please complete one or more fields then press report");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void minMax() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Minimum time and maximum time");
        alert.setHeaderText("Please insert a time interval!");
        alert.setContentText("Complete both minimum and maximum");
        alert.showAndWait();
    }

    public void minimumBigger() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Minimum time is bigger");
        alert.setHeaderText("Please insert a smaller minimum time!");
        alert.setContentText("Complete both minimum and maximum");
        alert.showAndWait();
    }

    public void negativeNumbers() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Negative numbers");
        alert.setHeaderText("Please insert only positive numbers");
        alert.setContentText("");
        alert.showAndWait();
    }

    public void invalidData() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid data format");
        alert.setHeaderText("Please reinsert data in format dd-mm-yyyy");
        alert.setContentText("");
        alert.showAndWait();
    }


}
