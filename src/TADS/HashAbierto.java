package TADS;

public class HashAbierto<K,V> implements HashTable<K,V> {
    private int sizeHash;
    private HashNode[] tableHash;


    public HashAbierto(int size){
        tableHash= new HashNode[size];
        this.sizeHash=size;
    }


    @Override
    public void put(K key, V value) {
        int positionInHashTable = key.hashCode() % sizeHash;
        boolean agregado=false;

        while(!agregado){
            if (tableHash[positionInHashTable]==null || tableHash[positionInHashTable].isDeleted()){
                tableHash[positionInHashTable]= new HashNode(key,value);
                agregado=true;
            }else {
                // Si es linked list, agregar nodo al final, si hay solo un nodo hacer la linked list y agregar 2nodos.


            }
        }

    }

    @Override
    public HashNode get(K key) {
        HashNode node=null;
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

        return node;
    }


    @Override
    public void remove(K clave) {

    }
}
