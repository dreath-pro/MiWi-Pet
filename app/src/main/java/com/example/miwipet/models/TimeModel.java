package com.example.miwipet.models;

public class TimeModel {
    private int id;
    private String currentTime;
    private String lastTimeLogin, lastDayLogin, lastMonthLogin, lastYearLogin;
    private int loginStreak;
    private boolean loggedIn;

    public TimeModel() {

    }

    public TimeModel(int id, String lastTimeLogin, String lastDayLogin, String lastMonthLogin, String lastYearLogin, int loginStreak, boolean loggedIn) {
        this.id = id;
        this.lastTimeLogin = lastTimeLogin;
        this.lastDayLogin = lastDayLogin;
        this.lastMonthLogin = lastMonthLogin;
        this.lastYearLogin = lastYearLogin;
        this.loginStreak = loginStreak;
        this.loggedIn = loggedIn;
    }

    public TimeModel(int id, String currentTime, String lastTimeLogin, String lastDayLogin, String lastMonthLogin, String lastYearLogin, int loginStreak, boolean rewardRefreshed) {
        this.id = id;
        this.currentTime = currentTime;
        this.lastTimeLogin = lastTimeLogin;
        this.lastDayLogin = lastDayLogin;
        this.lastMonthLogin = lastMonthLogin;
        this.lastYearLogin = lastYearLogin;
        this.loginStreak = loginStreak;
        this.loggedIn = rewardRefreshed;
    }

    public boolean isNewDay(TimeModel currentTime) {
        boolean result = false;

        if (Integer.parseInt(currentTime.getLastDayLogin()) > Integer.parseInt(lastDayLogin)) {
            result = true;
        }

        if (Integer.parseInt(currentTime.getLastMonthLogin()) > Integer.parseInt(lastMonthLogin)) {
            result = true;
        }

        if (Integer.parseInt(currentTime.getLastYearLogin()) > Integer.parseInt(lastYearLogin)) {
            result = true;
        }

        return result;
    }

    public boolean missedLogin(TimeModel currentTime) {
        boolean result = false;
        int timePassed = 0;

        int currentDay = Integer.parseInt(currentTime.getLastDayLogin()),
                currentMonth = Integer.parseInt(currentTime.getLastMonthLogin()),
                currentYear = Integer.parseInt(currentTime.getLastYearLogin());
        int lastDay = Integer.parseInt(lastDayLogin),
                lastMonth = Integer.parseInt(lastMonthLogin),
                lastYear = Integer.parseInt(lastYearLogin);

        timePassed = currentDay - lastDay;

        if (timePassed >= 2) {
            result = true;
        }else if (timePassed < 0) {

        }

        if (currentYear > lastYear) {

        }

        return result;
    }

    private int getNumberOfDays(int month, int year) {
        int numberOfDays = 0;

        switch (month) {
            case 1:
                //january
                numberOfDays = 31;
                break;
            case 2:
                //february
                if(isLeapYear(year))
                {
                    numberOfDays = 29;
                }else
                {
                    numberOfDays = 28;
                }
                break;
            case 3:
                //march
                numberOfDays = 31;
                break;
            case 4:
                //april
                numberOfDays = 30;
                break;
            case 5:
                //may
                numberOfDays = 31;
                break;
            case 6:
                //june
                numberOfDays = 30;
                break;
            case 7:
                //july
                numberOfDays = 31;
                break;
            case 8:
                //august
                numberOfDays = 31;
                break;
            case 9:
                //september
                numberOfDays = 30;
                break;
            case 10:
                //october
                numberOfDays = 31;
                break;
            case 11:
                //november
                numberOfDays = 30;
                break;
            case 12:
                //december
                numberOfDays = 31;
                break;
        }

        return numberOfDays;
    }

    private boolean isLeapYear(int year)
    {
        boolean result = false;

        if(year % 400 == 0)
        {
            result = true;
        }else if(year % 100 == 0)
        {
            result = false;
        }else if(year % 4 == 0)
        {
            result = true;
        }else
        {
            result = false;
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(String lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getLastDayLogin() {
        return lastDayLogin;
    }

    public void setLastDayLogin(String lastDayLogin) {
        this.lastDayLogin = lastDayLogin;
    }

    public String getLastMonthLogin() {
        return lastMonthLogin;
    }

    public void setLastMonthLogin(String lastMonthLogin) {
        this.lastMonthLogin = lastMonthLogin;
    }

    public String getLastYearLogin() {
        return lastYearLogin;
    }

    public void setLastYearLogin(String lastYearLogin) {
        this.lastYearLogin = lastYearLogin;
    }

    public int getLoginStreak() {
        return loginStreak;
    }

    public void setLoginStreak(int loginStreak) {
        this.loginStreak = loginStreak;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
