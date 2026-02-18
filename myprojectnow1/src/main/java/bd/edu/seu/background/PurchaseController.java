package bd.edu.seu.background;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

import static bd.edu.seu.background.DashboardController.movieprices;

public class PurchaseController implements Initializable {
    static int p;

    @FXML
    private TextField moviename;

    @FXML
    private TextField price;

    @FXML
    private Button purrc;

    @FXML
    private TextField quantity;

    // Method to handle the purchase event
    @FXML
    void purchaseit(ActionEvent event) {
        try {
            // Parsing quantity input
            int quanti = Integer.parseInt(quantity.getText());

            // Check if the entered quantity is less than or equal to zero
            if (quanti <= 0) {
                showAlert("Invalid Quantity", "Quantity must be greater than zero.");
                return; // Exit the method if the quantity is invalid
            }

            // Get the available stock for the movie from the database
            int availableStock = Savedataindatbase.getAvailableStock(DashboardController.movienames); // Fetch stock from the database

            // Check if the entered quantity exceeds the available stock
            if (quanti > availableStock) {
                showAlert("Insufficient Stock", "The requested quantity exceeds the available stock.");
                return; // Exit the method if there's insufficient stock
            }

            // Remove non-numeric characters from movieprices (like "BDT")
            String numericPrice = movieprices.replaceAll("[^0-9]", ""); // Remove anything that's not a digit

            // Parse the cleaned numeric string to an integer
            int pricePerUnit = Integer.parseInt(numericPrice);

            // Calculate the total price
            p = pricePerUnit * quanti;
            price.setText(String.valueOf(p));

            // Create the Customer object and save it
            Customer customer = new Customer(
                    LoginController.username,
                    DashboardController.movienames,
                    DashboardController.moviedates,
                    DashboardController.movietimes,
                    quanti,
                    p,
                    DashboardController.moviegenres
            );

            // Save data to the database
            Savedataindatbase.savePurchase(customer);

            // Update movie quantity in the movieinfo table
            Movie movie = new Movie(
                    DashboardController.movienames,
                    DashboardController.moviedates,
                    DashboardController.movietimes,
                    quanti,
                    pricePerUnit,
                    DashboardController.moviegenres
            );
            Savedataindatbase.updateQuantity(movie, quanti);  // Call the updateQuantity method to reduce the quantity

            // Update the superpoints based on the purchased quantity
            Savedataindatbase.updateSuperpoints(LoginController.username, quanti);

            // Show success alert
            showAlert("Purchase Successful", "Your purchase was completed successfully!");

            // Navigate back to the Dashboard
            HelloApplication.changescene("dashboard");

        } catch (NumberFormatException e) {
            // Handle invalid input
            showAlert("Invalid Input", "Please enter a valid quantity and price.");
        }
    }

    // Method to return to the Dashboard
    @FXML
    void retueeee(ActionEvent event) {
        HelloApplication.changescene("dashboard");
    }

    // Method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to calculate and display the total balance based on quantity
    @FXML
    void showbalance(ActionEvent event) {
        int quanti = Integer.parseInt(quantity.getText());

        // Remove non-numeric characters from movieprices (like "BDT")
        String numericPrice = movieprices.replaceAll("[^0-9]", ""); // Remove anything that's not a digit

        // Parse the cleaned numeric string to an integer
        int pricePerUnit = Integer.parseInt(numericPrice);

        // Calculate the total price
        p = pricePerUnit * quanti;
        price.setText(String.valueOf(p));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moviename.setText(DashboardController.movienames);
    }
}
