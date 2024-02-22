package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @CreatedDate
    private Date createdAt;

    //Updated at?

    private String email;

    private String username;

    private String password; //This needs to be hashed and salted.

    private String firstName;

    private String lastName;

    private String phoneNumber;


    //An array containing adress details. Add street, zip and city to this in postman.
    private List<String> adress_details = new ArrayList<>();

    //Wishlist
    @DBRef
    private List<GameAds> wishlist = new ArrayList<>();

    //List of all user ratings.
    private ArrayList<Integer> ratings = new ArrayList<>();

    //Holds the average rating of all user ratings.
    private int averageRating;

    //Empty constructor.
    public User() {
    }

    //Getters.

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getAdress_details() {
        return adress_details;
    }

    public String getUsername() {
        return username;
    }

    public List<GameAds> getWishlist() {
        return wishlist;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public int getAverageRating() {
        return averageRating;
    }

    // Setters.

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public void setWishlist(List<GameAds> wishlist) {
        this.wishlist = wishlist;
    }
}