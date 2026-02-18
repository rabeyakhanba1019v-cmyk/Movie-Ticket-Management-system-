package bd.edu.seu.background;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdmindashboardController implements Initializable {
    @FXML
    private TableColumn<Movie, String> listmoviedate;

    @FXML
    private TableColumn<Movie, String> listmoviename;

    @FXML
    private TableColumn<Movie, Number> listmovieprice;

    @FXML
    private TableColumn<Movie, Number> listmoviequantity;

    @FXML
    private TableColumn<Movie, String> listmovieratings;

    @FXML
    private TableColumn<Movie, String> listmovietime;

    @FXML
    private TableView< Movie> listofmoviesview;

    @FXML
    private TableView<Customer> mymovie;

    @FXML
    private TableColumn<Customer, String> mymoviedate;

    @FXML
    private TableColumn<Customer, String> mymoviename;

    @FXML
    private TableColumn<Customer, String> mymovienames;

    @FXML
    private TableColumn<Customer,   Number> mymovieprice;

    @FXML
    private TableColumn<Customer, Number> mymoviequantity;

    @FXML
    private TableColumn<Customer, String> mymovieratings;

    @FXML
    private TableColumn<Customer, String> mymovietime;

    @FXML
    private TableView<Superpoints> ratings;

    @FXML
    private TableColumn<Superpoints, String> ratingsname;

    @FXML
    private TableColumn<Superpoints, Number> ratingspoints;
    @FXML
    void returntohomescene(ActionEvent event) {
        HelloApplication.changescene("homescene");
    }
    @FXML
    void addDeleteUpdate(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Admin Access");
        dialog.setHeaderText("Enter Password");
        dialog.setContentText("Please enter the admin password:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals("admin123")) {
            HelloApplication.changescene("update");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Access Denied");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect password. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    void allpurchaselist(ActionEvent event) {
        mymovienames.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getName()));
        mymovieprice.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPrice()));
        mymoviedate.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDate()));
        mymoviename.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getMoviename()));
        mymovietime.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTime()));
        mymoviequantity.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getQuantity()));
        mymovieratings.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getGenre()));
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        List<Customer> productList =savedataindatbase.getCustomerPurchases();
        ObservableList<Customer> productObservableList = FXCollections.observableArrayList();
        productObservableList.addAll(productList);
        mymovie.setItems(productObservableList);

        listofmoviesview.setVisible(false);
        mymovie.setVisible(true);
        ratings.setVisible(false);
    }

    @FXML
    void showallpoints(ActionEvent event) {
        listofmoviesview.setVisible(false);
        mymovie.setVisible(false);
        ratings.setVisible(true);
        ratingsname.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        ratingspoints.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getPoints()));
        Savedataindatbase savedata = new Savedataindatbase();
        List<Superpoints> productList1 =savedata.getsuperpointPurchases();
        ObservableList<Superpoints> productObservableList1 = FXCollections.observableArrayList();
        productObservableList1.addAll(productList1);
        ratings.setItems(productObservableList1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
  listofmoviesview.setVisible(true);
        ratings.setVisible(false);
        mymovie.setVisible(false);
        listmovieprice.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPrice()));
        listmoviedate.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDate()));
        listmoviename.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getMoviename()));
        listmovietime.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTime()));
        listmoviequantity.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getQuantity()));
        listmovieratings.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getGenre()));
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        List<Movie> productList =savedataindatbase.getProductList();
        ObservableList<Movie> productObservableList = FXCollections.observableArrayList();
        productObservableList.addAll(productList);
        listofmoviesview.setItems(productObservableList);



    }
}
