package Entities;

public class Género implements Comparable<Género> {
    private String GenreName;
    private int counterGenero=0;
    public Género(String genreName) {
        GenreName = genreName;
    }
    public String getGenreName() {
        return GenreName;
    }
    public int getCounterGenero() {
        return counterGenero;
    }
    public void setCounterGenero() {
        this.counterGenero +=1;
    }
    public boolean equals(Género b){
        boolean equals = b.getGenreName().equals(this.getGenreName());
        return equals;
    }
    @Override
    public int compareTo(Género o) {
        return compare(this.getCounterGenero(),o.getCounterGenero());}
    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }
}