package org.ulearn;

public class Country {
    private String nameOfCountry;
    private String subregion;
    private String region;
    private long internetUsers;
    private long population;

    public Country(String nameOfCountry, String subregion, String region, long internetUsers, long population) {
        this.nameOfCountry = nameOfCountry;
        this.subregion = subregion;
        this.region = region;
        this.internetUsers = internetUsers;
        this.population = population;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getRegion() {
        return region;
    }

    public long getInternetUsers() {
        return internetUsers;
    }

    public long getPopulation() {
        return population;
    }
}
