package SecondRoll.demo.payload.response;

import java.util.Date;

//This class is called to display a custom error message for exceptions.
public class ErrorMessage {
    private Date timestamp;
    private String messsage;

    //Empty constructor.
    public ErrorMessage() {
    }

    //Constructor with timestamp and error message.
    public ErrorMessage(Date timestamp, String messsage) {
        this.timestamp = timestamp;
        this.messsage = messsage;
    }

    //Getters and setters.
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}