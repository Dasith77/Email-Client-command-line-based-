import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class SendEmailTLS {
       //this class is used to send emails via dasith2022@gmail.com gmail account
        public  static void sendEmail(String recipients,String subject,String body ) {

            final String username = "dasith2022@gmail.com";
            final String password = "qeesdcnfmueoowbt";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("dasith2022@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(recipients)
                );
                message.setSubject(subject);
                message.setText(body);
                System.out.println(message);
                Transport.send(message);

                System.out.println("Email sent.");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

}



