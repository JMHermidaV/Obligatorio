package Entities;

import TADS.ListaEnlazada;

import java.util.Date;

public class Movie {
    private String imdbTitled;
    private String title;
    private String originalTitle;
    private int year;
    private Date datePublished;
    private ListaEnlazada<String> genre;
    private int duration;
    private ListaEnlazada<String> country;
    private String language;
    private ListaEnlazada<String> director;
    private ListaEnlazada<String> writer;
    private String productionCompany;
    private ListaEnlazada<String> actors;
    private String description;
    private float avgVote;
    private int votes;
    private String budget;
    private String usaGrossIncome;
    private String worldwideGrossIncome;
    private float metaStore;
    private float reviewFromUsers;
    private float reviewFromCritics;
    private MovieRating rating;

    public Movie(String imdbTitled, String title, String originalTitle, int year, Date datePublished, ListaEnlazada<String> genre, int duration, ListaEnlazada<String> country, String language, ListaEnlazada<String> director, ListaEnlazada<String> writer, String productionCompany, ListaEnlazada<String> actors, String description, float avgVote, int votes, String budget, String usaGrossIncome, String worldwideGrossIncome, float metaStore, float reviewFromUsers, float reviewFromCritics) {
        this.imdbTitled = imdbTitled;
        this.title = title;
        this.originalTitle = originalTitle;
        this.year = year;
        this.datePublished = datePublished;
        this.genre = genre;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.director = director;
        this.writer = writer;
        this.productionCompany = productionCompany;
        this.actors = actors;
        this.description = description;
        this.avgVote = avgVote;
        this.votes = votes;
        this.budget = budget;
        this.usaGrossIncome = usaGrossIncome;
        this.worldwideGrossIncome = worldwideGrossIncome;
        this.metaStore = metaStore;
        this.reviewFromUsers = reviewFromUsers;
        this.reviewFromCritics = reviewFromCritics;
    }

    public String getImdbTitled() {
        return imdbTitled;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getYear() {
        return year;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public int getDuration() {
        return duration;
    }

    public ListaEnlazada<String> getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public ListaEnlazada<String> getDirector() {
        return director;
    }

    public ListaEnlazada<String> getWiter() {
        return writer;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public ListaEnlazada<String> getActors() {
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public float getAvgVote() {
        return avgVote;
    }

    public int getVotes() {
        return votes;
    }

    public String getBudget() {
        return budget;
    }

    public String getUsaGrossIncome() {
        return usaGrossIncome;
    }

    public String getWorldwideGrossIncome() {
        return worldwideGrossIncome;
    }

    public float getMetaStore() {
        return metaStore;
    }

    public float getReviewFromUsers() {
        return reviewFromUsers;
    }

    public float getReviewFromCritics() {
        return reviewFromCritics;
    }


    public boolean equals(Object b) {
        boolean equals = false;

        if (b instanceof Movie) {
            equals = ((Movie) b).getImdbTitled() == this.getImdbTitled();
        }

        return equals;
    }
}