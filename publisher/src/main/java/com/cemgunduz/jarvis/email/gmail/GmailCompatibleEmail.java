package com.cemgunduz.jarvis.email.gmail;

import com.cemgunduz.jarvis.email.Email;
import com.cemgunduz.jarvis.email.EmailInput;
import com.cemgunduz.jarvis.utils.GeneralUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cem on 05/07/16.
 */
public class GmailCompatibleEmail implements Email {

    final EmailInput emailInput;
    final String username;
    final String password;

    public GmailCompatibleEmail(EmailInput emailInput, String username, String password) {
        this.emailInput = emailInput;
        this.username = username;
        this.password = password;
    }

    @Override
    public Properties getProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }

    @Override
    public void send() throws MessagingException {

        Properties props = getProperties();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = emailInput.toMessage(session);

        Transport.send(message);
    }

    @Override
    public boolean validate() {

        return GeneralUtils.notNull(username, password, emailInput, emailInput.getFrom(), emailInput.getToList());
    }
}
