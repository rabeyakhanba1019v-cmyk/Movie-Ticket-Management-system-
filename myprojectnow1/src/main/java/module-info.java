module bd.edu.seu.background {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens bd.edu.seu.background to javafx.fxml;
    exports bd.edu.seu.background;
}