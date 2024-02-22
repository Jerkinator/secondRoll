package SecondRoll.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


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

    //Updated at?

    @NotBlank
    @Email
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Size(min = 7, max = 120)


    private String password; //This needs to be hashed and salted.

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private String firstName;

    private String lastName;

    private String phoneNumber;

    //Should all address-related information be inside an array?
    private String address_street;

    private String address_zip;

    private String address_city;

    //Wishlist
    @DBRef
    private List<GameAds> wishlist;

    //User rating here?

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

    public String getAddress_street() {
        return address_street;
    }

    public String getAddress_zip() {
        return address_zip;
    }

    public String getAddress_city() {
        return address_city;
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

    public void setWishlist(List<GameAds> wishlist) {
        this.wishlist = wishlist;
    }
}