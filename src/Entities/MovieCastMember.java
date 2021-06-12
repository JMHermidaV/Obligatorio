package Entities;

import TADS.ListaEnlazada;

public class MovieCastMember implements Comparable<MovieCastMember> {
    private String imdbTitled;
    private int ordering;
    private String imdbName;
    private String catogory;
    private String job;
    private ListaEnlazada<String> characters;

    public MovieCastMember(String imdbTitled, int ordering, String imdbName, String catogory, String job, ListaEnlazada<String> characters) {
        this.imdbTitled = imdbTitled;
        this.ordering = ordering;
        this.imdbName = imdbName;
        this.catogory = catogory;
        this.job = job;
        this.characters = characters;
    }

    public String getImdbTitled() {
        return imdbTitled;
    }

    public int getOrdering() {
        return ordering;
    }

    public String getImdbName() {
        return imdbName;
    }

    public String getCatogory() {
        return catogory;
    }

    public String getJob() {
        return job;
    }

    public ListaEnlazada<String> getCharacters() {
        return characters;
    }
}