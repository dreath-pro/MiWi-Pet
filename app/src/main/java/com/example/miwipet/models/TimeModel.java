package com.example.miwipet.models;

public class TimeModel {
    private int id;
    private String currentTime;
    private String lastTimeLogin, lastDayLogin, lastMonthLogin, lastYearLogin;
    private String currentTimeLogin, currentDayLogin, currentMonthLogin, currentYearLogin;
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

    public TimeModel(int id, String currentTime, String lastTimeLogin, String lastDayLogin, String lastMonthLogin, String lastYearLogin, int loginStreak, boolean loggedIn) {
        this.id = id;
        this.currentTime = currentTime;
        this.lastTimeLogin = lastTimeLogin;
        this.lastDayLogin = lastDayLogin;
        this.lastMonthLogin = lastMonthLogin;
        this.lastYearLogin = lastYearLogin;
        this.loginStreak = loginStreak;
        this.loggedIn = loggedIn;
    }

    public boolean isNewDay() {
        boolean result = false;

        if (Integer.parseInt(currentDayLogin) > Integer.parseInt(lastDayLogin)) {
            result = true;
        }

        if (Integer.parseInt(currentMonthLogin) > Integer.parseInt(lastMonthLogin)) {
            result = true;
        }

        if (Integer.parseInt(currentYearLogin) > Integer.parseInt(lastYearLogin)) {
            result = true;
        }

        return result;
    }

    public boolean missedLogin() {
        boolean result = false;

        int currentDay = Integer.parseInt(currentDayLogin),
                currentMonth = Integer.parseInt(currentMonthLogin),
                currentYear = Integer.parseInt(currentYearLogin);
        int lastDay = Integer.parseInt(lastDayLogin),
                lastMonth = Integer.parseInt(lastMonthLogin),
                lastYear = Integer.parseInt(lastYearLogin);

        int dayPassed = currentDay - lastDay;
        int monthPassed = currentMonth - lastMonth;
        int yearPassed = currentYear - lastYear;

        if (yearPassed == 0) {

            if (monthPassed == 0) {

                if (dayPassed >= 2) {
                    result = true;
                } else if (dayPassed < 0) {
                    result = true;
                }

            } else if (monthPassed >= 1) {

                if (dayPassed < 0) {
                    if(currentDay != 1 || lastDay != getNumberOfDays(lastMonth, lastYear))
                    {
                        result = true;
                    }
                } else {
                    result = true;
                }

            } else {
                result = true;
            }

        } else if (yearPassed >= 1) {

            if (monthPassed < 0) {
                monthPassed = Math.abs(monthPassed);

                if (monthPassed == 11) {
                    if(currentDay != 1 || lastDay != getNumberOfDays(lastMonth, lastYear))
                    {
                        result = true;
                    }
                }else
                {
                    result = true;
                }
            } else {
                result = true;
            }

        } else {
            result = true;
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
                if (isLeapYear(year)) {
                    numberOfDays = 29;
                } else {
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

    private boolean isLeapYear(int year) {
        boolean result = false;

        if (year % 400 == 0) {
            result = true;
        } else if (year % 100 == 0) {
            result = false;
        } else if (year % 4 == 0) {
            result = true;
        } else {
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

    public String getCurrentTimeLogin() {
        return currentTimeLogin;
    }

    public void setCurrentTimeLogin(String currentTimeLogin) {
        this.currentTimeLogin = currentTimeLogin;
    }

    public String getCurrentDayLogin() {
        return currentDayLogin;
    }

    public void setCurrentDayLogin(String currentDayLogin) {
        this.currentDayLogin = currentDayLogin;
    }

    public String getCurrentMonthLogin() {
        return currentMonthLogin;
    }

    public void setCurrentMonthLogin(String currentMonthLogin) {
        this.currentMonthLogin = currentMonthLogin;
    }

    public String getCurrentYearLogin() {
        return currentYearLogin;
    }

    public void setCurrentYearLogin(String currentYearLogin) {
        this.currentYearLogin = currentYearLogin;
    }
}
