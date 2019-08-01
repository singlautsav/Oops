package guiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;


class Student{

    private String name;
    private boolean isJoined;

    public Student(String name, boolean isJoined) {
        this.name = name;
        this.isJoined = isJoined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}

public class Application {

    public static JPanel p_name;

    public static JLabel l_name;
    public static JTextField tf_name;

    public static ArrayList<Student> students;
    public static void main(String[] args) {

        students = new ArrayList<>();

        JFrame frame = new JFrame("Demo");


        JPanel p_main = new JPanel();
        p_main.setLayout(new BoxLayout(p_main,BoxLayout.Y_AXIS));

        p_name = new JPanel();
        p_name.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_name);

        l_name = new JLabel("Name");
        tf_name = new JTextField();
        tf_name.setPreferredSize(new Dimension(150,50));
        p_name.add(l_name);
        p_name.add(tf_name);


        JPanel p_joined=  new JPanel();
        p_joined.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel l_joined = new JLabel("Joined Backpack : ");

        p_joined.add(l_joined);

        p_main.add(p_joined);

        ButtonGroup bg_joined = new ButtonGroup();

        JRadioButton rb_yes = new JRadioButton("Yes");
        JRadioButton rb_no = new JRadioButton("No");

        bg_joined.add(rb_yes);
        bg_joined.add(rb_no);

        p_joined.add(rb_yes);
        p_joined.add(rb_no);

        rb_no.setSelected(true);
////////////////////////////////////



        JPanel p_message = new JPanel();
        p_message.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel l_message = new JLabel("Message : ");
        JTextField tf_message = new JTextField();
        tf_message.setPreferredSize(new Dimension(150,50));

        p_message.add(l_message);
        p_message.add(tf_message);

        p_main.add(p_message);
////////////////////////////////////


        JPanel p_buttons = new JPanel();
        p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton b_next = new JButton("Next");
        JButton b_print = new JButton("Print");



        b_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String n = tf_name.getText();
                boolean j = rb_yes.isSelected();
                Student s = new Student(n,j);
                students.add(s);
                System.out.println(students.size());
            }
        });

        b_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                tf_message.setText(students.get(students.size() - 1).getName());
            }
        });

        p_buttons.add(b_next);
        p_buttons.add(b_print);

        p_main.add(p_buttons);
////////////////////////////////////




        frame.add(p_main);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


////////////////////////////////////


















    }
}