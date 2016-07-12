package com.cemgunduz.jarvis.reader.eksisozluk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 12/07/16.
 */

@RestController
@RequestMapping(path = "/eksi")
public class EksiApi {

    @Autowired
    EksisozlukTopicReader eksisozlukTopicReader;

    @RequestMapping(method = RequestMethod.GET)
    public String initiate()
    {
        eksisozlukTopicReader.read();
        return "Done ! ";
    }

}
