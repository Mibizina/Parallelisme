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
public class RSA_PublicKey implements PublicKey{
    BigInteger n, e;
    public RSA_PublicKey(BigInteger n, BigInteger e)
    {
        this.n = n;
        this.e = e;
        
    }
    
}
