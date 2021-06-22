import Entities.*;
import TADS.HashCerrado;
import TADS.Lista;
import TADS.ListaEnlazada;
import TADS.MyHeapImpl;

import java.util.Scanner;

public class main {
    private HashCerrado<String, CastMember> castMember;
    private HashCerrado<String, Movie> movie;
    private ListaEnlazada<MovieCastMember> movieCastMember;
    private MyHeapImpl<MovieRating> movieRating;
    private ListaEnlazada<CauseOfDeath> causeOfDeath;

    /*public int menuPrincipal(){
        Scanner myObj=new Scanner(System.in);
        System.out.println("Menu Principal");
        System.out.println("Seleccione la opcion que desee:");
        System.out.println("1.Carga de datos");
        System.out.println("2.Ejecutar consultas");
        System.out.println("3.Salir");
        int opcion=myObj.nextInt();
        return opcion;
    }


    public static void main(String[] args) {
        main main=new main();
        Consultas consultas= new Consultas();
        int opcion= main.menuPrincipal();
        if (opcion==1){


        }else if(opcion==2){
            opcion=consultas.menuDeConsultas();
            if (opcion==1){
                consultas.consultaUno(main.castMember, main.movieCastMember);
            }else if(opcion==2){
                consultas.consultaDos();
            }else if(opcion==3){
                consultas.consultaTres();
            }else if(opcion==4){
                consultas.consultaCuatro();
            }else if(opcion==5){
                consultas.consultaCinco();
            }else if(opcion==6){

            }

        }else if(opcion==3){

        }


    }*/


}
