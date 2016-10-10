package com.cemgunduz.jarvis.communicatior;

/**
 * Created by cem on 28/09/16.
 */
public enum CommunicationType {

    // TODO : Relies on order
    PHONE(5.0, 30, 5), LONG_MESSAGE(1.5, 8, 2), SHORT_MESSAGE(1.0, 5, 1), GROUP_MAIL(0.5, 3, 1);

    CommunicationType(double typeWeight, double typeTimeCost, double typeEffortCost) {
        this.typeWeight = typeWeight;
        this.typeTimeCost = typeTimeCost;
        this.typeEffortCost = typeEffortCost;
    }

    private double typeWeight;
    private double typeTimeCost;
    private double typeEffortCost;

    public double getTypeWeight() {
        return typeWeight;
    }

    public double getTypeTimeCost() {
        return typeTimeCost;
    }

    public double getTypeEffortCost() {
        return typeEffortCost;
    }
}
