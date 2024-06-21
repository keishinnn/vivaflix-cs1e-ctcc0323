import javax.swing.JButton;

public class RentalItem {
    private String title;
    private double pricePerDay;
    private int rentalDays;
    private JButton movieButton; // Add a JButton to RentalItem

    public RentalItem(String title, double pricePerDay, int rentalDays, JButton movieButton) {
        this.title = title;
        this.pricePerDay = pricePerDay;
        this.rentalDays = rentalDays;
        this.movieButton = movieButton; // Initialize the JButton
    }

    // Getters and setters for the new field
    public JButton getMovieButton() {
        return movieButton;
    }

    public void setMovieButton(JButton movieButton) {
        this.movieButton = movieButton;
    }

    // Existing getters and setters
    public String getTitle() {
        return title;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
