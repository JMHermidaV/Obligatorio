package TADS;

public class HashAbierto<K,V> implements HashTable<K,V> {
    private int sizeHash;
    private ListaEnlazada[] tableHash;


    public HashAbierto(int size){
        tableHash= new ListaEnlazada[size];
        this.sizeHash=size;
    }


    @Override
    public void put(K key, V value) {
        int positionInHashTable = key.hashCode() % sizeHash;
        boolean agregado=false;




    }

    @Override
    public HashNode get(K key) {
        HashNode node=null;
        int position=key.hashCode()%sizeHash;

        return node;
    }


    @Override
    public void remove(K key) {


    }
}
