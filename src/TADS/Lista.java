package TADS;

public interface Lista<T extends Comparable<T> > {
    void add(T value);
    void remove(int position);
    T get(int position);
    int size();
}
