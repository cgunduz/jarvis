package com.cemgunduz.jarvis.email;

/**
 * Created by cem on 05/07/16.
 */
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
}
