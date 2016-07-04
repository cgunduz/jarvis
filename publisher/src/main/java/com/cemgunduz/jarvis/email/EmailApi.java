package com.cemgunduz.jarvis.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.POST;

/**
 * Created by cem on 04/07/16.
 */
@Controller
@RequestMapping("/email")
public class EmailApi {

    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String sendEmail(@RequestBody String input)
    {
        System.out.println(input);
        return "Cool";
    }
}
