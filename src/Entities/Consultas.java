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
                try {
                    CastMembersHeapMax.insert(castMember.getTableHash()[i].getValue());
                }catch(NullPointerException n){
                    System.out.println(castMember.getTableHash()[i].getKey());
                }
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


        System.out.println("Nombre actor/actriz:"+ topFive[0].getName()+" Cantidad de apariciones:"+topFive[0].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[1].getName()+" Cantidad de apariciones:"+topFive[1].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[2].getName()+" Cantidad de apariciones:"+topFive[2].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[3].getName()+" Cantidad de apariciones:"+topFive[3].getApariciones());
        System.out.println("Nombre actor/actriz:"+ topFive[4].getName()+" Cantidad de apariciones:"+topFive[4].getApariciones());
        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo+"ms");
    }

    public void consultaDos(HashCerrado<String,CastMember> castMember, ListaEnlazada<MovieCastMember> movieCastMember){
        long tiempoInicial=System.currentTimeMillis();

        MovieCastMember movie= movieCastMember.first.getValue();
        while (movie!=null){
            if (movie.getCatogory()=="actor"|| movie.getCatogory()=="actress") {
                CastMember cast = (CastMember) castMember.get(movie.getImdbName()).getValue();
                if(cast.isDead() && (cast.getBirthPlace().contains("USA")||cast.getBirthPlace().contains("Uk")||cast.getBirthPlace().contains("Italy")||cast.getBirthPlace().contains("France")) && !cast.isRecorrido() )
                {
                    // Causas no lista
                }
            }
        }





        String[][] topFive=new String[5][2];

        System.out.println("Causa de muerte:"+ topFive[0][0]+"Cantidad de personas:"+topFive[0][1]);
        System.out.println("Causa de muerte:"+ topFive[1][0]+"Cantidad de personas:"+topFive[1][1]);
        System.out.println("Causa de muerte:"+ topFive[2][0]+"Cantidad de personas:"+topFive[2][1]);
        System.out.println("Causa de muerte:"+ topFive[3][0]+"Cantidad de personas:"+topFive[3][1]);
        System.out.println("Causa de muerte:"+ topFive[4][0]+"Cantidad de personas:"+topFive[4][1]);
        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }



     /*public void consultaTres(MyHeapImpl<MovieRating> movieRating,HashCerrado<Integer, Movie> movie,HashCerrado<Integer,ListaEnlazada<MovieCastMember>> movieCastMember,HashCerrado<Integer, CastMember> castMember) throws EmptyHeapException {
            long tiempoInicial=System.currentTimeMillis();
            int[][] top=new int[14][3];
            int i=0;
            while (movieRating.get()!=null || top[14][0]==null){
                Integer key=Integer.parseInt(movieRating.delete().getImbdTitled().substring(2,9));
                Movie movietemp=movie.get(key).getValue();
                if (movietemp.getYear()==1960||movietemp.getYear()==1950){

                    top[i][0]=movietemp.getImdbTitled();

                    i++;
                }
            }

            for (int i=0;i< top.length;i++){
                int movieTitlie=top[i][0];
                ListaEnlazada<MovieCastMember> castmembersTemp = movieCastMember.get(movieTitlie).getValue();
                Nodo<MovieCastMember> moviecastMemberNode=castmembersTemp.first;
                MovieCastMember movieCastMemberTemp =  moviecastMemberNode.getValue();
                while (movieCastMemberTemp!=null){
                    CastMember castTemp= castMember.get(Integer.parseInt(movieCastMemberTemp.getImdbName().substring(2,9))).getValue();
                    if(castTemp.getHeight()!=null){
                        top[i][1]+=castTemp.getHeight();
                        top[i][2]+=1;
                    }
                    moviecastMemberNode=moviecastMemberNode.getNextValue();
                    movieCastMemberTemp=moviecastMemberNode.getValue();
                }

                for (int i=0;i< top.length;i++){
                    if (top[i][1]!=null){
                        top[i][1]=(top[i][1])/(top[i][2]);

                    }
                }





            }


            for (int i=0;i<top.length;i++){
                if(top[i][2]!=null){
                    System.out.println("Id película:"+top[i][0]+"Nombre:"+top[i][1]+"Altura promedio de actores:"+top[i][2]);
                }
            }
            long tiempoFinal=System.currentTimeMillis();
            long tiempo=tiempoFinal-tiempoInicial;
            System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);

        }





            }


            for (int i=0;i<top.length;i++){
                if(top[i][2]!=null){
                    System.out.println("Id película:"+top[i][0]+"Nombre:"+top[i][1]+"Altura promedio de actores:"+top[i][2]);
                }
            }
            long tiempoFinal=System.currentTimeMillis();
            long tiempo=tiempoFinal-tiempoInicial;
            System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);

        }

    public void consultaCuatro(){
        long tiempoInicial=System.currentTimeMillis();
        int[] actores=new int[2];
        int[] actrices=new int[2];

        System.out.println("Actores:");
        System.out.println("Año:"+actores[0]);
        System.out.println("Cantidad"+actores[1]);

        System.out.println("Actrices:");
        System.out.println("Año:"+actrices[0]);
        System.out.println("Cantidad"+actrices[1]);

        long tiempoFinal=System.currentTimeMillis();
        long tiempo=tiempoFinal-tiempoInicial;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);

    }

    public void consultaCinco(){
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
