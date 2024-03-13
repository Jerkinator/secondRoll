package SecondRoll.demo.payload.response;

import SecondRoll.demo.models.GameAds;

import java.util.ArrayList;
import java.util.List;

public class UserProfileResponse {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String adress_street;
    private String adress_zip;
    private String adress_city;
    private ArrayList<Integer> ratings;
    private int averageRating;

    public UserProfileResponse(String id, String username, String email, String firstName, String lastName,
                               String phoneNumber, String adress_street, String adress_zip, String adress_city,
                               ArrayList<Integer> ratings, int averageRating) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.adress_street = adress_street;
        this.adress_zip = adress_zip;
        this.adress_city = adress_city;
        this.ratings = ratings;
        this.averageRating = averageRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public List<GameAds> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<GameAds> wishlist) {
        this.wishlist = wishlist;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
}
