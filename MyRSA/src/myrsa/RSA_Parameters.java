/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrsa;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author iks-d
 */
public class RSA_Parameters implements Parameters{
    int nb_bits;
    Random prg;
    
    public RSA_Parameters(int nb_bits, Random prg)
    {
        this.nb_bits = nb_bits;
        this.prg = prg;
    }
    public RSA_Parameters(int nb_bits)
    {
        this.nb_bits = nb_bits;
        prg = new SecureRandom();
    }
    
}
