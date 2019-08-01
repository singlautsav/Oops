package labAP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class l1q3 {
    public static void getPrimes(BigInteger n){
        String s = "";
        BigInteger r1 = n.sqrt();
//        System.out.println("Hello");
        for (BigInteger i = BigInteger.valueOf(2); r1.compareTo(i)>=0; i = i.add(BigInteger.ONE)){
//            System.out.println(i);
            while(n.mod(i).equals(BigInteger.ZERO)){

                s += i +"*";
                n = n.divide(i);
            }
        }
        BigInteger x = BigInteger.valueOf(2);
        if (n.compareTo(x)>0){
            s += n + "*";
        }
        String z = s.substring(0,s.length()-1);
        System.out.println(z);
    }

    public static void main(String[] args) throws java.io.IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strs = br.readLine();
        BigInteger x = BigInteger.valueOf(strs.length());
//        System.out.println(x);
        getPrimes(x);
    }

}
