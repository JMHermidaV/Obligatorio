package TADS;

public interface Lista<T extends Comparable<T> > {
    void add(T value);
    void remove(int position);
    Object get(int position);
    int size();
}
