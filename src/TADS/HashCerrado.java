package TADS;

public class HashCerrado<K,V> implements HashTable<K,V> {
    private int sizeHash;
    private HashNode[] tableHash;
    private int i=0;
    private float loadFactor = 0;

    public HashCerrado(int size){
        tableHash= new HashNode[size];
        this.sizeHash=size;
    }

    @Override
    public void put(K key, V value) {
        loadFactor = (loadFactor * sizeHash + 1)/sizeHash;
        if(loadFactor<= 0.75) {
            int positionInHashTable = key.hashCode() % sizeHash;

            if (tableHash[positionInHashTable] == null || tableHash[positionInHashTable].isDeleted()) {
                tableHash[positionInHashTable] = new HashNode(key, value);
            } else {
                positionInHashTable = this.funcionColision(positionInHashTable);
                tableHash[positionInHashTable] = new HashNode(key, value);
            }
        }else{
            //deberíamos crear otro hash más grande
        }
    }

    private int funcionColision(int position){
        position+=1;
        while (tableHash[position] != null && !tableHash[position].isDeleted()) {
            if (tableHash[position] != null && !tableHash[position].isDeleted()) {
                position += 1;
            }
        }
        return position;
    }

    @Override
    public HashNode get(K key) {
        HashNode node=null;
        int posibleposition=key.hashCode()%sizeHash;
        while (node==null && posibleposition!=sizeHash){
            if(tableHash[posibleposition].getKey()==key){
                if (!tableHash[posibleposition].isDeleted()){
                    node=tableHash[posibleposition];
                }
                else{
                    posibleposition = sizeHash;
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
