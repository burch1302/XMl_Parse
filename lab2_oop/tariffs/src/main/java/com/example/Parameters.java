package com.example;

public class Parameters {
    private int favNumber;
    private String tariffType;// Тип тарификации: "12-секундная", "минутная"
    private double connectionFee;

    public Parameters() {

    }

    public Parameters(int favNumber, String tariffType, double connectionFee) {
        this.favNumber = favNumber;
        this.tariffType = tariffType;
        this.connectionFee = connectionFee;
    }

    public void setFavNumber(int favNumber) {
        this.favNumber = favNumber;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public void setConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
    }

    public int getFavNumber() {
        return favNumber;
    }

    public String getTariffType() {
        return tariffType;
    }

    public double getConnectionFee() {
        return connectionFee;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "favoriteNumber=" + favNumber +
                ", billingType='" + tariffType + '\'' +
                ", connectionFee=" + connectionFee +
                '}';
    }
}
