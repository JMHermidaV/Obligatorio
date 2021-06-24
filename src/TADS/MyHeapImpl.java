package TADS;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T> {

    private T[] values;

    private int size;

    private int type; //1 es heap max y -1 es heap min

    public MyHeapImpl(int length, int type) {
        this.values = (T[]) new Comparable[length];
        this.type = type;
    }

    private int getFatherPosition(int position) {
        return (position - 1) / 2;
    }

    private int getLeftChildPosition(int position) {
        return (2 * position + 1);
    }

    private int getRightChildPosition(int position) {
        return (2 * position + 2);
    }

    @Override
    public void insert(T value) {
        if (values[values.length-1] != null) {
            T[] temp = (T[]) new Comparable[values.length];
            for (int i = 0; i<size;i++){
                temp[i] = values[i];
            }
            values = (T[]) new Comparable[(values.length)*2];
            for (int i = 0; i<size;i++){
                values[i] = temp[i];
            }
        }
        int position = size;
        size++;
        values[position] = value;

        if(type == 1) {
            while (position != 0 && values[position].compareTo(values[getFatherPosition(position)]) > 0) {
                values[position] = values[getFatherPosition(position)];
                values[getFatherPosition(position)] = value;

                position = getFatherPosition(position);
            }
        }else{
            while (position != 0 && values[position].compareTo(values[getFatherPosition(position)]) < 0) {
                values[position] = values[getFatherPosition(position)];
                values[getFatherPosition(position)] = value;

                position = getFatherPosition(position);
            }
        }
    }

    @Override
    public T delete() throws EmptyHeapException {
        T valueToReturn = null;
        T swapNode = null;

        if (size == 0) {
            throw new EmptyHeapException();
        }
        valueToReturn = values[0];

        if (size == 1) {
            values[0] = null;
        } else {
            int position = 0;
            values[position] = values[size - 1];
            values[size - 1] = null;
            size--;
            if(type == 1){
                int childMaxPosition = maxPosition(getLeftChildPosition(position),
                        getRightChildPosition(position));
                while (childMaxPosition != -1  && values[childMaxPosition].compareTo(values[position]) > 0) {
                    swapNode = values[position];
                   values[position] = values[childMaxPosition];
                   values[childMaxPosition] = swapNode;
                   position = childMaxPosition;
                   childMaxPosition = maxPosition(getLeftChildPosition(position), getRightChildPosition(position));
                }
            }else{
                int childMinPosition = minPosition(getLeftChildPosition(position),
                        getRightChildPosition(position));
                while (childMinPosition != -1 && values[childMinPosition].compareTo(values[position]) > 0) {
                    swapNode = values[position];
                    values[position] = values[childMinPosition];
                    values[childMinPosition] = swapNode;
                    position = childMinPosition;
                    childMinPosition = minPosition(getLeftChildPosition(position), getRightChildPosition(position));
                }
            }
        }

        return valueToReturn;
    }

    private int maxPosition(int position1, int position2) {
        int valueToReturn = position1;
        if (values[position1]!=null && values[position2]!=null){
            if(values[position2].compareTo(values[position1])>0){
                valueToReturn = position2;
            }
        }
        if (values[position1]==null && values[position2]==null){
            valueToReturn = -1;
        }
        // Controlar posiciones fueras del arbol
        return valueToReturn;
    }

    private int minPosition(int position1, int position2) {
        int valueToReturn = position1;
        if (values[position1]!=null && values[position2]!=null){
            if(values[position2].compareTo(values[position1])<0){
                valueToReturn = position2;
            }
        }
        if (values[position1]==null && values[position2]==null){
            valueToReturn = -1;
        }
        return valueToReturn;
    }

    @Override
    public T get() throws EmptyHeapException {

        if (size == 0) {
            throw new EmptyHeapException();
        }

        return values[0];
    }

    @Override
    public int size() {
        return size;
    }
}
