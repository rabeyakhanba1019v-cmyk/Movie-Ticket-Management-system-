package bd.edu.seu.background;

public class Movie {
    private String moviename;
    private String date;
    private String time;
    private int quantity;
    private int price;
    private String genre;

    public Movie(String moviename, String date, String time, int quantity, int price, String genre) {
        this.moviename = moviename;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.price = price;
        this.genre = genre;
    }

    public String getMoviename() {
        return moviename;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}
