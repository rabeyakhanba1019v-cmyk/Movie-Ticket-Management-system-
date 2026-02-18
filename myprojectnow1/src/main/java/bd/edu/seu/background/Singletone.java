package bd.edu.seu.background;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singletone {
    private static  final String DB_USERNAME="root";
    private static  final String DB_NAME="Moviemain";
    private static  final String DB_HOST="localhost";
    private static  final String DB_PASSWORD="1234567@";
    private static  final String DB_URL="jdbc:mysql://"+DB_HOST+"/"+DB_NAME;
    private static Connection connection;

    public static Singletone singleton =new Singletone();

    private Singletone() {
        try {
            connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
