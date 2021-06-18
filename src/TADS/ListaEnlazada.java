package TADS;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;

public class ListaEnlazada<T extends Comparable<T>> implements Lista<T> {

    public Nodo first = null;
    private Nodo last = null;
    private int size = 0;
    private Nodo ultimoComparado = null;
    private Nodo penultimoComparado = null;

    @Override
    public void add(T value) {
        Nodo nuevo = new Nodo(value);
        size++;
        if (first == null){
            first = nuevo;
            last = first;
        }else{
            last.setNextValue(nuevo);
            last = nuevo;
        }

    }



    @Override
    public void remove(int position) {
        if (position>size){
            System.out.println("No existe esa posición");
        }
        else if(size == 1){
            first = null;
            last = null;
            size --;
        }
        else{
            size--;
            Nodo anterior = null;
            Nodo sacado = first;
            for (int i = 1; i<=position; i++){
                if (i==position){
                    if (position == 1){
                        first = sacado.getNextValue();
                        sacado.setNextValue(null);
                    }
                    else if (sacado.getNextValue() == null){
                        anterior.setNextValue(null);
                        last = anterior;
                    }
                    else{
                        anterior.setNextValue(sacado.getNextValue());
                    }
                }
                else{
                    anterior = sacado;
                    sacado = sacado.getNextValue();
                }

            }}}

    @Override
    public Object get(int position) {
        Object devolvero = null;
        Nodo devolver = null;
        if (position>size){
            System.out.println("No existe esa posición");
        }
        else if(position <= 0){
            System.out.println("No existe esa posición");
        }
        else{
            devolver = first;
            for (int i = 1; i<position; i++){
                devolver = devolver.getNextValue();
            }
            devolvero = devolver.getValue();
        }
        return devolvero;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object objeto){
        boolean resultado = false;
        if (objeto == first.getValue()){
            resultado = true;
        }else {
            ultimoComparado = first;
            Nodo objetoAcomparar = first;
            while (penultimoComparado != last && ultimoComparado.getValue() != objeto) {
                if (objeto == objetoAcomparar.getValue()) {
                    resultado = true;
                    ultimoComparado = objetoAcomparar;
                } else {
                    penultimoComparado = objetoAcomparar;
                    objetoAcomparar = objetoAcomparar.getNextValue();
                }
            }
        }
        penultimoComparado = null;
        return resultado;
    }

    public void addFirst(T value){
        Nodo nuevo = new Nodo(value);
        size++;
        if (first == null){
            first = nuevo;
            last = first;
        }else{
            nuevo.setNextValue(first);
            first = nuevo;
        }
    }

    public void addLast(T value){
        add(value);
    }

    /*public ArrayList visualizar(ListaEnlazada P){
        ArrayList objetos = new ArrayList();
        for (int i = 0; i == size; i++){
            if(P.get(i)>size && P.get(i)<=0){
                System.out.println("Excede tamaño de lista");}
            else{
                  objetos.add(get(i)-1);
                }
        }
        return objetos;
    }*/

    public void intercambiar(Object o, int direccion){
        if(contains(o)){
            if (direccion==1){
                if (ultimoComparado.getNextValue() == null){
                    System.out.println("El valor siguiente es nulo");
                }
                else{
                    penultimoComparado.setNextValue(ultimoComparado.getNextValue());
                    ultimoComparado.setNextValue(ultimoComparado.getNextValue().getNextValue());
                    penultimoComparado.getNextValue().setNextValue(ultimoComparado);
                }
            }
            else if (direccion==-1){
                System.out.println("HAY QUE HACERLO");
            }
        }
        else{
            System.out.println("No existe ese objeto en la lista");
        }
    }
}
