package com.example;

public class CallPrice {
    private double callCoast;
    private double offline;
    private double onLandlines;

    public CallPrice() {

    }

    public CallPrice(double callCoast, double offline, double onLandlines) {
        this.callCoast = callCoast;
        this.offline = offline;
        this.onLandlines = onLandlines;
    }

    public void setCallCoast(double callCoast) {
        this.callCoast = callCoast;
    }

    public void setOffline(double offline) {
        this.offline = offline;
    }

    public void setOnLandlines(double onLandlines) {
        this.onLandlines = onLandlines;
    }

    public double getCallCoast() {
        return callCoast;
    }

    public double getOffline() {
        return offline;
    }

    public double getOnLandlines() {
        return onLandlines;
    }

    @Override
    public String toString() {
        return "CallPrice{" +
                "callCoast=" + callCoast +
                ", offline=" + offline +
                ", onLandlines=" + onLandlines +
                '}';
    }
}
