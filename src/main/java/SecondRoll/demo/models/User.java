package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    //Should all adress-related information be inside an array?
    private String adress_street;

    private String adress_zip;

    private String adress_city;

    //Wishlist
    @DBRef
    private List<GameAds> wishlist;

    //User rating here?

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

    public String getAdress_street() {
        return adress_street;
    }

    public String getAdress_zip() {
        return adress_zip;
    }

    public String getAdress_city() {
        return adress_city;
    }

    public String getUsername() {
        return username;
    }

    public List<GameAds> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<GameAds> wishlist) {
        this.wishlist = wishlist;
    }
}