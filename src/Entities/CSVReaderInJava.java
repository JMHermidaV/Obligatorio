package Entities;

import TADS.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.Iterator;

public class CSVReaderInJava {

    private HashTable<Integer, CastMember> CastMembersHash;
    private HashTable<Integer, Movie> MoviesHash;
    private HashTable<Integer, Lista<MovieCastMember>> MovieCastMemberHash;
    private MyHeap<MovieRating> MovieRatingsHeapMax;

    public CSVReaderInJava() {
        System.out.println("Los datos se están cargando...");
        long tiempoInicial=System.currentTimeMillis();
        CastMembersHash = readCastMembersFromCSV("IMDb names.csv");
        MoviesHash = readMoviesFromCSV("IMDb movies.csv");
        MovieCastMemberHash = readMovieCastMembersFromCSV("IMDb title_principals.csv");
        MovieRatingsHeapMax = readMovieRatingsFromCSV("IMDb ratings.csv");
        long tiempoFinal=System.currentTimeMillis();
        long tiempoTotal=tiempoFinal-tiempoInicial;

        System.out.println("Carga de datos exitosa, tiempo de ejecución de la carga: "+ tiempoTotal +"ms");
    }

    private static HashTable<Integer, CastMember> readCastMembersFromCSV(String fileName){
        HashTable<Integer, CastMember> CastMembersHash = new HashCerrado<>(396953);

        File file = new File(fileName);
        CsvReader csv = new CsvReader(file);
        Iterator<String[]> csvIterator = csv.iterator();
        String[] datos = csvIterator.next();
        if(csvIterator.hasNext()){datos = csvIterator.next();}
        while(csvIterator.hasNext()){
            datos = csvIterator.next();
            CastMember castMember = createCastMember(datos);
            CastMembersHash.put(castMember.hashCode(), castMember);
        }
        return CastMembersHash;
    }
    private static HashTable<Integer, Movie> readMoviesFromCSV(String fileName){
        HashTable<Integer, Movie> MoviesHash = new HashCerrado<>(114479);

        File file = new File("IMDb movies.csv");
        CsvReader csv = new CsvReader(file);
        Iterator<String[]> csvIterator = csv.iterator();
        String[] datos = csvIterator.next();
        if(csvIterator.hasNext()){datos = csvIterator.next();}
        while(csvIterator.hasNext()){
            datos = csvIterator.next();
            Movie movie = createMovie(datos);
            MoviesHash.put(movie.hashCode(),movie);
        }
        return MoviesHash;
    }

    private static HashTable<Integer, Lista<MovieCastMember>> readMovieCastMembersFromCSV(String fileName){
        HashTable<Integer, Lista<MovieCastMember>> movieCastMemberHash = new HashCerrado<>(114479);

        File file = new File(fileName);
        CsvReader csv = new CsvReader(file);
        Iterator<String[]> csvIterator = csv.iterator();
        String[] datos = csvIterator.next();
        if(csvIterator.hasNext()){datos = csvIterator.next();}
        while(csvIterator.hasNext()){
            datos = csvIterator.next();
            MovieCastMember movieCastMember = createMovieCastMember(datos);
            if (movieCastMemberHash.get(movieCastMember.hashCode())==null){
                Lista<MovieCastMember> listaMovieCastMembers = new ListaEnlazada<>();
                listaMovieCastMembers.add(movieCastMember);
                movieCastMemberHash.put(movieCastMember.hashCode(),listaMovieCastMembers);
            }else{
                movieCastMemberHash.get(movieCastMember.hashCode()).getValue().add(movieCastMember);
            }
        }
        return movieCastMemberHash;
    }

    private static MyHeap<MovieRating> readMovieRatingsFromCSV(String fileName){
        MyHeap<MovieRating> MovieRatingsHeapMax = new MyHeapImpl<>(85856, 1);

        File file = new File(fileName);
        CsvReader csv = new CsvReader(file);
        Iterator<String[]> csvIterator = csv.iterator();
        String[] datos = csvIterator.next();
        if(csvIterator.hasNext()){datos = csvIterator.next();}
        while(csvIterator.hasNext()){
            datos = csvIterator.next();
            MovieRating movieRating = createMovieRating(datos);
            MovieRatingsHeapMax.insert(movieRating);
        }
        return MovieRatingsHeapMax;
    }

    private static CastMember createCastMember(String[] datos){
        int birthYear = 0;
        int deathYear = 0;
        int altura = 0;
        if (!datos[6].isEmpty()){
            try{
                birthYear = Integer.parseInt(datos[6].substring(0,4));
            }catch (NumberFormatException e){
                birthYear = 0;
            }catch (StringIndexOutOfBoundsException e){
                deathYear = Integer.parseInt(datos[9].substring(0,2));
            }
        }if (!datos[9].isEmpty()){
            try{
                deathYear = Integer.parseInt(datos[9].substring(0,4));
            }catch (NumberFormatException e){
                deathYear = 0;
            }catch (StringIndexOutOfBoundsException e){
                deathYear = Integer.parseInt(datos[9].substring(0,3));
            }
        }
        CauseOfDeath causeofdeath = null;
        if (datos[11] != null){
            causeofdeath = new CauseOfDeath(datos[11]);
        }if (!datos[3].isEmpty()){
            altura = Integer.parseInt(datos[3]);
        }

        CastMember cast= new CastMember(datos[0].substring(datos[0].lastIndexOf("n")), datos[1], datos[2], altura, datos[4], birthYear, datos[7], deathYear, datos[10], datos[12], Integer.parseInt(datos[13]), Integer.parseInt(datos[14]), Integer.parseInt(datos[15]), Integer.parseInt(datos[16]), causeofdeath);
        return cast;
    }

    private static Movie createMovie(String[] datos){
        int year = 0;
        Date datePublished = null; //pos 4
        int duration = 0;
        Lista<Género> genre = new ListaEnlazada(); //pos 5
        Lista<String> country = createList(datos[7],",");; //pos 7
        Lista<String> director = null; // pos 9
        Lista<String> writer = null; // pos 10
        Lista<String> actors = null; // pos 12
        float  avgVotes = 0;
        int votes = 0;
        Float  metaStore = null;
        float  reviewFromUsers = 0;
        float  reviewFromCritics = 0;

        if (!datos[3].isEmpty()){
            try{ year = Integer.parseInt(datos[3]);
            }catch(NumberFormatException e){
                year = 2019;                //Se sabe que hay un único valor no numérico y es "TV Movie 2019" (hicimos pruebas)
            }
        }
        if (!datos[4].isEmpty()){
            datePublished = createDate(datos[4]);
        }
        if(!datos[6].isEmpty()){
            duration = Integer.parseInt(datos[6]);
        }
        if (!datos[5].isEmpty()){
            String[] generos = datos[5].split("and");
            for (int i=0;i<generos.length;i++){
                Género genero = new Género(generos[i]);
                genre.add(genero);
            }
        }/*if (!datos[7].isEmpty()){
            country = createList(datos[7],",");
        }*/if (!datos[9].isEmpty()){
            director = createList(datos[9],",");
        }if (!datos[10].isEmpty()){
            writer = createList(datos[10],",");
        }if (!datos[12].isEmpty()){
            actors = createList(datos[12],",");
        }if (!datos[14].isEmpty()){
            avgVotes = Float.parseFloat(datos[14]);
        }if (!datos[15].isEmpty()){
            votes = Integer.parseInt(datos[15]);
        }if (!datos[19].isEmpty()){
            metaStore = Float.parseFloat(datos[19]);
        }if (!datos[20].isEmpty()){
            reviewFromUsers = Float.parseFloat(datos[20]);
        }if (!datos[21].isEmpty()){
            reviewFromCritics = Float.parseFloat(datos[21]);
        }

        Movie movie = new Movie(datos[0].substring(datos[0].lastIndexOf("t")-1), datos[1], datos[2], year, datePublished, genre, duration, country, datos[8], director, writer, datos[11], actors, datos[13], avgVotes, votes, datos[16], datos[17], datos[18], metaStore, reviewFromUsers, reviewFromCritics);
        return movie;
    }

    private static MovieCastMember createMovieCastMember(String[] datos){
        Lista<String> characters = null;
        if (!datos[5].isEmpty()){
            characters = createList(datos[5], ","); //REVISAR SEPARADOR
        }

        MovieCastMember mvcm = new MovieCastMember(datos[0].substring(datos[0].lastIndexOf("t")-1), Integer.parseInt(datos[1]),datos[2],datos[3],datos[4],characters);
        return mvcm;
    }

    private static MovieRating createMovieRating(String[] datos){
        Lista<Integer> votesRating = new ListaEnlazada();
        Lista<Rating> allGenders = new ListaEnlazada();
        Lista<Rating> males = new ListaEnlazada();
        Lista<Rating> females = new ListaEnlazada();
        Rating top1000 = createRating(datos[43],datos[44]);
        Rating us = createRating(datos[45],datos[46]);
        Rating nonUs = createRating(datos[47],datos[48]);
        for (int i = 5; i<15; i++){
            if(!datos[i].isEmpty()){
                votesRating.add(Integer.parseInt(datos[i]));
            }
        }
        for (int i = 15; i<23; i=i+2){
            if(!datos[i].isEmpty() && !datos[i+1].isEmpty()){
                Rating allGendersRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                allGenders.add(allGendersRating);
            }
        }
        for (int i = 23; i<33; i=i+2){
            if(!datos[i].isEmpty() && !datos[i+1].isEmpty()){
                Rating maleRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                males.add(maleRating);
            }
        }
        for (int i = 33; i<43; i=i+2){
            if(!datos[i].isEmpty() && !datos[i+1].isEmpty()){
                Rating femaleRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                females.add(femaleRating);
            }
        }

        MovieRating movierating = new MovieRating(datos[0], Float.parseFloat(datos[1]), Integer.parseInt(datos[2]), Float.parseFloat(datos[3]), Float.parseFloat(datos[4]), votesRating, allGenders,  males, females, top1000, us, nonUs);
        return movierating;
    }

    private static Rating createRating(String avg, String votes){
        Rating rating = null;
        if (!avg.isEmpty() && !votes.isEmpty()) {
            rating = new Rating(Float.parseFloat(votes), Float.parseFloat(avg));
        }
        return rating;
    }
    private static Lista<String> createList(String dato, String separador) {
        Lista<String> MiLista = new ListaEnlazada();

        String[] lista = dato.split(separador);
        for (int i = 0; i<lista.length; i++){
            MiLista.add(lista[i]);
        }

        return MiLista;
    }

    private static Date createDate(String dato){
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
        if (dato.indexOf("-") == -1){
            try {
                date = format2.parse(dato);
            } catch (ParseException e) {
                date = new Date(0,0,0);
            }
        }else{
            try {
                date = format.parse(dato);
            } catch (ParseException e) {
                date = new Date(0,0,0);
            }
        }
        return date;
    }

    public HashTable<Integer, CastMember> getCastMembersHash() {
        return CastMembersHash;
    }

    public HashTable<Integer, Movie> getMoviesHash() {
        return MoviesHash;
    }

    public HashTable<Integer, Lista<MovieCastMember>> getMovieCastMemberHash() {
        return MovieCastMemberHash;
    }

    public MyHeap<MovieRating> getMovieRatingsHeapMax() {
        return MovieRatingsHeapMax;
    }
}
