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

        if (tableHash[positionInHashTable]==null || tableHash[positionInHashTable].isDeleted()){
            tableHash[positionInHashTable]= new HashNode(key,value);
        }else {
            positionInHashTable=this.funcion_colision(positionInHashTable);
            tableHash[positionInHashTable]= new HashNode(key,value);
        }

    }

    private int funcion_colision(int position){
        position+=1;
        while (tableHash[position] !=null || !tableHash[position].isDeleted()) {
            if (tableHash[position] != null || !tableHash[position].isDeleted()) {
                position += 1;
            }
        }
        return position;
    }

    @Override
    public HashNode get(K key) {
        HashNode node=null;
        int posibleposition=key.hashCode()%sizeHash;
        int j=1;
        while (node==null || j!=sizeHash){
            if(tableHash[posibleposition].getKey()==key){
                if (!tableHash[posibleposition].isDeleted()){
                    node=tableHash[posibleposition];
                }
            }else{
                posibleposition+=1;
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
