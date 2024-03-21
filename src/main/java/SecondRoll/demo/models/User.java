package SecondRoll.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Document(collection = "users")
public class User {

    @Id
    private String id;

    @CreatedDate
    private Date createdAt;

    @NotBlank
    @Indexed(unique = true)
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;
    @NotBlank
    @Size(min = 6, max = 12)
    private String phoneNumber;

    // Separate strings with the adress details to make it easier and more readable in code and mongodb
    @NotBlank
    private String adress_street;
    @NotBlank
    @Size(min = 5, max = 6)
    private String adress_zip;
    @NotBlank
    private String adress_city;

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

    // Constructor w username, email and password
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Getters.

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public String getUsername() {
        return username; }

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

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress_street() {
        return adress_street;
    }

    public void setAdress_street(String adress_street) {
        this.adress_street = adress_street;
    }

    public String getAdress_zip() {
        return adress_zip;
    }

    public void setAdress_zip(String adress_zip) {
        this.adress_zip = adress_zip;
    }

    public String getAdress_city() {
        return adress_city;
    }

    public void setAdress_city(String adress_city) {
        this.adress_city = adress_city;
    }
}