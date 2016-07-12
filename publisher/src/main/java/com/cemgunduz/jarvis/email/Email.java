package com.cemgunduz.jarvis.email;

import javax.mail.MessagingException;
import java.util.Properties;

/**
 * Created by cem on 04/07/16.
 */
public interface Email {

    Properties getProperties();
    void send() throws MessagingException;
    boolean validate();
}
