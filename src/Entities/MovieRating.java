package Entities;

import TADS.Lista;
import TADS.ListaEnlazada;

import java.util.List;

public class MovieRating implements Comparable<MovieRating> {

    private String imbdTitled;
    private float weightedAverage;
    private int totalVotes;
    private float meanVote;
    private float medianVote;
    private Lista<Integer> votesRating;
    private Lista<Rating> allGenders;
    private Lista<Rating> males;
    private Lista<Rating> females;
    private Rating top1000;
    private Rating us;
    private Rating nonUs;

    public MovieRating(String imbdTitled, float weightedAverage, int totalVotes, float meanVote, float medianVote, Lista<Integer> votesRating, Lista<Rating> allGenders, Lista<Rating> males,Lista<Rating> females, Rating top1000, Rating us, Rating nonUs) {
        this.imbdTitled = imbdTitled;
        this.weightedAverage = weightedAverage;
        this.totalVotes = totalVotes;
        this.meanVote = meanVote;
        this.medianVote = medianVote;
        this.votesRating = votesRating;
        this.allGenders = allGenders;
        this.males = males;
        this.females = females;
        this.top1000 = top1000;
        this.us = us;
        this.nonUs = nonUs;
    }

    public String getImbdTitled() {
        return imbdTitled;
    }

    public float getWeightedAverage() {
        return weightedAverage;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public float getMeanVote() {
        return meanVote;
    }

    public float getMedianVote() {
        return medianVote;
    }

    public Lista<Integer> getVotesRating() {
        return votesRating;
    }

    public Lista<Rating> getAllGenders() {
        return allGenders;
    }

    public Lista<Rating> getMales() {
        return males;
    }

    public Lista<Rating> getFemales() {
        return females;
    }

    public Rating getTop1000() {
        return top1000;
    }

    public Rating getUs() {
        return us;
    }

    public Rating getNonUs() {
        return nonUs;
    }

    @Override
    public int compareTo(MovieRating o) {
        return compare(this.getWeightedAverage(),o.getWeightedAverage());
    }

    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }
}
