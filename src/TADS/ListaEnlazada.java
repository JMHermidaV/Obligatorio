package TADS;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;

public class ListaEnlazada<T extends Comparable<T> > implements Lista<T> {

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
        ultimoComparado = first;
        boolean resultado = false;
        Nodo objetoAcomparar = first;
        while (penultimoComparado != last && ultimoComparado.getValue() != objeto){
            if (objeto == objetoAcomparar.getValue()){
                resultado = true;
                ultimoComparado = objetoAcomparar;
               }
            else{
                penultimoComparado = objetoAcomparar;
                objetoAcomparar = objetoAcomparar.getNextValue();
            }
        }
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


    public static void main(String[] args) {
        /*ListaEnlazada myList = new ListaEnlazada();
        myList.add(23);
        myList.add(2);
        myList.add(4);
        myList.add(8);
        myList.add(123);

        if (myList.size() == 5){
            System.out.println("PRUEBA 1 OK");
        }
        else{
            System.out.println("ERROR 1" + myList.size());
        }

        myList.remove(7);
        myList.remove(3);

        if (myList.size() == 4){
            System.out.println("PRUEBA 2 OK");
        }
        else{
            System.out.println("ERROR 2" + myList.size());
        }

        myList.get(4);

        if (myList.contains(123) && myList.contains(33) == false){
            System.out.println("Prueba 3 OK");
        }
        else{
            System.out.println("ERROR 3 "+myList.contains(123)+myList.contains(33));

        }

        myList.addFirst(25);
        myList.get(1);
        myList.remove(1);

        ListaEnlazada	l	=	new	ListaEnlazada();
        l.add("Obj1");
        l.add("Obj2");
        l.add("Obj3");
        ListaEnlazada	p	=	new	ListaEnlazada();
        Integer	i	=	new	Integer(1);
        Integer	j	=	new	Integer(3);
        p.addFirst(j);
        p.addFirst(i);
        l.visualizar(p);*/

        ListaEnlazada prueba = new ListaEnlazada();
        prueba.add(1);
        prueba.add(2);
        prueba.add(3);
        prueba.add(4);
        prueba.intercambiar(2,1);
        if ((Integer)prueba.get(2) == 3 && (Integer)prueba.get(3) == 2){
            System.out.println("OK 4");}
        else{
            System.out.println("ERROR Prueba4: " + prueba.get(2) + " " + prueba.get(3));}

    }
}
