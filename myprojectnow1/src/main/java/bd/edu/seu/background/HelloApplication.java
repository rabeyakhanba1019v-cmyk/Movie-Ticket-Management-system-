package bd.edu.seu.background;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homescene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.setTitle("Cineplex Movie");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void  changescene(String filename)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(filename+".fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}