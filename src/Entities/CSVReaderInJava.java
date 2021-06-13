package Entities;

import TADS.*;

import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReaderInJava {

    public static void main(String[] args) {

        HashTable<Integer, CastMember> CastMembersHash = readCastMembersFromCSV("IMDb names.csv");
        HashTable<Integer, Movie> MoviesHash = readMoviesFromCSV("IMDb movies.csv");
        Lista<MovieCastMember>  MovieCastMemberList = readMovieCastMembersFromCSV("IMDb title_principals");
        MyHeap<MovieRating> MovieRatingsHeapMax = readMovieRatingsFromCSV("IMDb ratings.txt");

    }

    private static HashTable<Integer, CastMember> readCastMembersFromCSV(String fileName){
        HashTable<Integer, CastMember> CastMembersHash = new HashCerrado<>(396953);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();            // leo la primera linea
            line = br.readLine();            // leo la prox pq la primera son títulos

            while (line != null) {
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                CastMember CastMember = createCastMember(attributes);
                CastMembersHash.put(Integer.parseInt(CastMember.getImdbNameId().substring(2,8)), CastMember);
                line = br.readLine(); //Leo la próxima linea (si llego al final me da null)
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return CastMembersHash;
    }
    private static HashTable<Integer, Movie> readMoviesFromCSV(String fileName){
        HashTable<Integer, Movie> MoviesHash = new HashCerrado<>(114479);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();            // leo la primera linea
            line = br.readLine();            // leo la prox pq la primera son títulos

            while (line != null) {
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Movie movie = createMovie(attributes);
                MoviesHash.put(Integer.parseInt(movie.getImdbTitled().substring(2,8)), movie);
                line = br.readLine(); //Leo la próxima linea (si llego al final me da null)
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return MoviesHash;
    }

    private static Lista<MovieCastMember> readMovieCastMembersFromCSV(String fileName){
        Lista<MovieCastMember> movieCastMemberList = new ListaEnlazada<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();            // leo la primera linea
            line = br.readLine();            // leo la prox pq la primera son títulos

            while (line != null) {
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                MovieCastMember movieCastMember = createMovieCastMember(attributes);
                movieCastMemberList.add(movieCastMember);
                line = br.readLine(); //Leo la próxima linea (si llego al final me da null)
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return movieCastMemberList;
    }

    private static MyHeap<MovieRating> readMovieRatingsFromCSV(String fileName){
        MyHeap<MovieRating> MovieRatingsHeapMax = new MyHeapImpl<>(85856, 1);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();            // leo la primera linea
            line = br.readLine();            // leo la prox pq la primera son títulos

            while (line != null) {
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                MovieRating movieRating = createMovieRating(attributes);
                MovieRatingsHeapMax.insert(movieRating);
                line = br.readLine(); //Leo la próxima linea (si llego al final me da null)
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return MovieRatingsHeapMax;
    }

    private static CastMember createCastMember(String[] datos){
        Lista<CauseOfDeath> causas2 = new ListaEnlazada();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
        Date birthDate = null;
        Date deathDate = null;
        String[] lugarNacimiento = new String[3];
        String[] lugarFallecimiento = new String[3];
        if (datos[6]!=null){
            birthDate = createDate(datos[6]);
        }
        if (datos[9]!=null){
            deathDate = createDate(datos[9]);
        }
        if (datos[11] != null){
            String[] causas = datos[11].split("and");
            for (int i=0;i<causas.length;i++){
                CauseOfDeath causeofdeath = new CauseOfDeath(causas[i]);
                causas2.add(causeofdeath);
            }
        }
        if(datos[7]!=null){
            lugarNacimiento = datos[7].split(",");
        }
        if(datos[10]!=null){
            lugarFallecimiento = datos[10].split(",");
        }
        System.out.println(datos.length);
        System.out.println(lugarNacimiento.length);
        for(int i = 0; i<datos.length;i++){
            System.out.println(datos[i]);
        }



        CastMember cast= new CastMember(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), datos[4], birthDate, lugarNacimiento[0], lugarNacimiento[2], lugarNacimiento[1], deathDate, lugarFallecimiento[0], lugarFallecimiento[2], lugarFallecimiento[1], datos[12], Integer.parseInt(datos[13]), Integer.parseInt(datos[14]), Integer.parseInt(datos[15]), Integer.parseInt(datos[16]), causas2);
        return cast;
    }

    private static Movie createMovie(String[] datos){
        Date datePublished = null; //pos 4
        Lista<Género> genre = new ListaEnlazada(); //pos 5
        Lista<String> country = null; //pos 7
        Lista<String> director = null; // pos 9
        Lista<String> writer = null; // pos 10
        Lista<String> actors = null; // pos 12
        if (datos[4]!=null){
            datePublished = createDate(datos[4]);
        }
        if (datos[5]!=null){
            String[] generos = datos[11].split("and");
            for (int i=0;i<generos.length;i++){
                Género genero = new Género(generos[i]);
                genre.add(genero);
            }
        }
        if (datos[7]!=null){
            country = createList(datos[7],",");
        }
        if (datos[9]!=null){
            director = createList(datos[9],",");
        }
        if (datos[10]!=null){
            writer = createList(datos[10],",");
        }
        if (datos[12]!=null){
            actors = createList(datos[12],",");
        }

        Movie movie = new Movie(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), datePublished, genre, Integer.parseInt(datos[6]), country, datos[8], director, writer, datos[11], actors, datos[13], Float.parseFloat(datos[14]), Integer.parseInt(datos[15]), datos[16], datos[17], datos[18], Float.parseFloat(datos[19]), Float.parseFloat(datos[20]), Float.parseFloat(datos[21]));
        return movie;
    }

    private static MovieCastMember createMovieCastMember(String[] datos){
        Lista<String> characters = null;
        if (datos[5]!=null){
            characters = createList(datos[5], ","); //REVISAR SEPARADOR
        }

        //imdb_title_id,ordering,imdb_name_id,category,job,characters
        MovieCastMember mvcm = new MovieCastMember(datos[0], Integer.parseInt(datos[1]),datos[2],datos[3],datos[4],characters);
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
            if(datos[i]!=null){
                votesRating.add(Integer.parseInt(datos[i]));
            }
        }
        for (int i = 15; i<23; i=i+2){
            if(datos[i]!=null && datos[i+1]!=null){
                Rating allGendersRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                allGenders.add(allGendersRating);
            }
        }
        for (int i = 23; i<33; i=i+2){
            if(datos[i]!=null && datos[i+1]!=null){
                Rating maleRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                males.add(maleRating);
            }
        }
        for (int i = 33; i<43; i=i+2){
            if(datos[i]!=null && datos[i+1]!=null){
                Rating femaleRating = new Rating(Float.parseFloat(datos[i+1]),Float.parseFloat(datos[i]));
                females.add(femaleRating);
            }
        }

        MovieRating movierating = new MovieRating(datos[0], Float.parseFloat(datos[1]), Integer.parseInt(datos[2]), Float.parseFloat(datos[3]), Float.parseFloat(datos[4]), votesRating, allGenders,  males, females, top1000, us, nonUs);
        return movierating;
    }

    private static Rating createRating(String avg, String votes){
        Rating rating = null;
        if (avg!=null && votes!=null) {
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

    }
