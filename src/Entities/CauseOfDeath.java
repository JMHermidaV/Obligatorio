package Entities;

public class CauseOfDeath implements Comparable<Entities.CauseOfDeath> {

    private String name;

    private int numeroDeMuertes=1;

    public CauseOfDeath(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Entities.CauseOfDeath o) {
        return compare(this.getNumeroDeMuertes(),o.getNumeroDeMuertes());
    }

    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }

    public boolean equals(Object b) {
        boolean equals = false;

        if (b instanceof CauseOfDeath) {
            equals = ((CauseOfDeath) b).getName() == this.getName();
        }

        return equals;
    }

    public int getNumeroDeMuertes() {
        return numeroDeMuertes;
    }

    public void setNumeroDeMuertes() {
        this.numeroDeMuertes+=1;
    }
}