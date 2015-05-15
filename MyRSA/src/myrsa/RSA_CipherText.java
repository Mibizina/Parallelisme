/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrsa;

import java.math.BigInteger;

/**
 *
 * @author iks-d
 */
class RSA_CipherText implements CipherText{
    BigInteger c ;
    public RSA_CipherText(BigInteger c)
    {
        this.c = c;
    }
    public String toString()
    {
        return c.toString();
    }
    
}
