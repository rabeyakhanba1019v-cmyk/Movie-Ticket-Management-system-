package bd.edu.seu.background;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private Label lable1;

    @FXML
    private Label lable2;

    @FXML
    private Label lable3;

    @FXML
    private Label lable4;

    @FXML
    private Label lable5;
    @FXML
    private Label lable6;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField nametextfield;

    @FXML
    private Spinner<Integer> pricespinner;

    @FXML
    private Spinner<Integer> quantityspinner;

    @FXML
    private TextField timetextfield;
    @FXML
    private ChoiceBox<String> genrechoicebox;


    @FXML
    void add(ActionEvent event) {
        boolean switchDecider=true;
        String name=  nametextfield.getText();
        if(name.trim().isEmpty())
        {
            lable1.setVisible(true);
            switchDecider = false;
        }
        else{
            lable1.setVisible(false);
            switchDecider=true && switchDecider;

        }
        String time=timetextfield.getText();
        if(time.trim().isEmpty())
        {
            lable2.setVisible(true);
            switchDecider = false;
        }
        else{
            lable2.setVisible(false);
            switchDecider=true && switchDecider;

        }
        int  quantity= quantityspinner.getValue();
        if(quantity<10)
        {
            lable4.setVisible(true);
            switchDecider = false;
        }
        else{
            lable4.setVisible(false);
            switchDecider=true && switchDecider;
        }
        int price =pricespinner.getValue();
        if(price<200)
        {
            lable5.setVisible(true);
            switchDecider = false;
        }
        else{
            lable5.setVisible(false);
            switchDecider=true && switchDecider;
        }


        LocalDate todaydate=LocalDate.of(2025,1,19);

        if(datepicker.getValue().isAfter(todaydate))
        {
            lable3.setVisible(false);
            switchDecider = true;
        }
        else {
            lable3.setVisible(true);
            switchDecider=false;
        }

        LocalDate datesel=datepicker.getValue();
        int  month=datesel.getMonthValue();
        int year=datesel.getYear();
        int day= datesel.getDayOfMonth();
        String l=day+"-"+month+"-"+year;
        System.out.println(datesel);
        double rating=0;
        String genre =genrechoicebox.getValue();
        if(genre==null||genre.isEmpty())
        {
            lable6.setVisible(true);
            switchDecider = false;
        }
        else {
            lable6.setVisible(false);
            switchDecider=true && switchDecider;
        }


        if(switchDecider){
            Movie movie = new Movie(name,l,time,quantity,price,genre);
            Savedataindatbase.save(movie);

        }
    }




    @FXML
    void delete(ActionEvent event) {
        String movieNameToDelete = nametextfield.getText().trim(); // You can adjust this based on how the user selects the movie to delete

        // Ensure a movie name is provided
        if (movieNameToDelete.isEmpty()) {
            // Show an error message or label indicating that the name cannot be empty
            lable1.setText("Please enter the movie name to delete.");
            lable1.setVisible(true);
            return;
        }

        // Delete the movie from the database
        Savedataindatbase.delete(movieNameToDelete);

        // Optional: After deleting, clear the fields or refresh the UI
        nametextfield.clear();
        timetextfield.clear();
        quantityspinner.getValueFactory().setValue(0);
        pricespinner.getValueFactory().setValue(0);
        genrechoicebox.setValue(null);
        datepicker.setValue(null);

        // Optionally, update any displayed movie list or UI
        // For example, refresh a table view that lists all movies

    }

    @FXML
    void update(ActionEvent event) {
        boolean switchDecider=true;
        String name=  nametextfield.getText();
        if(name.trim().isEmpty())
        {
            lable1.setVisible(true);
            switchDecider = false;
        }
        else{
            lable1.setVisible(false);
            switchDecider=true && switchDecider;

        }
        String time=timetextfield.getText();
        if(time.trim().isEmpty())
        {
            lable2.setVisible(true);
            switchDecider = false;
        }
        else{
            lable2.setVisible(false);
            switchDecider=true && switchDecider;

        }
        int  quantity= quantityspinner.getValue();
        if(quantity<10)
        {
            lable4.setVisible(true);
            switchDecider = false;
        }
        else{
            lable4.setVisible(false);
            switchDecider=true && switchDecider;
        }
        int price =pricespinner.getValue();
        if(price<200)
        {
            lable5.setVisible(true);
            switchDecider = false;
        }
        else{
            lable5.setVisible(false);
            switchDecider=true && switchDecider;
        }


        LocalDate todaydate=LocalDate.of(2025,1,19);

        if(datepicker.getValue().isAfter(todaydate))
        {
            lable3.setVisible(false);
            switchDecider = true;
        }
        else {
            lable3.setVisible(true);
            switchDecider=false;
        }

        LocalDate datesel=datepicker.getValue();
        int  month=datesel.getMonthValue();
        int year=datesel.getYear();
        int day= datesel.getDayOfMonth();
        String l=day+"-"+month+"-"+year;
        System.out.println(datesel);
        double rating=0;
        String genre =genrechoicebox.getValue();
        if(genre==null||genre.isEmpty())
        {
            lable6.setVisible(true);
            switchDecider = false;
        }
        else {
            lable6.setVisible(false);
            switchDecider=true && switchDecider;
        }


        if(switchDecider){
            Movie movie = new Movie(name,l,time,quantity,price,genre);
            Savedataindatbase.update(movie);

        }

    }



    @FXML
    void returnto(ActionEvent event) {
        HelloApplication.changescene("homescene");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 200);
        svf.setValue(10);
        quantityspinner.setValueFactory(svf);
        SpinnerValueFactory<Integer> svt = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 500);
        svf.setValue(200);
        pricespinner.setValueFactory(svt);
        LocalDate todaydate=LocalDate.of(2025,01,19);
        datepicker.setValue(todaydate);

        genrechoicebox.setItems(FXCollections.observableArrayList("Fantasy", "Romance","Comedy","Action","Drama"));
        lable1.setVisible(false);
        lable2.setVisible(false);
        lable3.setVisible(false);
        lable4.setVisible(false);
        lable5.setVisible(false);
        lable6.setVisible(false);



    }
}