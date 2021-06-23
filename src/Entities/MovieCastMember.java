package Entities;

import TADS.Lista;
import TADS.ListaEnlazada;

import java.util.Objects;

public class MovieCastMember implements Comparable<MovieCastMember> {
    private String imdbTitled;
    private int ordering;
    private String imdbName;
    private String catogory;
    private String job;
    private Lista<String> characters;

    public MovieCastMember(String imdbTitled, int ordering, String imdbName, String catogory, String job, Lista<String> characters) {
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

    public Lista<String> getCharacters() {
        return characters;
    }

    @Override
    public boolean equals(Object o) {
        boolean resultado = false;
        if (o instanceof MovieCastMember) {
            if ((((MovieCastMember) o).getImdbName() == this.imdbName) || ((MovieCastMember) o).getImdbTitled() == this.imdbTitled) {
                resultado = true;
            }
        }
        return resultado;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.getImdbTitled().substring(2,9));
    }

    @Override
    public int compareTo(MovieCastMember o) {
        return 0;
    }
}