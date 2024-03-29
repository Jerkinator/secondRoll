package SecondRoll.demo.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Indexed(unique = true)
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Indexed(unique = true)
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;
    @NotBlank
    @Size(min = 6, max = 12)
    private String phoneNumber;
    @NotBlank
    private String adress_street;
    @NotBlank
    @Size(min = 5, max = 6)
    private String adress_zip;
    @NotBlank
    private String adress_city;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setLastname(String lastName) {
        this.lastName = lastName;
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
}