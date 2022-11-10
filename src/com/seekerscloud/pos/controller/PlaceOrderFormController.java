package com.seekerscloud.pos.controller;

import com.seekerscloud.pos.db.Database;
import com.seekerscloud.pos.model.Customer;
import com.seekerscloud.pos.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class PlaceOrderFormController {
    public AnchorPane placeOrderContext;
    public ComboBox<String> cmbCustomerCodes;
    public ComboBox<String> cmbItemCodes;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;

    public void initialize() {
        loadCustomerIds();
        loadItemCodes();

        //==============Listeners============
            cmbCustomerCodes.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        setCustomerData(newValue);
                    });
        cmbItemCodes.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setProductData(newValue);
                });
        //==============Listeners============

    }

    private void setProductData(String code) {
        Product product = Database.productTable.stream().filter(e -> e.getCode().equals(code))
                .findFirst().orElse(null);
        if (product!=null){
            txtDescription.setText(product.getDescription());
            txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
        }
    }

    private void setCustomerData(String id) {
        Stream<Customer> customerList =
                Database.customerTable.stream().filter(e -> e.getId().equals(id));
        Optional<Customer> first = customerList.findFirst();
        if (first.isPresent()){
            Customer customer = first.get();
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtSalary.setText(String.valueOf(customer.getSalary()));
        }

    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (Customer c : Database.customerTable
        ) {
            obList.add(c.getId());
        }
        cmbCustomerCodes.setItems(obList);
    }

    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (Product p : Database.productTable
        ) {
            obList.add(p.getCode());
        }
        cmbItemCodes.setItems(obList);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm", "Dashboard");
    }

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) placeOrderContext.getScene().getWindow();
        window.setTitle(title);
        window.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))
        );
    }
}
