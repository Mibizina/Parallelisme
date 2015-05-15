/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrsa;

/**
 *
 * @author iks-d
 */
public class RSA_KeySet implements KeySet{
    RSA_PublicKey pkey;
    RSA_SecretKey skey;
    
    public RSA_KeySet(RSA_PublicKey pkey, RSA_SecretKey skey)
    {
        this.pkey = pkey;
        this.skey = skey;
    }
    
    public RSA_KeySet()
    {
       // pkey = new RSA_PublicKey();
        //skey = new RSA_SecretKey();
    }
    
}
