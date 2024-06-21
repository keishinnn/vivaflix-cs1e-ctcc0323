import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private List<RentalItem> rentedItems;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.rentedItems = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RentalItem> getRentedItems() {
        return rentedItems;
    }

    public void addRentedItem(RentalItem item) {
        this.rentedItems.add(item);
    }

    public void removeRentedItem(RentalItem item) {
        this.rentedItems.remove(item);
    }
}
