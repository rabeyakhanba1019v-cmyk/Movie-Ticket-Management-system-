package bd.edu.seu.background;

public class Officer {
    private String username; // Officer's username
    private String password;// Officer's password
    private static String adminpassword="123456";
    private static String admincode="Admin12345";


    public Officer() {
        // Default constructor for admin usage
    }

    public Officer(String username, String password) {
        this.username = username; // Set username
        this.password = password; // Set password
    }

    public boolean compareUsername(String username) {
        return this.username.equals(username); // Check username match
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password); // Check password match
    }

    public static boolean compare(String pas,String co){
        return (adminpassword.equals(pas)&&admincode.equals(co));
    }
}
