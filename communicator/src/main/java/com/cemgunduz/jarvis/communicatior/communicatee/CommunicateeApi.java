package com.cemgunduz.jarvis.communicatior.communicatee;

import com.cemgunduz.jarvis.communicatior.Communication;
import com.cemgunduz.jarvis.communicatior.people.People;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 28/09/16.
 */

@RestController
@RequestMapping("/communicatee")
public class CommunicateeApi {

    @Autowired
    CommunicateeDao communicateeDao;

    @RequestMapping(method= RequestMethod.GET)
    public List<CommunicateeIO> getAll()
    {
        List<CommunicateeIO> response = new ArrayList<>();
        communicateeDao.findAll().stream().forEach(item -> response.add(item.toIOModel()));
        return response;
    }

    @RequestMapping(method= RequestMethod.POST)
    public void createNewCommunicatee(@RequestBody CommunicateeIO communicateeIO)
    {
        communicateeDao.save(new Communicatee(communicateeIO));
    }

    @RequestMapping(value = "/{id}/people",method= RequestMethod.POST)
    public void createNewPersonForCommunicatee(@PathParam("id") String id, @RequestBody People person)
    {
        Communicatee communicatee = communicateeDao.findOne(id);
        communicatee.addPeople(person);
        communicateeDao.save(communicatee);
    }

    @RequestMapping(value = "/{id}/schedule",method= RequestMethod.GET)
    public List<Communication> getSchedule(@PathParam("id") String id)
    {
        Communicatee communicatee = communicateeDao.findOne(id);
        List<Communication> updatedList = communicatee.updateSchedule();

        communicateeDao.save(communicatee);
        return updatedList;
    }

    @RequestMapping(value = "/updateAll",method= RequestMethod.GET)
    public String updateAll(@PathParam("id") String id)
    {
        List<Communicatee> communicatees = communicateeDao.findAll();

        for(Communicatee communicatee : communicatees)
        {
            communicatee.updateSchedule();
            communicateeDao.save(communicatee);
        }

        return "Done !";
    }

    @RequestMapping("/hystrixTest")
    @HystrixCommand(fallbackMethod = "hystricsEndpointFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public void hystricsEndpoint()
    {
        throw new RuntimeException("asd");
    }

    public void hystricsEndpointFallback()
    {
        System.out.println("Failed!");
    }
}
