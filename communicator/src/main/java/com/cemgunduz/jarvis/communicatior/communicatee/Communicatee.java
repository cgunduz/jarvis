package com.cemgunduz.jarvis.communicatior.communicatee;

import com.cemgunduz.jarvis.communicatior.Communication;
import com.cemgunduz.jarvis.communicatior.CommunicationType;
import com.cemgunduz.jarvis.communicatior.Schedule;
import com.cemgunduz.jarvis.communicatior.people.People;
import com.cemgunduz.jarvis.communicatior.people.PeopleSatisfactionEntry;
import com.cemgunduz.jarvis.communicatior.people.Priority;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by cem on 28/09/16.
 */

@Document(collection = "Communicatee")
public class Communicatee {

    // a week
    private static final int UPDATE_INTERVALS_IN_DAYS = 7;

    @Id
    private String id;

    private List<People> peoples = new ArrayList<>();
    private String userName;

    private Schedule schedule;

    private Preferences preferences;

    private String email;

    @Transient
    private Random random = new Random();

    public Communicatee() {
    }

    public Communicatee(CommunicateeIO communicateeIO) {

        id = communicateeIO.getId();
        peoples = communicateeIO.getPeoples();
        preferences = communicateeIO.getPreferences();
        schedule = communicateeIO.getSchedule();
        userName = communicateeIO.getUserName();
        email = communicateeIO.getEmail();
    }

    public void addPeople(People p)
    {
        peoples.add(p);
        alterScheduleDueToNewPeople(p);
    }

    /**
     * Latest version is
     *
     */
    public List<Communication> updateSchedule()
    {
        schedule.recordPreviousCommunications();
        schedule.clear();

        Queue<PeopleSatisfactionEntry> rateList = schedule.getSortedSatisfactionRate();

        List<Communication> communications = new ArrayList<>();
        List<Integer> availableDays = preferences.getRemainingDaysOfTheWeek();
        int totalDays = preferences.getAvailableDayOfWeek().size();

        for(Integer dayNumber : availableDays)
        {
            double remainingTimePerDay = preferences.getAmountOfTimePerWeek() / totalDays;
            while(remainingTimePerDay >= 0 && !rateList.isEmpty())
            {
                People people = rateList.remove().getPeople();
                Communication communication = new Communication(
                        people, decideOnCommunicationType(people.getPriority(), people.getRateOfCommunication()),
                        new Date(), dayNumber
                );

                communications.add(communication);
                remainingTimePerDay -= communication.getEstimatedTimeLength();
            }
        }

        schedule.setSchedule(communications);
        return schedule.getUninitiatedCommunications();
    }

    public void createTestCommunicatee()
    {
        userName = "TestUser";
        schedule = new Schedule();
        preferences = new Preferences();
        preferences.createTestPreferences();
    }

    public Schedule getSchedule()
    {
        return schedule;
    }

    public CommunicateeIO toIOModel()
    {
        CommunicateeIO communicateeIO = new CommunicateeIO();
        communicateeIO.setId(id);
        communicateeIO.setPeoples(peoples);
        communicateeIO.setPreferences(preferences);
        communicateeIO.setSchedule(schedule);
        communicateeIO.setUserName(userName);
        communicateeIO.setEmail(email);

        return communicateeIO;
    }

    public String getEmail() {
        return email;
    }

    private CommunicationType decideOnCommunicationType(Priority priority, Integer rateOfCommunication)
    {
        double rateIncrement = Double.valueOf(rateOfCommunication);
        rateIncrement *= (1 + priority.ordinal());

        double outOf = 0;
        for(CommunicationType communicationType : CommunicationType.values())
        {
            outOf += communicationType.getTypeEffortCost();
        }

        Integer randomCap = (int) outOf;
        Integer chance = random.nextInt(randomCap);

        // TODO : Relies on order, sort first ?
        for(CommunicationType communicationType : CommunicationType.values())
        {
            if(chance > communicationType.getTypeEffortCost())
                return communicationType;
        }

        return CommunicationType.SHORT_MESSAGE;
    }

    /**
     * Recalculate schedule due to new arrivals, atm starts from scratch
     * TODO : Can be improved by partial updates but this is not needed
     *
     */
    private void alterScheduleDueToNewPeople(People people)
    {
        schedule.setInitialSatisfactionOfPeople(people);

        // TODO : Don t update
        //updateSchedule();
    }
}
