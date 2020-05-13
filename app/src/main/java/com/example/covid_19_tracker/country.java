package com.example.covid_19_tracker;

public class country {

   private String country ;
   private int cases ;
   private int todayCases ;
   private int deaths ;
   private int todayDeaths ;
   private int recovered ;
   private int active ;
   private int critical ;
   private int casesPerOneMillion ;
   private int DeathsPerOneMillion ;
   private String firstCase ;
   private int totalTests ;


    public int getTotalTests() {
        return totalTests;
    }

    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovred() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public int getDEathsPerOneMillion() {
        return DeathsPerOneMillion;
    }

    public String getFirstCase() {
        return firstCase;
    }
}
