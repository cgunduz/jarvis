package com.cemgunduz.jarvis.communicatior.communicatee;

import com.cemgunduz.jarvis.communicatior.Schedule;
import com.cemgunduz.jarvis.communicatior.people.People;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 03/10/16.
 */
public class CommunicateeIO {

    private String id;

    private List<People> peoples = new ArrayList<>();
    private String userName;

    private Schedule schedule = new Schedule();

    private Preferences preferences;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
