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
class RSA_PlainText implements PlainText{
    BigInteger m;
    public RSA_PlainText(BigInteger m)
    {
        this.m = m;
    }
    
}
