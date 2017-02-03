package com.cemgunduz.jarvis.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by cem on 05/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmailSendingResponse {

    private boolean isSent;
    private String reason;

    private EmailSendingResponse(boolean isSent, String reason) {
        this.isSent = isSent;
        this.reason = reason;
    }

    public static EmailSendingResponse createSucessResponse()
    {
        return new EmailSendingResponse(true, null);
    }

    public static EmailSendingResponse createErrorResponse(String message)
    {
        return new EmailSendingResponse(true, message);
    }

    public boolean isSent() {
        return isSent;
    }

    public String getReason() {
        return reason;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
