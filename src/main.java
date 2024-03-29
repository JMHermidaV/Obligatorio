import Entities.*;
import TADS.*;

import java.util.Scanner;

public class main {

   public int menuPrincipal(){
        Scanner myObj=new Scanner(System.in);
        System.out.println("Menu Principal");
        System.out.println("Seleccione la opcion que desee:");
        System.out.println("1.Carga de datos");
        System.out.println("2.Ejecutar consultas");
        System.out.println("3.Salir");
        int opcion=myObj.nextInt();
        return opcion;
    }


    public static void main(String[] args) throws EmptyHeapException {
        main main = new main();
        Consultas consultas = new Consultas();
        CSVReaderInJava data = null;
        boolean corriendo = true;
        while (corriendo) {
            int opcion = main.menuPrincipal();
            if (opcion == 1) {
                data = new CSVReaderInJava();
            } else if (opcion == 2) {
                if (data == null) {
                    System.out.println("Es necesaria la carga de datos para realizar las consultas");
                } else {
                    boolean enConsultas = true;
                    while (enConsultas) {
                        opcion = consultas.menuDeConsultas();
                        if (opcion == 1) {
                            consultas.consultaUno(data.getCastMembersHash(), data.getMovieCastMemberHash());
                        } else if (opcion == 2) {
                            consultas.consultaDos(data.getCastMembersHash(), data.getMovieCastMemberHash());
                        } else if (opcion == 3) {
                            consultas.consultaTres(data.getMovieRatingsHeapMax(), data.getMoviesHash(), data.getMovieCastMemberHash(), data.getCastMembersHash());
                        } else if (opcion == 4) {
                            consultas.consultaCuatro(data.getCastMembersHash(), data.getMovieCastMemberHash());
                        } else if (opcion == 5) {
                            consultas.consultaCinco(data.getMoviesHash(), data.getMovieCastMemberHash(),data.getCastMembersHash());
                        } else if (opcion == 6) {
                            enConsultas = false;
                            corriendo = false;
                        }
                    }
                }
            } else if (opcion == 3) {
                corriendo = false;
            }

        }
    }
}
