package TADS;

public class HashCerrado<K,V> implements HashTable<K,V> {
    private int sizeHash;
    private HashNode<K,V>[] tableHash;
    private float loadFactor = 0;

    public HashCerrado(int size){
        tableHash= new HashNode[size];
        this.sizeHash=size;
    }

    @Override
    public void put(K key, V value) {
        loadFactor = (loadFactor * sizeHash + 1)/sizeHash;
        if(loadFactor<= 0.75) {
            int a = key.hashCode();
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
        int i = 1;
        while (tableHash[position] != null && !tableHash[position].isDeleted()) {
            if (tableHash[position] != null && !tableHash[position].isDeleted()) {
                position += i;
            }
        }
        return position;
    }

    @Override
    public HashNode get(K key) {
        HashNode<K,V> node=null;
        int i = 1;
        int posibleposition = key.hashCode()%sizeHash;
        if(tableHash[posibleposition] != null){
            while (node==null && posibleposition!=sizeHash){
                if(tableHash[posibleposition] != null) {
                    if (tableHash[posibleposition].getKey() == key) {
                        if (!tableHash[posibleposition].isDeleted()) {
                            node = tableHash[posibleposition];
                        } else {
                            return null;
                        }
                    } else {
                        posibleposition += i;
                    }
                }else{
                    return null;
                }
            }if(posibleposition==sizeHash){
                return null;
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

    public int getSizeHash() {
        return sizeHash;
    }

    public HashNode<K, V>[] getTableHash() {
        return tableHash;
    }
}
