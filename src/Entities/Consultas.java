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
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size()) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor") || category.equals("actress")) {
                        Integer a = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
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
                System.out.println("Id película:" + top[i].getImdbTitled() + "   Nombre:" + top[i].getTitle() + "    Altura promedio de actores:" + top[i].getAlturaPromedio());
            }
        }

        long tiempoFinal = System.currentTimeMillis();
        long tiempo = tiempoFinal - tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:" + tiempo);
    }

    public void consultaCuatro(HashTable<Integer,CastMember> castMember, HashTable<Integer,Lista<MovieCastMember>> movieCastMember) throws EmptyHeapException {
        long tiempoInicial=System.currentTimeMillis();
        Lista<AnoNacimientoPorCategory> anosNacimientoActors = new ListaEnlazada<>();
        Lista<AnoNacimientoPorCategory> anosNacimientoActress = new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash();i++){
            int j = 1;
            if(movieCastMember.getTableHash()[i]!=null) {
                while (j < movieCastMember.getTableHash()[i].getValue().size()) {
                    String category = movieCastMember.getTableHash()[i].getValue().get(j).getCatogory();
                    if (category.equals("actor")) {
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        if(!node.getValue().isCounted()) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            if (node.getValue().getBirthYear()!=0) {
                                if(anosNacimientoActors.size() == 0){
                                    AnoNacimientoPorCategory temp = new AnoNacimientoPorCategory(node.getValue().getBirthYear(),"actor");
                                    anosNacimientoActors.add(temp);
                                }else {
                                    for (int k = 1; k < anosNacimientoActors.size() + 1; k++) {
                                        if (node.getValue().getBirthYear() == anosNacimientoActors.get(k).getAnoNacimiento()) {
                                            anosNacimientoActors.get(k).setCantidadPersonas();
                                            entro = true;
                                        }
                                    }
                                    if (!entro) {
                                        AnoNacimientoPorCategory temp = new AnoNacimientoPorCategory(node.getValue().getBirthYear(),"actor");
                                        anosNacimientoActors.add(temp);
                                    }
                                }
                            }
                        }
                    }else if(category.equals("actress")){
                        Integer key = Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(j).getImdbName().substring(2, 9));
                        HashNode<Integer, CastMember> node = castMember.get(key);
                        if(!node.getValue().isCounted()) {
                            node.getValue().setCounted();
                            boolean entro = false;
                            if (node.getValue().getBirthYear()!=0) {
                                if(anosNacimientoActress.size() == 0){
                                    AnoNacimientoPorCategory temp = new AnoNacimientoPorCategory(node.getValue().getBirthYear(),"actress");
                                    anosNacimientoActress.add(temp);
                                }else {
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
                        }
                    }
                    j++;
                }
            }
        }
        MyHeap<AnoNacimientoPorCategory> anosActorsHeapMax = new MyHeapImpl<>(2027, 1);
        MyHeap<AnoNacimientoPorCategory> anosActressHeapMax = new MyHeapImpl<>(2027, 1);
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

    public void consultaCinco(HashTable<Integer, Movie> movie, HashTable<Integer, Lista<MovieCastMember>> movieCastMember, HashTable<Integer, CastMember> castMember){
        long tiempoInicial=System.currentTimeMillis();
        Lista<Género> listaGenero= new ListaEnlazada<>();
        for (int i=0; i<movieCastMember.getSizeHash(); i++){
            boolean tieneMasDeDosHijos=false;
            if(movieCastMember.getTableHash()[i]!=null) {
                int l = 1;
                while (!tieneMasDeDosHijos && l <= movieCastMember.getTableHash()[i].getValue().size()) {
                    if ((movieCastMember.getTableHash()[i].getValue().get(l).getCatogory().equals("actor") || movieCastMember.getTableHash()[i].getValue().get(l).getCatogory().equals("actress")) && castMember.get(Integer.parseInt((movieCastMember.getTableHash()[i].getValue().get(l).getImdbName().substring(2, 9)))).getValue().getChildren() >= 2 && !movie.get(Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(l).getImdbTitled().substring(2, 9))).getValue().isRecorrido()) {
                        tieneMasDeDosHijos = true;
                        movie.get(Integer.parseInt(movieCastMember.getTableHash()[i].getValue().get(l).getImdbTitled().substring(2, 9))).getValue().setRecorrido();
                        Lista<String> generosTemp = movie.get(Integer.parseInt((movieCastMember.getTableHash()[i].getValue().get(l).getImdbTitled().substring(2, 9)))).getValue().getGenre();
                        for (int j = 1; j < generosTemp.size() + 1; j++) {
                            boolean coinidencia = false;
                            while (!coinidencia) {
                                for (int k = 1; k < listaGenero.size() + 1; k++) {
                                    if (listaGenero.get(k).getGenreName().equals(generosTemp.get(j))) {
                                        listaGenero.get(k).setCounterGenero();
                                        coinidencia = true;
                                    }
                                }
                                if (!coinidencia) {
                                    Género newGenero = new Género(generosTemp.get(j));
                                    newGenero.setCounterGenero();
                                    listaGenero.add(newGenero);
                                    coinidencia = true;
                                }
                            }

                        }
                    }else{
                        l++;
                    }
                }
            }
        }

        /*long tiempoInicial=System.currentTimeMillis();
        Lista<Género> listaGenero= new ListaEnlazada<>();
        HashTable<Integer,Lista<String>> PelisConsideradas = new HashCerrado<>(72353);
        //int i = 0;
        //while(i<movieCastMember.getSizeHash())
        for (int i=0; i<movieCastMember.getSizeHash(); i++){
            if(movieCastMember.getTableHash()[i]!=null) {
                Lista<MovieCastMember> listaCastMembersTemp= movieCastMember.getTableHash()[i].getValue();
                int j = 1;
                boolean considerada = false;
                while (j<listaCastMembersTemp.size()+1 && !considerada) {
                    if ((listaCastMembersTemp.get(j).getCatogory().equals("actor") || listaCastMembersTemp.get(j).getCatogory().equals("actress")) && castMember.get(Integer.parseInt((listaCastMembersTemp.get(j).getImdbName().substring(2, 9)))).getValue().getChildren() >= 2) {
                        considerada = true;
                        Lista<String> generosTemp = movie.get(Integer.parseInt((listaCastMembersTemp.get(j).getImdbTitled().substring(2, 9)))).getValue().getGenre();
                        PelisConsideradas.put(Integer.parseInt((listaCastMembersTemp.get(j).getImdbTitled().substring(2, 9))), generosTemp);
                    }j++;
                }
            }
        }
        for (int i=0; i<PelisConsideradas.getSizeHash(); i++){
            if(PelisConsideradas.getTableHash()[i]!=null) {
                Lista<String> generosTemp = PelisConsideradas.getTableHash()[i].getValue();
                for (int k = 1; k < generosTemp.size() + 1; k++) {
                    boolean coinidencia = false;
                    while (!coinidencia) {
                        for (int l = 1; l < listaGenero.size() + 1; l++) {
                            if (listaGenero.get(l).getGenreName().equals(generosTemp.get(k))) {
                                listaGenero.get(l).setCounterGenero();
                                coinidencia = true;
                            }
                        }
                        if (!coinidencia) {
                            Género newGenero = new Género(generosTemp.get(k));
                            newGenero.setCounterGenero();
                            listaGenero.add(newGenero);
                            coinidencia = true;
                        }
                    }
                }
            }
        }*/


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


        System.out.println("Genero pelicula:"+ topTen[0].getGenreName()+"   Cantidad:"+topTen[0].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[1].getGenreName()+"   Cantidad:"+topTen[1].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[2].getGenreName()+"   Cantidad:"+topTen[2].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[3].getGenreName()+"   Cantidad:"+topTen[3].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[4].getGenreName()+"   Cantidad:"+topTen[4].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[5].getGenreName()+"   Cantidad:"+topTen[5].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[6].getGenreName()+"   Cantidad:"+topTen[6].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[7].getGenreName()+"   Cantidad:"+topTen[7].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[8].getGenreName()+"   Cantidad:"+topTen[8].getCounterGenero());
        System.out.println("Genero pelicula:"+ topTen[9].getGenreName()+"   Cantidad:"+topTen[9].getCounterGenero());
        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }

}
