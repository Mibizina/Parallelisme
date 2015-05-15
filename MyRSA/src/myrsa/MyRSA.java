/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrsa;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author iks-d
 */
public class MyRSA implements CipherScheme{
    RSA_Parameters params;
    int nb_bits;
    
    public MyRSA(int nb_bits)
    {
        params = new RSA_Parameters(nb_bits);
    }
    public RSA_Parameters getParameters()
    {
        return params;
    }
    
    
    public RSA_CipherText Encrypt(RSA_PlainText msg, RSA_PublicKey pkey) throws Invalid_PublicKey, Invalid_PlainText
    {
        if (pkey == null)
            throw new Invalid_PublicKey();
        else
        {
            if (msg == null)
                throw new Invalid_PlainText();
            else
            {
                
                BigInteger c = msg.m.modPow(pkey.e, pkey.n);
                return new RSA_CipherText(c);
            }
        }
    }
    
    public RSA_PlainText Decrypt(RSA_CipherText msg, RSA_SecretKey skey) throws Invalid_SecretKey, Invalid_CipherText
    {
        if (skey == null)
            throw new Invalid_SecretKey();
        else
        {
            if (msg == null)
                throw new Invalid_CipherText();
            else
            {
                BigInteger m1 = msg.c.modPow(skey.d, skey.n);
                return new RSA_PlainText(m1);
            }
        }
    }
    class Invalid_PublicKey extends Exception
    {
        public Invalid_PublicKey()
        {
            super("Clé publique invalide");
        }
    }
    class Invalid_PlainText extends Exception
    {
        public Invalid_PlainText()
        {
            super("Message invalide");
        }
    }
    class Invalid_SecretKey extends Exception
    {
        public Invalid_SecretKey()
        {
            super("Clé secrète invalide");
        }
    }
    class Invalid_CipherText extends Exception
    {
        public Invalid_CipherText()
        {
            super("Message chiffré invalide");
        }
    }
    //Keygen
    public RSA_KeySet Keygen()
    {
        BigInteger p = getPrime(params.nb_bits, 1, params.prg);
        BigInteger q = getPrime(params.nb_bits, 1,params.prg);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);// prime(6543) nombre premier 
        BigInteger d = extended_gcd(e,phi);
        while (d.compareTo(BigInteger.ZERO) < 0)
        {
            d = d.add(phi);
        }
        System.out.println("p  = " + p);
        System.out.println("q  = " + q);
        System.out.println("n  = " + n );
        System.out.println("phi = " + phi );
        System.out.println("e = " + e);
        System.out.println("d = " + d);
        
        return new RSA_KeySet(new RSA_PublicKey(n,e), new RSA_SecretKey(n, d));
        
    }
    
    //Extended_gcd
    public static BigInteger extended_gcd(BigInteger a, BigInteger b)
    {
        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastx = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        
        while (b.compareTo(BigInteger.ZERO) !=0)
        {
            BigInteger q = a.divide(b);
            BigInteger c = a.mod(b);
            a = b;
            b = c;
            c = x;
            x = lastx.subtract(q.multiply(x));
            lastx = c;
            c = y;
            y = lastx.subtract(q.multiply(y));
            lasty = c;
        }
        return lastx;
    }
    
    //GetPrime
    public static BigInteger getPrime(int nb_bits, int certainty, Random prg)
    {
        BigInteger p = new BigInteger(nb_bits, certainty, prg);
        return p;
    }
    //Message
    public static BigInteger message(int nb_bits, Random prg)
    {
        BigInteger messa = new BigInteger(nb_bits, prg);
        return messa;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Invalid_PublicKey, Invalid_PlainText, Invalid_SecretKey, Invalid_CipherText {
        // TODO code application logic here
        System.out.println("This is MyRSA");
        MyRSA rsa = new MyRSA(1024);
        RSA_KeySet cles;
        cles = rsa.Keygen();
        Random rs_random = new Random();
        BigInteger message;
        message = new BigInteger(1024, rs_random);
        System.out.println("message = " + message);
        RSA_PlainText monmess = new RSA_PlainText(message);
        RSA_CipherText texte_chiffre = rsa.Encrypt(monmess,cles.pkey);
        System.out.println("cle public = " + cles.pkey.e);
        System.out.println("cle public = " + cles.pkey.n);
        System.out.println("texte chiffre = " + texte_chiffre);
        RSA_PlainText rems = rsa.Decrypt(texte_chiffre, cles.skey);
        System.out.println("texte chiffre = " + String.valueOf(rems));
        System.out.println("texte chiffre = " + rems.toString());
       // String mms = ""+texte_chiffre;
       // System.out.println("texte chiffre = " + mms);
        
    }
    
}