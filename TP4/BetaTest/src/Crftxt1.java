/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iks-d
 */
import java.io.*;
public class Crftxt1 {
    public static void main (String args[]) throws IOException, InterruptedException
    {
        String nomfich;
        nomfich = "Hello.txt";
        System.out.println("On va enregistrer dans un fichier texte");
        PrintWriter sortie = new PrintWriter(new FileWriter(nomfich));
        for (int i = 0 ; i<15 ; i++)
        {
            sortie.println("Ligne " + i);
        }
        sortie.close();
        System.out.println("C'est fini");
        Thread.sleep(15000);
        System.out.println("on a réussi");
    }
}
