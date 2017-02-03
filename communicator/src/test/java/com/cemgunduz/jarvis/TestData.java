package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.communicatior.communicatee.Communicatee;
import com.cemgunduz.jarvis.communicatior.communicatee.CommunicateeDao;
import com.cemgunduz.jarvis.communicatior.people.People;
import com.cemgunduz.jarvis.communicatior.people.Priority;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by cem on 03/10/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CommunicatorApplication.class)
@Ignore
public class TestData {

    @Autowired
    CommunicateeDao communicateeDao;

    @Test
    public void addOne()
    {
        Communicatee communicatee = new Communicatee();
        communicatee.createTestCommunicatee();

        communicatee.addPeople(createLowProNormalPerson());
        communicatee.addPeople(createMediumProEasyPerson());
        communicatee.addPeople(createHighProHardPerson());

        communicateeDao.save(communicatee);
    }

    private People createMediumProEasyPerson()
    {
        People people = new People();
        people.setName(UUID.randomUUID().toString());
        people.setPriority(Priority.MEDIUM);
        people.setRateOfCommunication(4);

        return people;
    }

    private People createHighProHardPerson()
    {
        People people = new People();
        people.setName(UUID.randomUUID().toString());
        people.setPriority(Priority.HIGH);
        people.setRateOfCommunication(2);

        return people;
    }

    private People createLowProNormalPerson()
    {
        People people = new People();
        people.setName(UUID.randomUUID().toString());
        people.setPriority(Priority.LOW);
        people.setRateOfCommunication(3);

        return people;
    }
}
