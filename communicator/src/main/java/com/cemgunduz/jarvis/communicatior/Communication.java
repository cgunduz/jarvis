package com.cemgunduz.jarvis.communicatior;

import com.cemgunduz.jarvis.communicatior.communicatee.Communicatee;
import com.cemgunduz.jarvis.communicatior.people.People;

import java.time.DayOfWeek;
import java.util.Date;

/**
 * Created by cem on 28/09/16.
 */
public class Communication {

    private People communicated;
    private CommunicationType communicationType;

    private DayOfWeek dayOfWeek;
    private Date dateAssigned;
    private Integer day;

    public Communication(People communicated, CommunicationType communicationType, Date dateAssigned, Integer day) {
        this.communicated = communicated;
        this.communicationType = communicationType;
        this.dateAssigned = dateAssigned;
        this.day = day;
    }

    public People getCommunicated()
    {
        return communicated;
    }

    public double getCommunicationRating()
    {
        return communicationType.getTypeWeight();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public Double getEstimatedTimeLength()
    {
        Double remain = communicationType.getTypeTimeCost();
        return remain;
    }
}
