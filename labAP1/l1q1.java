package labAP1;

import java.io.*;

class Node
{
    int num;
    Node next;
    Node(int x)
    {
        num=x;
        next=null;
    }
}

class LinkedList
{
    Node start;
    public LinkedList()
    {
        start=new Node(-1); //Dummy start node which is not considered as part of the list
    }


    public void add(int x){
        Node ptr = start;
        while (ptr.next!=null){
            ptr = ptr.next;
        }
        Node z = new Node(x);
        ptr.next = z;
    }

    public void delete(int x){
        Node first = start.next;
        Node second = first.next;
        for (int i=2; i<x; i++){
            first = first.next;
            second = second.next;
        }
        first.next = second.next;

    }

    public void display()
    {
        Node ptr=start.next;
        while(ptr!=null)
        {
            System.out.print(ptr.num+" ");
            ptr=ptr.next;
        }
        System.out.println();
    }
}

public class l1q1 {

    public static void main(String[] args) throws java.io.IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        for (int j=0;j<a;j++){
            LinkedList l = new LinkedList();
            int b = Integer.parseInt(br.readLine());
            String  lines = br.readLine();
            String[] strs = lines.trim().split("\\s+");
            for (int index = 0; index<b;index++){
                int q = Integer.parseInt(strs[index]);
//                System.out.println(q);
                l.add(q);
            }
//            l.display();
            int z = Integer.parseInt(br.readLine());
            if (z<=0){
                System.out.println("False");
                System.out.println(0);
                l.display();
            }
            else{
                System.out.println("True");
                System.out.println(b-z+1);
                l.delete(z);

                l.display();
            }
        }
    }
}
