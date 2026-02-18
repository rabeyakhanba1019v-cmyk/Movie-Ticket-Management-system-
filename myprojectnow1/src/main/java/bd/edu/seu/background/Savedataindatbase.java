package bd.edu.seu.background;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Savedataindatbase {
    public static int getAvailableStock(String movieName) {
        Connection connection = Singletone.getConnection();
        int availableStock = 0;

        try {
            // Fetch the available stock for the selected movie
            String query = "SELECT quantity FROM movieinfo WHERE movieName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, movieName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                availableStock = resultSet.getInt("quantity");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableStock;
    }


    // Method to save a movie in the database
    public static void save(Movie movie) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO MOVIEINFO (movieName, date, time, quantity, price, genre) " +
                    "VALUES ('" + movie.getMoviename() + "', '" + movie.getDate() + "', '" +
                    movie.getTime() + "', " + movie.getQuantity() + ", " + movie.getPrice() + ",' " + movie.getGenre() + "')";
            statement.execute(query);
            System.out.println("Movie saved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a list of movies from the database
    public List<Movie> getProductList() {
        List<Movie> movieList = new ArrayList<>();
        Connection connection = Singletone.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM movieinfo"; // Ensure table and column names are correct
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String movieName = resultSet.getString("movieName");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String genre = resultSet.getString("genre");
                Movie movie = new Movie(movieName, date, time, quantity, price, genre);
                movieList.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieList;
    }

    // Method to update a movie in the database
    public static void update(Movie movie) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE movieinfo SET price = " + movie.getPrice() +
                    ", quantity = " + movie.getQuantity() +
                    ", genre = '" + movie.getGenre() +
                    "' WHERE movieName = '" + movie.getMoviename() + "'";
            statement.executeUpdate(query);
            System.out.println("Movie updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to delete a movie from the database
    public static void delete(String movieName) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM movieinfo WHERE movieName = '" + movieName + "'";
            statement.executeUpdate(query);
            System.out.println("Movie deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update movie quantity after a purchase
    public static void updateQuantity(Movie movie, int quantity) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE movieinfo SET quantity = quantity - " + quantity + " WHERE movieName = '" + movie.getMoviename() + "'";
            statement.executeUpdate(query);
            System.out.println("Movie quantity updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to save a customer's movie purchase
    public static void savePurchase(Customer movie) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO MOVIEIN (name, movieName, date, time, quantity, price, genre) " +
                    "VALUES ('" + movie.getName() + "','" + movie.getMoviename() + "', '" + movie.getDate() + "', '" +
                    movie.getTime() + "', " + movie.getQuantity() + ", " + movie.getPrice() + ", '" + movie.getGenre() + "')";
            statement.execute(query);
            System.out.println("Purchase saved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get the list of customer purchases
    public List<Customer> getCustomerPurchasesIndivisual() {
        List<Customer> purchaseList = new ArrayList<>();
        Connection connection = Singletone.getConnection();

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM MOVIEIN WHERE name = '" + LoginController.username + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String movieName = resultSet.getString("movieName");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String genre = resultSet.getString("genre");

                Customer customer = new Customer(name, movieName, date, time, quantity, price, genre);
                purchaseList.add(customer);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchaseList;
    }

    // Method to retrieve all customer purchases
    public List<Customer> getCustomerPurchases() {
        List<Customer> purchaseList = new ArrayList<>();
        Connection connection = Singletone.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MOVIEIN"; // Ensure this table exists
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String movieName = resultSet.getString("movieName");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String genre = resultSet.getString("genre");

                Customer customer = new Customer(name, movieName, date, time, quantity, price, genre);
                purchaseList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return purchaseList;
    }

    // Method to cancel a booking by movie name
    public static void cancelBooking(String movieName) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM moviein WHERE movieName = '" + movieName + "'";
            statement.executeUpdate(query);
            System.out.println("Booking for " + movieName + " canceled");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to save superpoints for a customer
    public static void savesuperpoints(Superpoints superpoints) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO superpoint (name, superpoint) " +
                    "VALUES ('" + superpoints.getName() + "', " + superpoints.getPoints() + ")";
            statement.execute(query);
            System.out.println("Points saved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update superpoints for a customer
    public static void updatesuperpoints(Superpoints superpoints) {
        Connection connection = Singletone.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE superpoint SET superpoint = " + superpoints.getPoints() + " WHERE name = '" + superpoints.getName() + "'";
            statement.execute(query);
            System.out.println("Points updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get the individual superpoints for a customer
    public List<Superpoints> getsuperpointIndivisual() {
        List<Superpoints> purchaseList = new ArrayList<>();
        Connection connection = Singletone.getConnection();

        try {
            String query = "SELECT * FROM superpoint WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, LoginController.username); // Use setString to safely pass the username

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("superpoint");

                Superpoints superpoints = new Superpoints(name, quantity);
                purchaseList.add(superpoints);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return purchaseList;
    }

    // Method to retrieve all customer superpoint purchases
    public List<Superpoints> getsuperpointPurchases() {
        List<Superpoints> purchaseList = new ArrayList<>();
        Connection connection = Singletone.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM superpoint";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("superpoint");

                Superpoints superpoints = new Superpoints(name, quantity);
                purchaseList.add(superpoints);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return purchaseList;
    }

    // Method to get the most frequent genre from customer purchases
    public String getMostFrequentGenre() {
        String mostFrequentGenre = null;
        Savedataindatbase savedataindatbase = new Savedataindatbase();
        List<Customer> customerPurchases = savedataindatbase.getCustomerPurchasesIndivisual();

        Map<String, Integer> genreCountMap = new HashMap<>();
        for (Customer customer : customerPurchases) {
            String genre = customer.getGenre();  // Assuming getGenre() returns the genre of the purchased movie
            genreCountMap.put(genre, genreCountMap.getOrDefault(genre, 0) + 1);
        }

        int maxCount = -1;
        for (Map.Entry<String, Integer> entry : genreCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentGenre = entry.getKey();
            }
        }

        return mostFrequentGenre;
    }

    // Method to update superpoints when a purchase is made
    public static void updateSuperpoints(String username, int quantity) {
        Connection connection = Singletone.getConnection();
        try {
            String selectQuery = "SELECT superpoint FROM superpoint WHERE name = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int currentPoints = resultSet.getInt("superpoint");
                int newPoints = currentPoints + quantity;

                String updateQuery = "UPDATE superpoint SET superpoint = ? WHERE name = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newPoints);
                updateStatement.setString(2, username);
                updateStatement.executeUpdate();

                System.out.println("Superpoints updated for user: " + username);
            } else {
                String insertQuery = "INSERT INTO superpoint (name, superpoint) VALUES (?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, username);
                insertStatement.setInt(2, quantity);
                insertStatement.executeUpdate();

                System.out.println("Superpoints inserted for new user: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
