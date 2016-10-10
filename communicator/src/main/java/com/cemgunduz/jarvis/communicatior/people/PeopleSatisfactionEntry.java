package com.cemgunduz.jarvis.communicatior.people;

/**
 * Created by cem on 29/09/16.
 */
public class PeopleSatisfactionEntry {

    private People people;
    private Double satisfactionRate;

    private boolean initialRecord = false;

    public PeopleSatisfactionEntry() {
    }

    public PeopleSatisfactionEntry(People people, Double satisfactionRate) {
        this.people = people;
        this.satisfactionRate = satisfactionRate;

        adjustSatisfactionRateByPeoplePriority();
    }

    public PeopleSatisfactionEntry(People people, Double satisfactionRate, boolean initialRecord) {
        this.people = people;
        this.satisfactionRate = satisfactionRate;
        this.initialRecord = initialRecord;
    }

    public void append(PeopleSatisfactionEntry peopleSatisfactionEntry)
    {
        this.satisfactionRate += peopleSatisfactionEntry.getSatisfactionRate();
    }

    public People getPeople() {
        return people;
    }

    public Double getSatisfactionRate() {
        return satisfactionRate;
    }

    public boolean isInitialRecord() {
        return initialRecord;
    }

    private void adjustSatisfactionRateByPeoplePriority()
    {
        double adjustmentRate = 1 + people.getPriority().ordinal();
        satisfactionRate /= adjustmentRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeopleSatisfactionEntry that = (PeopleSatisfactionEntry) o;

        return people != null ? people.equals(that.people) : that.people == null;

    }

    @Override
    public int hashCode() {
        return people != null ? people.hashCode() : 0;
    }
}
