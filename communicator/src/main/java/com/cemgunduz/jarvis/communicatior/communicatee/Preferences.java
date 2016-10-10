package com.cemgunduz.jarvis.communicatior.communicatee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 29/09/16.
 */
public class Preferences {

    private Double amountOfTimePerWeek;

    // Sunday -> 1 , Monday 2 as stated on calendar
    private List<Integer> availableDayOfWeek;

    // TODO : Not used atm
    private Double amountOfEffort;

    public Double getAmountOfTimePerWeek() {

        return amountOfTimePerWeek;
    }

    public List<Integer> getAvailableDayOfWeek() {
        return availableDayOfWeek;
    }

    public List<Integer> getRemainingDaysOfTheWeek() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return availableDayOfWeek.stream().filter(item -> item >= day).collect(Collectors.toList());
    }

    public void createTestPreferences()
    {
        amountOfTimePerWeek = 75.0;
        availableDayOfWeek = new ArrayList<>();
        availableDayOfWeek.addAll(Arrays.asList(1,2,5,6));
    }

}
