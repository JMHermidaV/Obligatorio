public interface HashTable <K,V> {
    public void put(K key, V value);
    public HashNode get(K key);
    public void remove(K clave);
}
