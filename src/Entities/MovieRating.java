package Entities;

import TADS.ListaEnlazada;

import java.util.List;

public class MovieRating implements Comparable<MovieRating> {

    private String imbdTitled;
    private float weightedAverage;
    private int totalVotes;
    private float meanVote;
    private float medianVote;
    private ListaEnlazada<Integer> votesRating;
    private List<Rating>[] allGenders = new List[4];
    private List<Rating>[] males = new List[4];
    private List<Rating>[] females = new List[4];
    private List<Rating>[] top1000 = new List[2];
    private List<Rating>[] us = new List[2];
    private List<Rating>[] nonUs = new List[2];

    public MovieRating(String imbdTitled, float weightedAverage, int totalVotes, float meanVote, float medianVote, ListaEnlazada<Integer> votesRating, List<Rating>[] allGenders, List<Rating>[] males, List<Rating>[] females, List<Rating>[] top1000, List<Rating>[] us, List<Rating>[] nonUs) {
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

    public ListaEnlazada<Integer> getVotesRating() {
        return votesRating;
    }

    @Override
    public int compareTo(MovieRating o) {
        return 0;
    }
}
