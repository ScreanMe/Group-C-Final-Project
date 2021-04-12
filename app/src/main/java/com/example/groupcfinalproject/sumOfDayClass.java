package com.example.groupcfinalproject;

public class sumOfDayClass {
    private int sum;
    private String date;

    public sumOfDayClass()
    {};

    public sumOfDayClass(String date, int sum)
    {
        this.date = date;
        this.sum = sum;
    };

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return date + ": $" + sum;
    }
}
