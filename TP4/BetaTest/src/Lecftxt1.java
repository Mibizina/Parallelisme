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

public class Lecftxt1 {
    public static void main(String args []) throws IOException
    {
        String nomfich;
        nomfich = "Hello.txt";
        String ligne;
        System.out.println("On va chercher dans un fichier texte");
        BufferedReader entree = new BufferedReader(new FileReader(nomfich));
        int n = 0;
        String stockage[] = new String[50];
        do
        {
            ligne = entree.readLine();
            if (ligne != null) 
            {System.out.println(ligne);
            stockage[n] = ligne;
            n++;
            }
        }
        while (ligne != null);
        entree.close();
        System.out.println("Fin liste fichier");
        System.out.println(stockage[3]);
    }
}
