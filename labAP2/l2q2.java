package labAP2;

class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}


class LL{
    static Node head = new Node(-1);
    static int size;

    public LL(Node x){
        Node temp = head;
        while (temp.next!=null){
            temp = temp.next;

        }
        temp.next = x;
        size ++;
    }

    public LL(int data){
        Node n = new Node(data);
        Node temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = n;
        size++;
    }


    public void display(){
        sort();
//        LL.sort()
        Node temp = head.next;
        while(temp!=null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void sort(){
        for (int i = 0;i<size;i++){
            Node temp = head.next;
            for (int j=0;j<size-i-1;j++){
                Node next = temp.next;
                if (next.data<temp.data){
                    int x = temp.data;
                    temp.data = next.data;
                    next.data = x;
                }
                temp = next;
            }

        }
    }

    public void find(){
        sort();
        Node temp = head.next;
        System.out.print(temp.data + " ");
        while(temp.next!=null){
            temp = temp.next;
        }
        System.out.println(temp.data);
    }

}

public class l2q2 {
    public static void main(String[] args) {
        LL d = new LL(1);
        LL d2 = new LL(10);
        LL d3 = new LL(9);
        LL d4 = new LL(6);
        LL d5 = new LL(14);

        Node x1 = new Node(8);
        Node x2 = new Node(7);
        Node x3 = new Node(4);
        Node x4 = new Node(11);
        Node x5 = new Node(22);
        LL d6 = new LL(x1);
        LL d7 = new LL(x2);
        LL d8 = new LL(x3);
        LL d9 = new LL(x4);
        LL d10 = new LL(x5);

        d2.display();
        System.out.println(" ");
        d2.find();

    }

//    LinkedList d2 = new LinkedList(10);
//    LinkedList d3 = new LinkedList(7);

}
