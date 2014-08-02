package util;
/**
 * Created by claytonsantosdasilva on 01/07/14.
 */
import scala.collection.immutable.List;

import java.security.*;
import java.math.*;

public class MD5 {
     public  static String  hash(String yVal) throws NoSuchAlgorithmException{

         MessageDigest m=MessageDigest.getInstance("MD5");
         m.update(yVal.getBytes(), 0, yVal.length());

         return (new BigInteger(1,m.digest()).toString(16));
  }



}
