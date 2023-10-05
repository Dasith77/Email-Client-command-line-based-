import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Email implements Serializable{
    //this class is use to create Email objects


    private String emailAddress;
    private String EmailSubject;
    private String EmailContent;


    private String day;


    public Email(String emailAddress, String EmailSubject, String EmailContent,String day){
        this.emailAddress=emailAddress;
        this.EmailContent=EmailContent;
        this.EmailSubject=EmailSubject;
        this.day=day;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getEmailSubject() {
        return EmailSubject;
    }
    public String getEmailContent() {
        return EmailContent;
    }
    public String getDay() {
        return day;
    }





}
