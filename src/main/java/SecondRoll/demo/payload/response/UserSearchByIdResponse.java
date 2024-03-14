package SecondRoll.demo.payload.response;

public class UserSearchByIdResponse {
    private String id;
    private String username;
    private String adress_city;


    public UserSearchByIdResponse(String id, String username, String adress_city) {
        this.id = id;
        this.username = username;
        this.adress_city = adress_city;
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

    public String getAdress_city() {
        return adress_city;
    }

    public void setAdress_city(String adress_city) {
        this.adress_city = adress_city;
    }
}
