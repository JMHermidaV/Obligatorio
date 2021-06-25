package Entities;

import TADS.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Consultas {

    public int menuDeConsultas(){
        System.out.println("1. Indicar el Top 5 de actores/actrices que más apariciones han tenido a\n" +
                "lo largo de los años.");
        System.out.println("2. Indicar el Top 5 de las causas de muerte más frecuentes en directores\n" +
                "y productores nacidos en Italia, Estados Unidos, Francia y UK.");
        System.out.println("3. Mostrar de las 14 películas con más weightedAverage, el promedio de\n" +
                "altura de sus actores/actrices si su valor es distinto de nulo.");
        System.out.println("4. Indicar el año más habitual en el que nacen los actores y las actrices");
        System.out.println("5. Indicar el Top 10 de géneros de películas más populares, en las\n" +
                "cuales al menos un actor/actriz tiene 2 o más hijos.");
        System.out.println("6. Salir.");
        Scanner myObj=new Scanner(System.in);
        int opcion=myObj.nextInt();
        return opcion;
    }

    public void consultaUno(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember){
        long tiempoInicial=System.currentTimeMillis();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size()) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor") || category.equals("actress")) {
                        Integer a = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(a);
                        castMember.get(a).getValue().setApariciones();
                    }
                    j++;
                }
            }
        }
        MyHeap<CastMember> CastMembersHeapMax = new MyHeapImpl<>(85856, 1);
        for(int i=0; i<castMember.getSizeHash();i++){
            if(castMember.getTableHash()[i]!=null) {
                CastMembersHeapMax.insert(castMember.getTableHash()[i].getValue());
            }
        }
        CastMember[] topFive=new CastMember[5];
        try {
            topFive[0] = CastMembersHeapMax.delete();
            topFive[1] = CastMembersHeapMax.delete();
            topFive[2] = CastMembersHeapMax.delete();
            topFive[3] = CastMembersHeapMax.delete();
            topFive[4] = CastMembersHeapMax.delete();
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }


        System.out.println("Nombre actor/actriz:"+ topFive[0].getName()+"   Cantidad de apariciones:"+topFive[0].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[1].getName()+"   Cantidad de apariciones:"+topFive[1].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[2].getName()+"   Cantidad de apariciones:"+topFive[2].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[3].getName()+"   Cantidad de apariciones:"+topFive[3].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[4].getName()+"   Cantidad de apariciones:"+topFive[4].getApariciones());
        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
    }

    public void consultaDos(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember){
        long tiempoInicial=System.currentTimeMillis();
        Lista<CauseOfDeath> causas = new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size()) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("producer") || category.equals("director")) {
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        String birthPlace = castMember.get(key).getValue().getBirthPlace();
                        if(birthPlace.contains("USA") || birthPlace.contains("UK") || birthPlace.contains("France") || birthPlace.contains("Italy")){
                            if(!node.getValue().isRecorrido()) {
                                node.getValue().setRecorrido();
                                boolean entro = false;
                                if (!node.getValue().getCausesOfDeath().equals("")) {
                                    if(causas.size() == 0){
                                        CauseOfDeath causa = new CauseOfDeath(node.getValue().getCausesOfDeath());
                                        causas.add(causa);
                                    }else {
                                        for (int k = 1; k < causas.size() + 1; k++) {
                                            if (node.getValue().getCausesOfDeath().equals(causas.get(k).getName())) {
                                                causas.get(k).setNumeroDeMuertes();
                                                entro = true;
                                            }
                                        }
                                        if (!entro) {
                                            CauseOfDeath causa = new CauseOfDeath(node.getValue().getCausesOfDeath());
                                            causas.add(causa);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    j++;
                }
            }
        }

        MyHeap<CauseOfDeath> causesHeapMax = new MyHeapImpl<>(10000, 1);
        for(int i=1; i<causas.size()+1;i++){
            causesHeapMax.insert(causas.get(i));
        }

        CauseOfDeath[] topFive = new CauseOfDeath[5];
        try {
            topFive[0] = causesHeapMax.delete();
            topFive[1] = causesHeapMax.delete();
            topFive[2] = causesHeapMax.delete();
            topFive[3] = causesHeapMax.delete();
            topFive[4] = causesHeapMax.delete();
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }
        System.out.println("Causa de muerte:"+ topFive[0].getName()+"   Cantidad de personas:"+topFive[0].getNumeroDeMuertes());
        System.out.println("Causa de muerte:"+ topFive[1].getName()+"   Cantidad de personas:"+topFive[1].getNumeroDeMuertes());
        System.out.println("Causa de muerte:"+ topFive[2].getName()+"   Cantidad de personas:"+topFive[2].getNumeroDeMuertes());
        System.out.println("Causa de muerte:"+ topFive[3].getName()+"   Cantidad de personas:"+topFive[3].getNumeroDeMuertes());
        System.out.println("Causa de muerte:"+ topFive[4].getName()+"   Cantidad de personas:"+topFive[4].getNumeroDeMuertes());
        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
    }



    public void consultaTres(MyHeap<MovieRating> movieRating, HashTable<Integer, Movie> movie, HashTable<Integer, Lista<MovieCastMember>> movieCastMember, HashTable<Integer, CastMember> castMember) throws EmptyHeapException {
        long tiempoInicial = System.currentTimeMillis();
        Movie[] top = new Movie[14];
        int i = 0;
        while (movieRating.get() != null && top[13] == null) {
            Integer key = Integer.parseInt(movieRating.delete().getImbdTitled().substring(2, 9));
            Movie movietemp = movie.get(key).getValue();
            if (movietemp.getYear() <= 1960 && movietemp.getYear() >= 1950) {
                top[i] = movietemp;
                i++;
            }
        }
        for (i = 0; i < top.length; i++) {
            int movieKey = Integer.parseInt(top[i].getImdbTitled().substring(2, 9));
            Lista<MovieCastMember> castmembersTemp = movieCastMember.get(movieKey).getValue();
            for (int k = 1; k < (castmembersTemp.size() + 1); k++) {
                MovieCastMember movieCastMemberTemp = castmembersTemp.get(k);
                CastMember castTemp = castMember.get(Integer.parseInt(movieCastMemberTemp.getImdbName().substring(2, 9))).getValue();
                if (castTemp.getHeight() != 0) {
                    int altura = castTemp.getHeight();
                    top[i].setSumaAltura(altura);
                    top[i].setActoresConAltura();
                }
            }
        }
        for (i = 0; i < top.length; i++) {
            if (top[i].getActoresConAltura() != 0) {
                String MovieName = top[i].getOriginalTitle();
                System.out.println("Id película:" + top[i].getTitle() + "   Nombre:" + top[i].getOriginalTitle() + "    Altura promedio de actores:" + top[i].getAlturaPromedio());
            }
        }

        long tiempoFinal = System.currentTimeMillis();
        long tiempo = tiempoFinal - tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:" + tiempo);
    }

    public void consultaCuatro(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember) throws EmptyHeapException {
        long tiempoInicial=System.currentTimeMillis();
        Lista<AnoNacimientoPorCaegory> anosNacimientoActors = new ListaEnlazada<>();
        Lista<AnoNacimientoPorCaegory> anosNacimientoActress = new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size()) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor")) {
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        int birthYear = castMember.get(key).getValue().getBirthYear();
                        if(!node.getValue().isCounted()) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            if (node.getValue().getBirthYear()!=0) {
                                if(anosNacimientoActors.size() == 0){
                                    AnoNacimientoPorCaegory temp = new AnoNacimientoPorCaegory(node.getValue().getBirthYear(),"actor");
                                    anosNacimientoActors.add(temp);
                                }else {
                                    for (int k = 1; k < anosNacimientoActors.size() + 1; k++) {
                                        if (node.getValue().getBirthYear() == anosNacimientoActors.get(k).getAnoNacimiento()) {
                                            anosNacimientoActors.get(k).setCantidadPersonas();
                                            entro = true;
                                        }
                                    }
                                    if (!entro) {
                                        AnoNacimientoPorCaegory temp = new AnoNacimientoPorCaegory(node.getValue().getBirthYear(),"actor");
                                        anosNacimientoActors.add(temp);
                                    }
                                }
                            }
                        }
                    }else if(category.equals("actress")){
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        int birthYear = castMember.get(key).getValue().getBirthYear();
                        if(!node.getValue().isCounted()) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            if (node.getValue().getBirthYear()!=0) {
                                if(anosNacimientoActress.size() == 0){
                                    AnoNacimientoPorCaegory temp = new AnoNacimientoPorCaegory(node.getValue().getBirthYear(),"actress");
                                    anosNacimientoActress.add(temp);
                                }else {
                                    for (int k = 1; k < anosNacimientoActress.size() + 1; k++) {
                                        if (node.getValue().getBirthYear() == anosNacimientoActress.get(k).getAnoNacimiento()) {
                                            anosNacimientoActress.get(k).setCantidadPersonas();
                                            entro = true;
                                        }
                                    }
                                    if (!entro) {
                                        AnoNacimientoPorCaegory temp = new AnoNacimientoPorCaegory(node.getValue().getBirthYear(),"actress");
                                        anosNacimientoActress.add(temp);
                                    }
                                }
                            }
                        }
                    }
                    j++;
                }
            }
        }
        MyHeap<AnoNacimientoPorCaegory> anosActorsHeapMax = new MyHeapImpl<>(2027, 1);
        MyHeap<AnoNacimientoPorCaegory> anosActressHeapMax = new MyHeapImpl<>(2027, 1);
        for(int i=1; i<anosNacimientoActors.size()+1;i++){
            anosActorsHeapMax.insert(anosNacimientoActors.get(i));
        }
        for(int i=1; i<anosNacimientoActress.size()+1;i++){
            anosActressHeapMax.insert(anosNacimientoActress.get(i));
        }

        System.out.println("Actores:");
        System.out.println("    Año:"+anosActorsHeapMax.delete().getAnoNacimiento());
        System.out.println("    Cantidad:"+anosActorsHeapMax.delete().getCantidadPersonas());

        System.out.println("Actrices:");
        System.out.println("    Año: "+anosActressHeapMax.delete().getAnoNacimiento());
        System.out.println("    Cantidad: "+anosActressHeapMax.delete().getCantidadPersonas());

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");

    }

    /*public void consultaCinco(){
        long tiempoInicial=System.currentTimeMillis();
        String[][] topTen=new String[10][2];

        System.out.println("Genero pelicula:"+ topTen[0][0]+"Cantidad:"+topTen[0][1]);
        System.out.println("Genero pelicula:"+ topTen[1][0]+"Cantidad:"+topTen[1][1]);
        System.out.println("Genero pelicula:"+ topTen[2][0]+"Cantidad:"+topTen[2][1]);
        System.out.println("Genero pelicula:"+ topTen[3][0]+"Cantidad:"+topTen[3][1]);
        System.out.println("Genero pelicula:"+ topTen[4][0]+"Cantidad:"+topTen[4][1]);
        System.out.println("Genero pelicula:"+ topTen[5][0]+"Cantidad:"+topTen[5][1]);
        System.out.println("Genero pelicula:"+ topTen[6][0]+"Cantidad:"+topTen[6][1]);
        System.out.println("Genero pelicula:"+ topTen[7][0]+"Cantidad:"+topTen[7][1]);
        System.out.println("Genero pelicula:"+ topTen[8][0]+"Cantidad:"+topTen[8][1]);
        System.out.println("Genero pelicula:"+ topTen[9][0]+"Cantidad:"+topTen[9][1]);

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);

    }*/

}
