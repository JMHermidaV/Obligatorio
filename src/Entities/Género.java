package Entities;

public class Género implements Comparable<Género> {
    private String GenreName;

    public Género(String genreName) {
        GenreName = genreName.toLowerCase();
    }

    public String getGenreName() {
        return GenreName;
    }

    public boolean equals(Género b){
        boolean equals = b.getGenreName() == this.getGenreName();
        return equals;
    }

    @Override
    public int compareTo(Género o) {
        return 0;
    }
}
