package com.company;
import java.time.DayOfWeek ;

import sun.util.locale.provider.BreakIteratorProviderImpl;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.sql.SQLOutput;
import java.time.format.TextStyle;
import java.util.*;

public class Main {

    public static void main(String args[]) {
        try {
            // list est une liste qui contient les input de fichier texte :


            List<String > list = ListeInput("file.txt") ;


            //stocker les input dans des variables :

            //MatiersN est un tableau des matiers (String)
            String[] MatiersN = list.get(0).split(",");
            //nbrM est le nombre des matiers
            int nbrM = MatiersN.length;
            //repetition est un tableau les repetition de chaque matier (string)
            String[] repetition = list.get(1).split(",");
            //nbrS est le nombre de seance possible par jour
            int nbrS = Integer.parseInt(list.get(2));
            // nbrJ est le nombre de jours a travailler par semaine
            int nbrJ = Integer.parseInt(list.get(3));




            //creer des objet de la class Matiers et affecter leur noms et repetition ,
            // et les stocker dans une list des Matiers (objet)

            List<Matiers> ListMatiers = new ArrayList<>();

            creerLesMatiers(MatiersN,repetition,nbrM,ListMatiers);




            //emploie est une liste qui vas contenir les matiers repeté selon leur repetition

            List<String> emploie = new ArrayList<>();

            // fonctionPrincipale stocke les matiers dans la liste emploie selon l'algorithme

            fonctionPrincipale(ListMatiers,emploie);

            //mercrediSoir fonction qui donne Mercredi une apres-midi libre

            mercrediSoir(emploie,nbrS);


            //L'affichage :

            affichage(emploie,nbrJ,nbrS);

        }
        catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<String> ListeInput(String path) throws IOException {
        // Le fichier d'entrée
        File file = new File("file.txt");
        // Créer l'objet File Reader
        FileReader fr = new FileReader(file);
        // creer une liste
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> repetitions = new ArrayList<>();
        // Créer l'objet BufferedReader
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        fr.close();

    return list ;}


    public static void affichage(List<String> emploie , int nbrJ , int nbrS){
        int size = emploie.size();
        int z=0 ;
        for (int w = 0; w < nbrJ; w++) {

            System.out.print(Utils.rightPad(String.valueOf(DayOfWeek.of(w+1)
                    .getDisplayName(TextStyle.FULL, Locale.FRANCE)),' ',15));
            while (z < nbrS && z + (nbrS * w) < size) {
                System.out.print(Utils.rightPad( emploie.get(z + (nbrS * w)),' ',15) );
                z++;
            }
            z = 0;
            System.out.println();
        }

    }


    public static void fonctionPrincipale (List<Matiers> ListMatiers , List<String> emploie ){
        for (int k = 0; k < 5; k++) {
            for (Matiers var : ListMatiers) {
                if (var.getRepetition() != 0) {
                    emploie.add(var.getName());
                    int rep = var.getRepetition();
                    var.setRepetition(rep - 1);
                }
            }
        }
    }
    public static void creerLesMatiers(String[] MatiersN,String[]repetition,int nbrM , List<Matiers> ListMatiers){
        for (int i = 0; i < nbrM; i++) {
            Matiers matier = new Matiers();
            matier.setName(MatiersN[i]);
            matier.setRepetition(Integer.valueOf(repetition[i]));
            ListMatiers.add(matier);
        }
    }
    public static void mercrediSoir(List<String> emploie,int nbrS ){
        for (int i = 0; i < nbrS/2 ; i++) {
            emploie.add(nbrS/2*5,"");
        }
    }

}




