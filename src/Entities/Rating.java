package Entities;

public class Rating implements Comparable<Rating> {

    private float NumberVotes;
    private float AverageRating;

    public Rating(float numberVotes, float averageRating) {
        NumberVotes = numberVotes;
        AverageRating = averageRating;
    }

    public float getNumberVotes() {
        return NumberVotes;
    }

    public void setNumberVotes(float numberVotes) {
        NumberVotes = numberVotes;
    }

    public float getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(float averageRating) {
        AverageRating = averageRating;
    }

    @Override
    public int compareTo(Rating o) {
        return 0;
    }
}