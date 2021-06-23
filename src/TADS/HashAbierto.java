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
       /* HashNode node=null;

        int position=key.hashCode()%sizeHash;

        int posicion=key.hashCode()%sizeHash;

        if(tableHash[posicion]==null){
            //no existe un nodo con esa key (no se que habría q hacer acá)
        }
        else{
            if(tableHash[posicion].getKey()==key){
                node=tableHash[posicion];
            }else if (tableHash[posicion]==null){

            }else {
                while ((int i =0)<tableHash[posicion].size()){

                }
            }
        }
*/

       return null; //va return node
    }


    @Override
    public void remove(K key) {


    }

    @Override
    public HashNode<K, V>[] getTableHash() {
        return new HashNode[0];
    }

    @Override
    public int getSizeHash() {
        return 0;
    }
}
