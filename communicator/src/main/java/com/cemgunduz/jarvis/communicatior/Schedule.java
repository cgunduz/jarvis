package com.cemgunduz.jarvis.communicatior;

import com.cemgunduz.jarvis.communicatior.people.People;
import com.cemgunduz.jarvis.communicatior.people.PeopleSatisfactionEntry;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.*;

/**
 * Created by cem on 28/09/16.
 */
public class Schedule {

    // 6 months(ish)
    private static final Integer COMMUNICATION_CALCULATION_CAP_IN_DAYS = 6 * 30;

    private List<PeopleSatisfactionEntry> initialSatisfactionList = new ArrayList<>();

    private List<Communication> communicationHistory = new ArrayList<>();
    private List<Communication> uninitiatedCommunications = new ArrayList<>();

    public void clear()
    {
        uninitiatedCommunications.clear();
    }

    public List<Communication> getHistory()
    {
        return communicationHistory;
    }

    public void addToHistory(Communication c)
    {
        communicationHistory.add(c);
    }

    public void setInitialSatisfactionOfPeople(People people)
    {
        Double mean = 0.0;
        int counter = 1;

        DateTime today = new DateTime();
        for(Communication communication : communicationHistory)
        {
            // TODO : Erase stuff that are older than COMMUNICATION_CALCULATION_CAP_IN_DAYS from persistent layer
            DateTime commAssignTime = new DateTime(communication.getDateAssigned());
            if(Days.daysBetween(today, commAssignTime).getDays() > COMMUNICATION_CALCULATION_CAP_IN_DAYS)
            {
                continue;
            }

            mean += communication.getCommunicationRating();
            counter++;
        }

        PeopleSatisfactionEntry peopleSatisfactionEntry = new PeopleSatisfactionEntry(
                people, mean / (double)counter
        ) ;

        initialSatisfactionList.add(peopleSatisfactionEntry);
    }

    public void recordPreviousCommunications()
    {
        DateTime today = new DateTime();

        // TODO : Faulty same day logic but acceptable, if you make a call before making the calls it won t work,
        // might change the strategy
        for(Communication initiatedCommunication : getUninitiatedCommunications())
        {
            if(Days.daysBetween(today, new DateTime(initiatedCommunication.getDateAssigned())).getDays() > 0)
            {
                addToHistory(initiatedCommunication);
            }
        }
    }

    public void recordPreviousCommunicationsAndFlush()
    {
        getUninitiatedCommunications().stream().forEach(item -> addToHistory(item));
        clear();
    }

    public Queue<PeopleSatisfactionEntry> getSortedSatisfactionRate()
    {
        DateTime today = new DateTime();

        List<PeopleSatisfactionEntry> result = new LinkedList<>();
        for(Communication communication : communicationHistory)
        {
            // TODO : Erase stuff that are older than COMMUNICATION_CALCULATION_CAP_IN_DAYS from persistent layer
            DateTime commAssignTime = new DateTime(communication.getDateAssigned());
            if(Days.daysBetween(today, commAssignTime).getDays() > COMMUNICATION_CALCULATION_CAP_IN_DAYS)
            {
                continue;
            }

            PeopleSatisfactionEntry peopleSatisfactionEntry = new PeopleSatisfactionEntry(
                    communication.getCommunicated(), communication.getCommunicationRating()
            );

            if(!result.contains(peopleSatisfactionEntry))
            {
                result.add(peopleSatisfactionEntry);
                continue;
            }

            result.get(result.indexOf(peopleSatisfactionEntry)).append(peopleSatisfactionEntry);
        }

        for(PeopleSatisfactionEntry peopleSatisfactionEntry : initialSatisfactionList)
        {
            if(!result.contains(peopleSatisfactionEntry))
            {
                result.add(peopleSatisfactionEntry);
                continue;
            }

            result.get(result.indexOf(peopleSatisfactionEntry)).append(peopleSatisfactionEntry);
        }


        result.sort(new Comparator<PeopleSatisfactionEntry>() {

            @Override
            public int compare(PeopleSatisfactionEntry o1, PeopleSatisfactionEntry o2) {
                return (int) (o1.getSatisfactionRate() - o2.getSatisfactionRate());
            }
        });

        return new LinkedList<>(result);
    }

    public void setSchedule(List<Communication> uninitiatedCommunications)
    {
        this.uninitiatedCommunications = uninitiatedCommunications;
    }

    public List<Communication> getUninitiatedCommunications()
    {
        return uninitiatedCommunications;
    }
}
