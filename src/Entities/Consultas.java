package Entities;

import TADS.*;

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
        return myObj.nextInt();
    }

    public void consultaUno(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember){
        long tiempoInicial=System.currentTimeMillis();
        for (int i=0; i<castMember.getSizeHash();i++){
            if(castMember.getTableHash()[i] != null) {
                castMember.getTableHash()[i].getValue().resetApariciones();
            }
        }
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size() + 1) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor") || category.equals("actress")) {
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9).replaceAll("^0+(?!$)", ""));
                        castMember.get(key).getValue().setApariciones();
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

        for (int i = 0; i<5;i++) {
            System.out.println("Nombre actor/actriz:" + topFive[i].getName());
            System.out.println("Cantidad de apariciones:" + topFive[i].getApariciones());
            System.out.println();
        }

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
        System.out.println();
    }

    public void consultaDos(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember){
        long tiempoInicial=System.currentTimeMillis();
        for (int i=0; i<castMember.getSizeHash();i++){
            if(castMember.getTableHash()[i] != null) {
                castMember.getTableHash()[i].getValue().resetRecorrido();
            }
        }
        Lista<CauseOfDeath> causas = new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size() + 1) {
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
        for (int i = 0; i<5;i++) {
            System.out.println("Causa de muerte:" + topFive[i].getName());
            System.out.println("Cantidad de personas:" + topFive[i].getNumeroDeMuertes());
            System.out.println();
        }

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
        System.out.println();
    }



    public void consultaTres(MyHeap<MovieRating> movieRating, HashTable<Integer, Movie> movie, HashTable<Integer, Lista<MovieCastMember>> movieCastMember, HashTable<Integer, CastMember> castMember) throws EmptyHeapException {
        long tiempoInicial = System.currentTimeMillis();
        for (int i=0; i<movie.getSizeHash();i++){
            if(movie.getTableHash()[i] != null) {
                movie.getTableHash()[i].getValue().resetAlturas();
            }
        }
        Movie[] top = new Movie[14];
        Lista<MovieRating> resetList = new ListaEnlazada<>();
        int i = 0;
        while (movieRating.get() != null && top[13] == null) {
            MovieRating mRating = movieRating.delete();
            resetList.add(mRating);
            Integer key = Integer.parseInt(mRating.getImbdTitled().substring(2, 9).replaceAll("^0+(?!$)", ""));
            Movie movietemp = movie.get(key).getValue();
            if (movietemp.getYear() <= 1960 && movietemp.getYear() >= 1950) {
                top[i] = movietemp;
                i++;
            }
        }
        for(int j=1; j<= resetList.size(); j++){
            movieRating.insert(resetList.get(j));
        }
        for (i = 0; i < top.length; i++) {
            int movieKey = Integer.parseInt(top[i].getImdbTitled().substring(2, 9).replaceAll("^0+(?!$)", ""));
            Lista<MovieCastMember> castmembersTemp = movieCastMember.get(movieKey).getValue();
            for (int k = 1; k < (castmembersTemp.size() + 1); k++) {
                MovieCastMember movieCastMemberTemp = castmembersTemp.get(k);
                CastMember castTemp = castMember.get(Integer.parseInt(movieCastMemberTemp.getImdbName().substring(2, 9).replaceAll("^0+(?!$)", ""))).getValue();
                if (castTemp.getHeight() != 0 && (movieCastMemberTemp.getCatogory().equals("actor") || movieCastMemberTemp.getCatogory().equals("actress"))) {
                    int altura = castTemp.getHeight();
                    top[i].setSumaAltura(altura);
                    top[i].setActoresConAltura();
                }
            }
        }
        for (i = 0; i < top.length; i++) {
            if (top[i].getActoresConAltura() != 0) {
                System.out.println("Id película:" + top[i].getImdbTitled());
                System.out.println("Nombre:" + top[i].getTitle());
                System.out.println("Altura promedio de actores:" + top[i].getAlturaPromedio());
                System.out.println();
            }
        }

        long tiempoFinal = System.currentTimeMillis();
        long tiempo = tiempoFinal - tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:" + tiempo);
        System.out.println();
    }

    public void consultaCuatro(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember) throws EmptyHeapException {
        long tiempoInicial=System.currentTimeMillis();
        for (int i=0; i<castMember.getSizeHash();i++){
            if(castMember.getTableHash()[i] != null) {
                castMember.getTableHash()[i].getValue().resetCounted();
            }
        }
        Lista<AnoNacimientoPorCategory> anosNacimientoActors = new ListaEnlazada<>();
        Lista<AnoNacimientoPorCategory> anosNacimientoActress = new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size() + 1) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor")) {
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9).replaceAll("^0+(?!$)", ""));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        if (!node.getValue().isCounted() && node.getValue().getBirthYear()!=0) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            for (int k = 1; k < anosNacimientoActors.size() + 1; k++) {
                                if (node.getValue().getBirthYear() == anosNacimientoActors.get(k).getAnoNacimiento()) {
                                    anosNacimientoActors.get(k).setCantidadPersonas();
                                    entro = true;
                                }
                            }
                            if (!entro) {
                                AnoNacimientoPorCategory temp = new AnoNacimientoPorCategory(node.getValue().getBirthYear(), "actor");
                                anosNacimientoActors.add(temp);
                            }
                        }
                    }else if(category.equals("actress")){
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9).replaceAll("^0+(?!$)", ""));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        if(!node.getValue().isCounted() && node.getValue().getBirthYear()!=0) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            for (int k = 1; k < anosNacimientoActress.size() + 1; k++) {
                                if (node.getValue().getBirthYear() == anosNacimientoActress.get(k).getAnoNacimiento()) {
                                    anosNacimientoActress.get(k).setCantidadPersonas();
                                    entro = true;
                                }
                            }
                            if (!entro) {
                                AnoNacimientoPorCategory temp = new AnoNacimientoPorCategory(node.getValue().getBirthYear(),"actress");
                                anosNacimientoActress.add(temp);
                            }
                        }
                    }
                    j++;
                }
            }
        }
        MyHeap<AnoNacimientoPorCategory> anosActorsHeapMax = new MyHeapImpl<>(anosNacimientoActors.size(), 1);
        MyHeap<AnoNacimientoPorCategory> anosActressHeapMax = new MyHeapImpl<>(anosNacimientoActors.size(), 1);
        for(int i=1; i<anosNacimientoActors.size()+1;i++){
            anosActorsHeapMax.insert(anosNacimientoActors.get(i));
        }
        for(int i=1; i<anosNacimientoActress.size()+1;i++){
            anosActressHeapMax.insert(anosNacimientoActress.get(i));
        }

        System.out.println("Actores:");
        System.out.println("Año:"+anosActorsHeapMax.delete().getAnoNacimiento());
        System.out.println("Cantidad:"+anosActorsHeapMax.delete().getCantidadPersonas());
        System.out.println();

        System.out.println("Actrices:");
        System.out.println("Año:"+anosActressHeapMax.delete().getAnoNacimiento());
        System.out.println("Cantidad:"+anosActressHeapMax.delete().getCantidadPersonas());

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println();
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
        System.out.println();

    }

    public void consultaCinco(HashTable<Integer, Movie> movie, HashTable<Integer, Lista<MovieCastMember>> movieCastMember, HashTable<Integer, CastMember> castMember){
        long tiempoInicial=System.currentTimeMillis();
        Lista<Género> listaGenero= new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash(); i++){
            boolean tieneMasDeDosHijos=false;
            if(movieCastMember.getTableHash()[i]!=null) {
                int l = 1;
                while (!tieneMasDeDosHijos && l <= movieCastMember.getTableHash()[i].getValue().size()) {
                    if ((movieCastMember.getTableHash()[i].getValue().get(l).getCatogory().equals("actor") || movieCastMember.getTableHash()[i].getValue().get(l).getCatogory().equals("actress")) && castMember.get(Integer.parseInt((movieCastMember.getTableHash()[i].getValue().get(l).getImdbName().substring(2, 9)))).getValue().getChildren() >= 2) {
                        tieneMasDeDosHijos = true;
                        Lista<String> generosTemp = movie.get(Integer.parseInt((movieCastMember.getTableHash()[i].getValue().get(l).getImdbTitled().substring(2, 9)))).getValue().getGenre();
                        for (int j = 1; j < generosTemp.size() + 1; j++) {
                            boolean coinidencia = false;
                            while (!coinidencia) {
                                int k = 1;
                                while(k< listaGenero.size() + 1 && !coinidencia){
                                    if (listaGenero.get(k).getGenreName().equals(generosTemp.get(j))) {
                                        listaGenero.get(k).setCounterGenero();
                                        coinidencia = true;
                                    }k++;
                                }
                                if (!coinidencia) {
                                    Género newGenero = new Género(generosTemp.get(j));
                                    listaGenero.add(newGenero);
                                    coinidencia = true;
                                }
                            }
                        }
                    }l++;
                }
            }
        }

        MyHeap<Género> GeneroHeapMax = new MyHeapImpl<>(listaGenero.size()+1, 1);
        for(int i=1; i<listaGenero.size()+1;i++){
            if(listaGenero.get(i)!=null) {
                GeneroHeapMax.insert(listaGenero.get(i));
            }
        }
        Género[] topTen= new Género[10];
        try {
            topTen[0]= GeneroHeapMax.delete();
            topTen[1]= GeneroHeapMax.delete();
            topTen[2]= GeneroHeapMax.delete();
            topTen[3]= GeneroHeapMax.delete();
            topTen[4]= GeneroHeapMax.delete();
            topTen[5]= GeneroHeapMax.delete();
            topTen[6]= GeneroHeapMax.delete();
            topTen[7]= GeneroHeapMax.delete();
            topTen[8]= GeneroHeapMax.delete();
            topTen[9]= GeneroHeapMax.delete();
        }catch (EmptyHeapException e) {
            e.printStackTrace();
        }
        for (int i = 0; i<10;i++) {
            System.out.println("Genero pelicula:"+ topTen[i].getGenreName());
            System.out.println("Cantidad:"+topTen[i].getCounterGenero());
            System.out.println();
        }

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
        System.out.println();
    }

}
