package TADS;

public class HashCerrado<K,V> implements HashTable<K,V> {
    private int sizeHash;
    private HashNode[] tableHash;
    private int i=0;

    public HashCerrado(int size){
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
                positionInHashTable+=i;
                i++;
            }
        }

    }

    @Override
    public HashNode get(K key) {
        HashNode node=null;
        int posibleposicion=key.hashCode()%sizeHash;
        int j=1;
        while (node==null || j!=i){
            if(tableHash[posibleposicion].getKey()==key){
                if (!tableHash[posibleposicion].isDeleted()){
                    node=tableHash[posibleposicion];
                }
            }else{
                for(; j<i; j++){
                    posibleposicion+=j;
                }
            }
        }
        return node;
    }

    @Override
    public void remove(K key) {
        HashNode node=get(key);

        if (node !=null){
            node.setDeleted(true);
        }

    }
}
