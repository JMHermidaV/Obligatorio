package TADS;

public class Nodo<T extends Comparable<T> > {

    private T value;
    private Nodo nextValue;

    public Nodo(T value) {
        this.value = value;
        nextValue = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo getNextValue() {
        return nextValue;
    }

    public void setNextValue(Nodo nextValue) {
        this.nextValue = nextValue;
    }
}
