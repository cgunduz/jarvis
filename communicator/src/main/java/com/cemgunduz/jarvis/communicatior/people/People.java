package com.cemgunduz.jarvis.communicatior.people;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by cem on 28/09/16.
 */
public class People {
    
    private String name;
    private Priority priority;

    // Between 1 & 5
    private Integer rateOfCommunication;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Integer getRateOfCommunication() {
        return rateOfCommunication;
    }

    public void setRateOfCommunication(Integer rateOfCommunication) {
        this.rateOfCommunication = rateOfCommunication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        return name != null ? name.equals(people.name) : people.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
