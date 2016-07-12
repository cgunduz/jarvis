package com.cemgunduz.jarvis.email;

import com.cemgunduz.jarvis.publish.Publishable;

import java.util.List;

/**
 * Created by cem on 04/07/16.
 */
public class EmailInput {

    public EmailInput(Publishable publishable,
        String from, String toList) {

        this.from = from;
        this.toList = toList;
        subject = publishable.getType().name()
                    .concat(" - ")
                    .concat(publishable.getTitle());
        content = publishable.getDescription()
                    .concat(" - ")
                    .concat(publishable.getLink());
    }

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
}
