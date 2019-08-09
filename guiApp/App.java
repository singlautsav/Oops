package guiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


class Node{
    Runner stu;
    Node next;
    Node(Runner obj){
        stu = obj;
        next = null;
    }
}


class LinkedList{
    Node head;
    static int size;
    public LinkedList(){
        Runner x = new Runner("Head",-1, "null");
        head = new Node(x);

    }

    public void add(Runner y){
        Node ptr = head;
        while(ptr.next!=null){
            ptr = ptr.next;
        }
        Node l = new Node(y);
        ptr.next = l;
        size++;
    }

    public void sort(){
        for (int i = 0;i<size;i++){
            Node temp = head.next;

            for (int j=0;j<size-i-1;j++){
                Node next = temp.next;
                if (next.stu.time()<temp.stu.time()){
                    Runner x = temp.stu;
                    temp.stu = next.stu;
                    next.stu = x;
                }
                temp = next;
            }

        }
    }
    public void display(){
        Node ptr=head.next;
        while(ptr!=null)
        {
            System.out.print(ptr.stu.name() + " " + ptr.stu.time());
            ptr=ptr.next;
        }
        System.out.println();
    }

    public Runner[] winners(){
        Runner[] winner = new Runner[6];
        Node ptr = head.next;
        int i = 0;
        int k5 = 0;
        int k10 = 0;
        int k20 = 0;
//        Node ptr = head.next;
        while (ptr!=null){
            if (ptr.stu.category.equals("Half Marathon") && k20<2) {
                winner[i] = ptr.stu;
                k20 += 1;
                i+=1;
            }
            else if (ptr.stu.category.equals("Open 10K Run")&& k10<2) {
                winner[i]=ptr.stu;
                k10+=1;
                i+=1;
            }
            else if (ptr.stu.category.equals("Great Delhi Run")&& k5<2){
                winner[i] = ptr.stu;
                k5+=1;
                i+=1;
            }
            ptr = ptr.next;
        }
    return winner;
    }

}


class Runner{
    private String name;
    private int time;
    String category;
    public Runner(){

    }
    public Runner(String name, int time,String category){
        this.name = name;
        this.time = time;
        this.category = category;

    }
    public String name(){
        return this.name;
    }
    public int time(){
        return this.time;
    }

}

public class App {
    public static LinkedList l;
    public static void main(String[] args) {
        l = new LinkedList();


        JFrame frame = new JFrame("Marathon Runners");
        JPanel p_main = new JPanel();
        p_main.setLayout(new BoxLayout(p_main,BoxLayout.Y_AXIS));

////////////////////////////////////////////

        JPanel p_name = new JPanel();
        p_name.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_name);

        JLabel l_name = new JLabel("Name");
        JTextField tf_name = new JTextField();
        tf_name.setPreferredSize(new Dimension(150,20));

        p_name.add(l_name);
        p_name.add(tf_name);

/////////////////////////////////////////////

        JPanel p_time = new JPanel();
        p_time.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_time);

        JLabel l_time = new JLabel("Time");
        JTextField tf_time = new JTextField();
        tf_time.setPreferredSize(new Dimension(150,20));

        p_time.add(l_time);
        p_time.add(tf_time);

//////////////////////////////////////////////


        JPanel p_cat = new JPanel();
        p_cat.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel l_cat = new JLabel("Category");
        p_main.add(p_cat);
        p_cat.add(l_cat);
        ButtonGroup bg_cats = new ButtonGroup();
        JRadioButton rb_20k = new JRadioButton("Half Marathon");
        JRadioButton rb_10k = new JRadioButton("Open 10K Run");
        JRadioButton rb_5k = new JRadioButton("Great Delhi Run");

        bg_cats.add(rb_5k);
        bg_cats.add(rb_10k);
        bg_cats.add(rb_20k);

        p_cat.add(rb_5k);
        p_cat.add(rb_10k);
        p_cat.add(rb_20k);

        rb_10k.setSelected(true);

///////////////////////////////////////////////
//        first in Great Delhi Run
        JPanel p_wgdr = new JPanel();
        p_wgdr.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_wgdr);

        JLabel l_wgdr = new JLabel("Great Delhi Run");
        JTextField tf_fgdr = new JTextField();
        tf_fgdr.setPreferredSize(new Dimension(250,20));

//       Second in Great Delhi Run
        JTextField tf_sgdr = new JTextField();
        tf_sgdr.setPreferredSize(new Dimension(250,20));

        p_wgdr.add(l_wgdr);
        p_wgdr.add(tf_fgdr);
        p_wgdr.add(tf_sgdr);

///////////////////////////////////////////////
//        WInner of Open 10K
        JPanel p_wor = new JPanel();
        p_wor.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_wor);

        JLabel l_wor = new JLabel("Open 10K Run");
        JTextField tf_for = new JTextField();
        tf_for.setPreferredSize(new Dimension(250,20));

        JTextField tf_sor = new JTextField();
        tf_sor.setPreferredSize(new Dimension(250,20));

        p_wor.add(l_wor);
        p_wor.add(tf_for);
        p_wor.add(tf_sor);

///////////////////////////////////////////////
        JPanel p_whm = new JPanel();
        p_whm.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_whm);

        JLabel l_whm = new JLabel("Half Marathon");
        JTextField tf_fhm = new JTextField();
        tf_fhm.setPreferredSize(new Dimension(250,20));

        JTextField tf_shm = new JTextField();
        tf_shm.setPreferredSize(new Dimension(250,20));

        p_whm.add(l_whm);
        p_whm.add(tf_fhm);
        p_whm.add(tf_shm);

///////////////////////////////////////////////

        JPanel p_buttons = new JPanel();
        p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton b_next = new JButton("Next");
        JButton b_winner = new JButton("Find Winner");
        JButton b_cancel = new JButton("Cancel");

        b_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameX = tf_name.getText();
                String timeX = tf_time.getText();
                boolean sel5k = rb_5k.isSelected();
                boolean sel10k = rb_10k.isSelected();
                boolean sel20k = rb_20k.isSelected();
                if (nameX.equals("")){
                    JDialog a = new JDialog(frame, "Name Error");
                    JLabel z = new JLabel("Name Cannot Be empty");
                    a.add(z);
                    a.setSize(200, 200);
                    a.setVisible(true);
                    return;
                }
                try{
                    Integer.parseInt(timeX);
                }catch (Exception f){
                    JDialog b = new JDialog(frame, "Time Error");
                    JLabel c = new JLabel("Input Valid Time");
                    b.add(c);
                    b.setSize(200, 200);
                    b.setVisible(true);
                    return;
                }
//                ButtonModel buttonModel = bg_cats.getSelection();
//                String val = bg_cats.getElements().nextElement().getText();
                String val;
                if (sel5k){
                    val = rb_5k.getText();
                }
                else if (sel10k){
                    val = rb_10k.getText();
                }
                else{
                    val = rb_20k.getText();
                }
                tf_name.setText("");
                tf_time.setText("");
                Runner s = new Runner(nameX,Integer.valueOf(timeX),val);
                l.add(s);
                System.out.println(nameX +" " + timeX +" " + val);
            }
        });
        b_winner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int k5 = 0;
                    int k10 = 0;
                    int k20 = 0;
                    l.sort();
                    Runner[] winners = l.winners();
                    for (int i = 0; i < winners.length; i++) {
                        if (winners[i].category.equals("Half Marathon")) {
                            if (k20 == 0) {
                                tf_fhm.setText("First " + winners[i].name() + " Rs. 2,80,000/-");
                                k20 += 1;
                            } else if (k20 == 1) {
                                tf_shm.setText("Second " + winners[i].name() + " Rs. 2,10,000/-");

                            }
                        } else if (winners[i].category.equals("Open 10K Run")) {
                            if (k10 == 0) {
                                tf_for.setText("First " + winners[i].name() + " Rs. 1,90,000/-");
                                k10 += 1;
                            } else if (k10 == 1) {
                                tf_sor.setText("Second " + winners[i].name() + " Rs. 1,50,00/-");
                            }
                        } else if (winners[i].category.equals("Great Delhi Run")) {
                            if (k5 == 0) {
                                tf_fgdr.setText("First " + winners[i].name() + " Rs. 1,35,000/-");
                                k5 += 1;
                            } else if (k5 == 1) {
                                tf_sgdr.setText("Second " + winners[i].name() + " Rs.1,15,000/-");
                            }
                        }
                    }
                } catch (Exception z){
                    JDialog d = new JDialog(frame, "dialog Box");
                    JLabel l = new JLabel(z+ " :- Not Enough Values Provided to find 2 winners in each category");
                    d.add(l);
                    d.setSize(500, 100);
                    d.setVisible(true);
                }
            }
        });

        b_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        p_main.add(p_buttons);
        p_buttons.add(b_next);
        p_buttons.add(b_winner);
        p_buttons.add(b_cancel);


        frame.add(p_main);
        frame.setSize(1000,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
