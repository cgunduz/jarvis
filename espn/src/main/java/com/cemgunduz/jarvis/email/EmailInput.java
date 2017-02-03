package com.cemgunduz.jarvis.email;

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

    public void setFrom(String from) {
        this.from = from;
    }

    public void setToList(String toList) {
        this.toList = toList;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
