package Entities;

import TADS.*;

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

        HashTable<Integer, CastMember> CastMembersHash = readCastMembersFromCSV("IMDb names.txt");
        HashTable<Integer, Movie> MoviesHash = readMoviesFromCSV("IMDb movies.txt");
        MyHeap<MovieRating> MovieRatingsHeapMax = readMovieRatingsFromCSV("IMDb ratings.txt");

    }

    private static HashTable<Integer, CastMember> readCastMembersFromCSV(String fileName){
        HashTable<Integer, CastMember> CastMembersHash = new HashCerrado<>(396953);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();            // leo la primera linea


            while (line != null) {
                String[] attributes = line.split(",");
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
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();            // leo la primera linea


            while (line != null) {
                String[] attributes = line.split(",");
                Movie movie = createMovie(attributes);
                MoviesHash.put(Integer.parseInt(movie.getImdbTitled().substring(2,8)), movie);
                line = br.readLine(); //Leo la próxima linea (si llego al final me da null)
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return MoviesHash;
    }

    private static MyHeap<MovieRating> readMovieRatingsFromCSV(String fileName){
        MyHeap<MovieRating> MovieRatingsHeapMax = new MyHeapImpl<>(85856, 1);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();            // leo la primera linea


            while (line != null) {
                String[] attributes = line.split(",");
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
        CauseOfDeath[] causas2 = new CauseOfDeath[0];
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
                causas2[i] = causeofdeath;
            }
        }
        if(datos[7]!=null){
            lugarNacimiento = datos[7].split(",");
        }
        if(datos[10]!=null){
            lugarFallecimiento = datos[10].split(",");
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

    private static MovieRating createMovieRating(String[] datos){
        Lista<Integer> votesRating = new ListaEnlazada();
        for (int i = 5; i<15; i++){
            if(datos[i]!=null){
                votesRating.add(Integer.parseInt(datos[i]));
            }
        }
        imdb_title_id,weighted_average_vote,total_votes,mean_vote,median_vote,votes_10,votes_9,votes_8,votes_7,votes_6,votes_5,votes_4,votes_3,votes_2,votes_1,allgenders_0age_avg_vote,allgenders_0age_votes,allgenders_18age_avg_vote,allgenders_18age_votes,allgenders_30age_avg_vote,allgenders_30age_votes,allgenders_45age_avg_vote,allgenders_45age_votes,males_allages_avg_vote,males_allages_votes,males_0age_avg_vote,males_0age_votes,males_18age_avg_vote,males_18age_votes,males_30age_avg_vote,males_30age_votes,males_45age_avg_vote,males_45age_votes,females_allages_avg_vote,females_allages_votes,females_0age_avg_vote,females_0age_votes,females_18age_avg_vote,females_18age_votes,females_30age_avg_vote,females_30age_votes,females_45age_avg_vote,females_45age_votes,top1000_voters_rating,top1000_voters_votes,us_voters_rating,us_voters_votes,non_us_voters_rating,non_us_voters_votes
        MovieRating rating = new MovieRating(datos[0], Float.parseFloat(datos[1]), Integer.parseInt(datos[2]), Float.parseFloat(datos[3]), Float.parseFloat(datos[4]), votesRating, List<Rating>[] allGenders, List<Rating>[] males, List<Rating>[] females, List<Rating>[] top1000, List<Rating>[] us, List<Rating>[] nonUs);
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
