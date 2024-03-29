package Entities;

import TADS.Lista;
import TADS.ListaEnlazada;

import java.util.Date;
import java.util.Objects;

public class CastMember implements Comparable<CastMember> {

    private String imdbNameId;
    private String name;
    private String birthName;
    private int height;
    private String bio;
    private int birthYear;
    private String birthPlace;
    private int deathYear;
    private String deathPlace;
    private String spousesString;
    private int spouses;
    private int divorces;
    private int spousesWithChildren;
    private int children;
    private String causesOfDeath;
    private int apariciones=0;
    private boolean recorrido=false;
    private boolean counted = false;

    public CastMember(String imdbNameId, String name, String birthName, int height, String bio, int birthYear, String birthPlace, int deathYear, String deathPlace, String spousesString, int spouses, int divorces, int spousesWithChildren, int children, String causesOfDeath) {
        this.imdbNameId = imdbNameId;
        this.name = name;
        this.birthName = birthName;
        this.height = height;
        this.bio = bio;
        this.birthYear = birthYear;
        this.birthPlace = birthPlace;
        this.deathYear = deathYear;
        this.deathPlace = deathPlace;
        this.spousesString = spousesString;
        this.spouses = spouses;
        this.divorces = divorces;
        this.spousesWithChildren = spousesWithChildren;
        this.children = children;
        this.causesOfDeath = causesOfDeath;
    }

    public String getImdbNameId() {
        return imdbNameId;
    }

    public String getName() {
        return name;
    }

    public String getBirthName() {
        return birthName;
    }

    public int getHeight() {
        return height;
    }

    public String getBio() {
        return bio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public String getDeathPlace() {
        return deathPlace;
    }

    public String getSpousesString() {
        return spousesString;
    }

    public int getSpouses() {
        return spouses;
    }

    public int getDivorces() {
        return divorces;
    }

    public int getSpousesWithChildren() {
        return spousesWithChildren;
    }

    public int getChildren() {
        return children;
    }

    public String getCausesOfDeath() {
        return causesOfDeath;
    }


    @Override
    public int hashCode() {
       return Integer.parseInt(this.getImdbNameId().substring(2,9).replaceAll("^0+(?!$)", ""));
    }

    public boolean equals(Object b) {
        boolean equals = false;

        if (b instanceof CastMember) {
            equals = ((CastMember) b).getImdbNameId() == this.getImdbNameId();
        }

        return equals;
    }

    public int getApariciones() {
        return apariciones;
    }

    public void setApariciones() {
        this.apariciones +=1;
    }

    public void resetApariciones() {
        this.apariciones = 0;
    }

    public boolean isDead(){
        boolean r=false;
        if (this.causesOfDeath != null){
            r=true;
        }
        return r;
    }

    public boolean isRecorrido() {
        return recorrido;
    }

    public void setRecorrido() {
        this.recorrido = true;
    }

    public void resetRecorrido() {
        this.recorrido = false;
    }

    public boolean isCounted() {
        return counted;
    }
    public void resetCounted() {
        this.counted = false;
    }
    public void setCounted() {
        this.counted = true;
    }

    @Override
    public int compareTo(CastMember o) {
        return compare(this.getApariciones(),o.getApariciones());
    }
    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }
}