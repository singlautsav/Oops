package labAP2;

class Student{
    String Name;
    int Age ;
    int rollNum;
    String Branch;
    static int LatestRollNum;

    public Student(){
//        rollNum = LatestRollNum++;
    }

    public Student(String name, int age, String branch, int roll){
        Name  = name;
        Age = age;
        Branch = branch;
        rollNum = roll;
        LatestRollNum = rollNum;

    }

    public void display(){
        System.out.println(Name);
        System.out.println(Age);
        System.out.println(rollNum);
        System.out.println(Branch);
        System.out.println(" ");
    }

//    public int latestNum(){
//        return LatestRollNum;
//    }
}

public class l2q1 {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.Name = "Dino";
        s1.Age = 20;
        s1.Branch = "Design";
        s1.rollNum = 201;

        Student s2 = new Student();
        s2.Name = "Vasu";
        s2.Age = 20;
        s2.Branch = "CSE";
        s2.rollNum = 320;

        Student s3 = new Student();
        s3.Name = "Koi Toh Hoga";
        s3.Age = 20;
        s3.Branch = "Design";
        s3.rollNum = 220;

        Student s4 = new Student();
        s4.Name = "Ajeeb Hi HomeWork Hai";
        s4.Age = 50;
        s4.Branch = "Applied Maths";
        s4.rollNum = 200;

        Student s5 = new Student();
        s5.Name = "Bore hue ye karte hue?";
        s5.Age = 21;
        s5.Branch = "Design";
        s5.rollNum = 341;


        Student s6 = new Student("Utsav",20,"Design",321);
        Student s7 = new Student("Chandan", 20,"BioScience",386);
        Student s8 = new Student("Bhunesh", 19, "Design",305);
        Student s9 = new Student("Ritik",19,"Design",280);
        Student s10 = new Student("Sahi ho assignment waley?", 20,"Medical",1);
        s1.display();
        s2.display();
        s3.display();
        s4.display();
        s5.display();
        s6.display();
        s7.display();
        s8.display();
        s9.display();
        s10.display();
        System.out.println(Student.LatestRollNum);


    }
}
