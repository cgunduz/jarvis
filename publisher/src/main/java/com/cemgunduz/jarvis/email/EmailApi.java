package com.cemgunduz.jarvis.email;

import com.cemgunduz.jarvis.email.gmail.GmailConfiguration;
import com.cemgunduz.jarvis.email.gmail.GmailCompatibleEmail;
import com.cemgunduz.jarvis.email.postfix.PostfixCompatibleEmail;
import com.cemgunduz.jarvis.email.postfix.PostfixConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

/**
 * Created by cem on 04/07/16.
 */
@Controller
@RequestMapping("/email")
public class EmailApi {

    @Autowired
    GmailConfiguration gmailConfiguration;

    @Autowired
    PostfixConfiguration postfixConfiguration;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    @HystrixCommand(fallbackMethod = "sendEmailFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    public @ResponseBody EmailSendingResponse sendEmail(@RequestBody EmailInput input) throws MessagingException {
        Email email = new PostfixCompatibleEmail(input, postfixConfiguration.getHost(),
                postfixConfiguration.getPort());

        email.send();
        return EmailSendingResponse.createSucessResponse();
    }

    public @ResponseBody EmailSendingResponse sendEmailFallback(@RequestBody EmailInput input)
    {
        Email email = new GmailCompatibleEmail(input, gmailConfiguration.getUsername(),
                gmailConfiguration.getPassword());

        try {
            email.send();
        } catch (MessagingException e) {
            return EmailSendingResponse.createErrorResponse(e.getMessage());
        }
        return EmailSendingResponse.createSucessResponse();
    }
}
