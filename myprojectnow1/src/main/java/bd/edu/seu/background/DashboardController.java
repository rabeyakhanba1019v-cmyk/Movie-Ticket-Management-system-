package bd.edu.seu.background;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {
   static String  movienames;
    static String  movietimes;
    static String  moviedates;
    static String  moviegenres;
    static String  movieprices;

    @FXML
    private Label moviedatelable;
    @FXML
    private TextField serchfield;

    @FXML
    private Label movienamelable;

    @FXML
    private Label moviepricelable;

    @FXML
    private Label moviequantitylable;

    @FXML
    private Label movietimelable;

    @FXML
    private Label date1;
    @FXML
    private Label nam1;
    @FXML
    private Label price1;

    @FXML
    private Label quantity1;

    @FXML
    private Label time1;
  @FXML
  private Button purchase;


    @FXML
    private TableColumn<Movie,String> listmoviedate;

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
    private TableView<Movie> listofmoviesview;

    @FXML
    private TableView<Customer> mymovie;

    @FXML
    private TableColumn<Customer, String> mymoviedate;

    @FXML
    private TableColumn<Customer, String> mymoviename;

    @FXML
    private TableColumn<Customer, String> mymovienames;

    @FXML
    private TableColumn<Customer, Number> mymovieprice;

    @FXML
    private TableColumn<Customer, Number> mymoviequantity;

    @FXML
    private TableColumn<Customer, String> mymovieratings;

    @FXML
    private TableColumn<Customer, String> mymovietime;

    @FXML
    private Label superpointslable;
    @FXML
    private Label namelable;



    @FXML
    void purchasebutton(ActionEvent event) {
        movienames =nam1.getText();
        moviedates =date1.getText();
        movietimes=time1.getText();
        movieprices=price1.getText();

        HelloApplication.changescene("purchase");

        System.out.println(movienames);

    }
    @FXML
     void ser(KeyEvent keyEvent) {
        String search= serchfield.getText();
       Savedataindatbase sql =new Savedataindatbase();
        List<Movie> productList=sql.getProductList();
        List<Movie> filterList = productList.stream().filter(c -> c.getMoviename().toLowerCase().equals(search.toLowerCase())).collect(Collectors.toList());
        ObservableList<Movie> observableList=FXCollections.observableArrayList();
        observableList.addAll(filterList);
        listofmoviesview.setItems(observableList);
    }

    @FXML
    void recomendation(ActionEvent event) {
        // Get the most frequent genre
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        String mostFrequentGenre = savedataindatbase.getMostFrequentGenre();

        // Display the genre recommendation
        System.out.println("Recommended Genre: " + mostFrequentGenre);

        // Fetch all movies from the database
        List<Movie> movieList = savedataindatbase.getProductList();
        ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

        // Filter movies by the most frequent genre
        for (Movie movie : movieList) {
            if (movie.getGenre().equalsIgnoreCase(mostFrequentGenre)) {
                movieObservableList.add(movie);
            }
        }

        // Set up the table view to display the recommended movies
        listmoviename.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoviename()));
        listmoviedate.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));
        listmovietime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTime()));
        listmovieprice.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPrice()));
        listmoviequantity.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()));
        listmovieratings.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));

        // Set the items in the TableView
        listofmoviesview.setItems(movieObservableList);

        // Update UI visibility for recommendations
        listofmoviesview.setVisible(true);
        superpointslable.setVisible(false);
        date1.setVisible(true);
        time1.setVisible(true);
        nam1.setVisible(true);
        price1.setVisible(true);
        quantity1.setVisible(true);
        moviedatelable.setVisible(true);
        movienamelable.setVisible(true);
        moviepricelable.setVisible(true);
        movietimelable.setVisible(true);
        moviequantitylable.setVisible(true);
        mymovie.setVisible(false);
    }

    @FXML
    void showlist(ActionEvent event) {
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

        mymovie.setVisible(false);
        listofmoviesview.setVisible(true);
        superpointslable.setVisible(false);
        date1.setVisible(true);
        time1.setVisible(true);
        nam1.setVisible(true);
        price1.setVisible(true);
        quantity1.setVisible(true);
        moviedatelable.setVisible(true);
        movienamelable.setVisible(true);
        moviepricelable.setVisible(true);
        movietimelable.setVisible(true);
        moviequantitylable.setVisible(true);
        purchase.setVisible(true);

    }

    @FXML
    void showpoints(ActionEvent event) {
        listofmoviesview.setVisible(false);
        mymovie.setVisible(false);
        superpointslable.setVisible(true);
        date1.setVisible(false);
        time1.setVisible(false);
        nam1.setVisible(false);
        price1.setVisible(false);
        quantity1.setVisible(false);
        moviedatelable.setVisible(false);
        movienamelable.setVisible(false);
        moviepricelable.setVisible(false);
        movietimelable.setVisible(false);
        moviequantitylable.setVisible(false);
        purchase.setVisible(false);

        // Create an instance of Savedataindatbase and fetch the superpoints
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        List<Superpoints> superpointsList = savedataindatbase.getsuperpointIndivisual();

        // Check if the superpointsList is not empty
        if (!superpointsList.isEmpty()) {
            // Get the last element in the list and set the superpoint label
            Superpoints obj = superpointsList.get(superpointsList.size() - 1);
            superpointslable.setText(String.valueOf(obj.getPoints()));
        } else {
            superpointslable.setText("No points available.");
        }

    }

    @FXML
    void showpurchase(ActionEvent event) {
        // Set up the cell value factories for your table
        mymovieprice.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPrice()));
        mymoviedate.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));
        mymoviename.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoviename()));
        mymovietime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTime()));
        mymoviequantity.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()));
        mymovienames.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        mymovieratings.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));

        // Create an instance of Savedataindatbase to retrieve customer purchases
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        List<Customer> productLists = savedataindatbase.getCustomerPurchasesIndivisual();

        // Check if productLists is null or empty
        if (productLists == null || productLists.isEmpty()) {
            // Optionally show a message to the user that no purchases are found
            System.out.println("No purchases found for this user.");

            // Create an empty ObservableList and set it to the table view
            ObservableList<Customer> productObservableLists = FXCollections.observableArrayList();
            mymovie.setItems(productObservableLists);  // Clear the existing data from the table

            // Optionally display a label indicating no purchases found
           // Make a label visible to show "No purchases found"
        } else {
            // If purchases exist, populate the table with the data
            ObservableList<Customer> productObservableLists = FXCollections.observableArrayList();
            productObservableLists.addAll(productLists);
            mymovie.setItems(productObservableLists);  // Populate the table with the customer purchases
        }

        // Hide unnecessary UI elements when showing the purchase screen
        listofmoviesview.setVisible(false);
        mymovie.setVisible(true);
        superpointslable.setVisible(false);
        date1.setVisible(false);
        time1.setVisible(false);
        nam1.setVisible(false);
        price1.setVisible(false);
        quantity1.setVisible(false);
        moviedatelable.setVisible(false);
        movienamelable.setVisible(false);
        moviepricelable.setVisible(false);
        movietimelable.setVisible(false);
        moviequantitylable.setVisible(false);
        purchase.setVisible(false);
    }

    @FXML
    void returntohomescene(ActionEvent event) {
         HelloApplication.changescene("homescene");
    }
    @FXML
    void getiteams(MouseEvent event) {
        Movie selectedMovie = listofmoviesview.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            // Set the labels with the selected movie's details
            nam1.setText(selectedMovie.getMoviename());
            date1.setText(selectedMovie.getDate());
            time1.setText(selectedMovie.getTime());
            price1.setText(selectedMovie.getPrice() + " BDT");
            quantity1.setText(String.valueOf(selectedMovie.getQuantity()));
            moviegenres =selectedMovie.getGenre();

        } else {
            // Optionally reset labels or set default text if no movie is selected
            nam1.setText("No movie selected");
            date1.setText("");
            time1.setText("");
            price1.setText("");
            quantity1.setText("");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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




        purchase.setVisible(true);
        namelable.setText(LoginController.username);
        listofmoviesview.setVisible(true);
        mymovie.setVisible(false);
        superpointslable.setVisible(false);
        date1.setVisible(true);
        time1.setVisible(true);
        nam1.setVisible(true);
        price1.setVisible(true);
        quantity1.setVisible(true);
        moviedatelable.setVisible(true);
        movienamelable.setVisible(true);
        moviepricelable.setVisible(true);
        movietimelable.setVisible(true);
        moviequantitylable.setVisible(true);
        purchase.setVisible(true);
    }
}
