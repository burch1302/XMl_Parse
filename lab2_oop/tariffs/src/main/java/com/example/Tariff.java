package com.example;

import java.util.ArrayList;
import java.util.List;

public class Tariff {
    private String name;
    private String operatorName;
    private int payroll;
    private double smsPrice;

    private List<CallPrice> cpList = new ArrayList<>();
    private List<Parameters> paramList = new ArrayList<>();

    public Tariff() {

    }

    public Tariff(String name, String operatorName, int payroll, double smsPrice, List<CallPrice> cpList,
            List<Parameters> paramList) {
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.cpList = cpList;
        this.paramList = paramList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public List<CallPrice> getCpList() {
        return cpList;
    }

    public void setCpList(List<CallPrice> cpList) {
        this.cpList = cpList;
    }

    public List<Parameters> getParamList() {
        return paramList;
    }

    public void setParamList(List<Parameters> paramList) {
        this.paramList = paramList;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payroll=" + payroll +
                ", smsPrice=" + smsPrice +
                ", callPriceList=" + cpList +
                ", parametersList=" + paramList +
                '}';
    }

}
