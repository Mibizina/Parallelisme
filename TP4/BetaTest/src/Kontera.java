/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iks-d
 */
public class Kontera {
   // public static int n;
    
    public static void main(String args[])
    {
        Gestion a;
        a = new Gestion();
        int y = 15;
        a.affiche();
        a.compteura(y);
    }
}
class Gestion
{
    private int i;   
    public void compteura(int i)
    {
        this.i = i;
        boolean compteur;
        int n;
        n = 0;
        compteur =  true;
            do 
            {
                System.out.println("c'est vrai");
                compteur = false;
                n++;
                System.out.println("la valeur de n dans la boucle 1 " + n );
                do
                    {
                        System.out.println("c'est faux");
                        compteur = true;
                        n++;
                        System.out.println("la valeur de n dans la boucle 2 " + n );
                    }
                    while(compteur == false);
            }
            while (n < i);
        System.out.println("c'est fini");
    }
    public void affiche()
    {
        System.out.println("Heeloooooooo");
    }
}