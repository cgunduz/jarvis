package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.email.Email;
import com.cemgunduz.jarvis.email.EmailInput;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cem on 05/07/16.
 */
public class GmailCompatibleEmailTest  {

    public Properties getProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }

    public void send() throws MessagingException {

        Properties props = getProperties();

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jarvismailacc@gmail.com", "Jarvis1234");
                    }
                });


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jarvismailacc@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("jarvismailacc@gmail.com"));

        message.setSubject("Subject");
        message.setText("Content");

        Transport.send(message);
    }
}
