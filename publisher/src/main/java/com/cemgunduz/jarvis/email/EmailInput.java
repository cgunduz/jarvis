package com.cemgunduz.jarvis.email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by cem on 04/07/16.
 */
public class EmailInput {

    private String from;

    // comma seperated
    private String toList;
    private String ccList;
    private String bccList;
    // comma seperated !

    private String subject;
    private String content;
    private List<Object> attachmentList;

    public String getFrom() {
        return from;
    }

    public String getToList() {
        return toList;
    }

    public String getCcList() {
        return ccList;
    }

    public String getBccList() {
        return bccList;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public List<Object> getAttachmentList() {
        return attachmentList;
    }

    public Message toMessage(Session session) throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(getFrom()));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(getToList()));

        if (getCcList() != null)
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(getCcList()));

        if (getBccList() != null)
            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(getBccList()));

        message.setSubject(getSubject());
        message.setText(getContent());

        return message;
    }
}
