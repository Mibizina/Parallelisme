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
public class RSA_SecretKey implements SecretKey{
    BigInteger n, d;
    public RSA_SecretKey(BigInteger n, BigInteger d)
    {
        this.d = d;
        this.n = n;
    }
}
