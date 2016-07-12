package com.cemgunduz.jarvis.email.postfix;

import com.cemgunduz.jarvis.email.Email;
import com.cemgunduz.jarvis.email.EmailInput;
import com.cemgunduz.jarvis.utils.GeneralUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cem on 12/07/16.
 */
public class PostfixCompatibleEmail implements Email {

    private final EmailInput emailInput;
    private final String host;
    private final String port;

    public PostfixCompatibleEmail(EmailInput emailInput, String host, String port) {
        this.emailInput = emailInput;
        this.host = host;
        this.port = port;
    }

    @Override
    public Properties getProperties() {

        return System.getProperties();
    }

    @Override
    public void send() throws MessagingException {

        // Get system properties
        Properties properties = getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        Message message = emailInput.toMessage(session);

        Transport.send(message);
    }

    @Override
    public boolean validate() {

        return GeneralUtils.notNull(host, port, emailInput, emailInput.getFrom(), emailInput.getToList());
    }
}
