package labAP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class l1q2 {
    public static void main(String[] args) throws java.io.IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strs = br.readLine();
        char temp = strs.charAt(0);
//        String[] arr = new String[strs.length*2];
        String s ="";
        s+= temp;
        int count =1;
        for (int x =1;x<strs.length();x++){
            char check = strs.charAt(x);
//            System.out.println(check);
            if (temp == check){
                count +=1;
            }
            else{
                s+=count;
                count =1;
                temp = strs.charAt(x);
                s+=temp;
            }
        }
        System.out.println(s);
    }
}
