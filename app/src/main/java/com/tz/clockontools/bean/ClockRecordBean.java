package com.tz.clockontools.bean;

public class ClockRecordBean {
    /**
     * yyyy-MM-dd HH:mm:SS
     */
    public String clockTime;
    public int year;
    public int month;
    public int day;

    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}