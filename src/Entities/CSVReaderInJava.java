package Entities;

import TADS.HashCerrado;
import TADS.HashTable;
import TADS.MyHeap;
import TADS.MyHeapImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReaderInJava {

    public static void main(String[] args) {

        HashTable<Integer, CastMember> CastMembersHash = readCastMembersFromCSV("IMDb names.txt");
        HashTable<Integer, Movie> MoviesHash = new HashCerrado<>(114479);
        MyHeap<MovieRating> MovieRatingsHeapMax = new MyHeapImpl<>(85856, 1);

    }

    private static HashTable<Integer, CastMember> readCastMembersFromCSV(String fileName){
        HashTable<Integer, CastMember> CastMembersHash = new HashCerrado<>(396953);
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                CastMember CastMember = createCastMember(attributes);

                CastMembersHash.put(Integer.parseInt(CastMember.getImdbNameId().substring(2,8)), CastMember);

                line = br.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
        private static CastMember createCastMember(String[] datos){
            CauseOfDeath[] causas2 = new CauseOfDeath[0];
            Date birthDate= new Date(datos[6]);
            Date deathDate=new Date(datos[9]);
            if (datos[11] != null){
                String[] causas = datos[11].split("and");
                for (int i=0;i<causas.length;i++){
                    CauseOfDeath causeofdeath = new CauseOfDeath(causas[i]);
                    causas2[i] = causeofdeath;
                }
            }
            CastMember cast= new CastMember(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), datos[4], birthDate, datos[6], datos[7], datos[8], deathDate, datos[10], datos[12], datos[13], datos[14], Integer.parseInt(datos[15]), Integer.parseInt(datos[16]), Integer.parseInt(datos[17]), Integer.parseInt(datos[18]), causas2);

        }

    }
