package labAP3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


class Score{
    private int scoreX;
    private String comName;
    public Score(int score,String name){
        this.scoreX = score;
        this.comName = name;
    }

    public int getScoreX(){
        return this.scoreX;
    }

    public String getComName(){
        return this.comName;
    }
}


class Studentx {
    private String branch;
    private ArrayList<Score> sc = new ArrayList<>();
    private float cgpa;
    private static int count = 1;
    private int rollNum;
    private String company;
    private boolean isPlaced = false;

    public Studentx(){

    }
    public Studentx(float _cgpa,String _branch ){
        this.branch = _branch;
        this.cgpa = _cgpa;
        this.rollNum = count;
        count+=1;
    }
    public void addCompany(String _company){
        this.company = _company;
        this.isPlaced = true;
    }

    public int getRollNum(){
        return this.rollNum;
    }

    public boolean getisPlaced(){
        return this.isPlaced;
    }

    public String getCompany(){
        return this.company;
    }

    public String getBranch(){
        return this.branch;
    }

    public float getcgpa(){
        return this.cgpa;
    }

    public void setCompany(String companyName){
        this.company = companyName;
    }

    public void setIsPlaced(){
        this.isPlaced = true;
    }

    public void addScore(int _score, String _name){
        Score ss = new Score(_score,_name);
        this.sc.add(ss);
    }

    public ArrayList<Score> getScores(){
        return this.sc;
    }

}

class Company {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private String[] courses;
    private ArrayList<Studentx> studentPos = new ArrayList<>();
    private ArrayList<Studentx> selectedStudent = new ArrayList<>();
    private int numStudents;
    private String appStatus = "Open";

    public Company(){

    }
    public Company(String _name, String[] _courses, int _numStudents, String _appStatus){
        this.name = _name;
        this.courses = _courses;
        this.numStudents = _numStudents;
        this.appStatus = _appStatus;
    }

    public String[] getCourses(){
        return this.courses;
    }

    public String getName(){
        return this.name;
    }
    public int getNumStudents(){
        return this.numStudents;
    }

    public String getAppStatus(){
        return this.appStatus;
    }

    public ArrayList<Studentx> getSelectedStudent(){
        return this.selectedStudent;
    }
    public void findStudent(ArrayList<Studentx> x)throws IOException{

        for (int i=0; i<x.size();i++){
            Studentx a = x.get(i);
            boolean flag = false;
            for (int z = 0;z<courses.length;z++){
                if (a.getBranch().equals(courses[z]) & !a.getisPlaced()){
                    flag = true;
                }
            }
            if (flag){
                System.out.print("Enter Score for rollNum " + a.getRollNum() + ": ");
                int sss = Integer.parseInt(br.readLine());
                a.addScore(sss,this.name);
                studentPos.add(a);
            }
        }

    }

    public void SelectStudents(){
        while (true){
            break;
        }
//        Collections.sort(studentPos, new CustomComparator(){
//            @Override
//        });
        int numStus;
        if (this.numStudents>studentPos.size()){
            numStus = studentPos.size();
        }
        else{
            numStus = this.numStudents;
        }

        for (int i=0;i<numStus;i++){
            Studentx stu = studentPos.get(i);
            stu.setIsPlaced();
            stu.setCompany(this.name);
        }
    }

}

class PlaceCom{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Studentx> studentsNotPlaced = new ArrayList<Studentx>();
        ArrayList<Company> companies = new ArrayList<Company>();
        ArrayList<Studentx> studentsPlaced = new ArrayList<Studentx>();
        ArrayList<Company> companiesClosed = new ArrayList<Company>();
        for (int i=0; i<n;i++){
            String[] x = br.readLine().split(" ");
            float a = Float.parseFloat(x[0]);
            Studentx s = new Studentx(a,x[1]);
            studentsNotPlaced.add(s);
        }
        System.out.println("Hi");

        while (!studentsNotPlaced.isEmpty()){
            String a = br.readLine();
            if (a.length()==1){
                int start = Integer.parseInt(a);
                switch (start){
                    case 1:
                        String comName = br.readLine();
                        System.out.print("Number of Courses: ");
                        int numCourses = Integer.parseInt(br.readLine());
                        String[] courses = new String[numCourses];
                        for (int b=0;b<numCourses;b++){
                           courses[b]=br.readLine();
                        }
                        System.out.print("Number of Req Candidates: ");
                        int numStudents = Integer.parseInt(br.readLine());
                        Company com = new Company(comName,courses,numStudents,"Open");
                        companies.add(com);
                        com.findStudent(studentsNotPlaced);
                        break;
                    case 2:
                        for (Iterator<Studentx> iterator = studentsNotPlaced.iterator(); iterator.hasNext();) {
                            Studentx stu = iterator.next();
                            if (stu.getisPlaced()) {
                                System.out.println(stu.getRollNum());
                                iterator.remove();
                            }
                        }
                        break;
                    case 3:
                        for (Iterator<Company> iterator = companies.iterator(); iterator.hasNext();) {
                            Company comp = iterator.next();
                            if (comp.getAppStatus().equals("Closed")) {
                                System.out.println(comp.getName());
                                iterator.remove();
                            }
                        }
                        break;
                    case 4:
                        System.out.println(studentsNotPlaced.size() + " Students Left.");
                        break;
                    case 5:
                        for (int coms=0;coms<companies.size();coms++) {
                            Company m = companies.get(coms);
                            if (m.getAppStatus().equals("Open")) {
                                System.out.println(m.getName());
                            }
                        }
                        break;
                }

            }
            else{
                String[] check = a.split(" ");
                int start = Integer.parseInt(check[0]);
                switch (start){
                    case 6: case 7:
                        String comName = check[1];
                        Company comX = new Company();
                        boolean flag = false;
                        for (int num=0;num<companies.size();num++){
                            Company com = companies.get(num);
                            if (com.getName().equals(comName)) {
                                comX = com;
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            if (start == 6) {
                                break;
                            } else {
                                System.out.println(comX.getName());
                                String[] courses = comX.getCourses();
                                for (int j = 0; j < courses.length; j++) {
                                    System.out.println(courses[j]);
                                }
                                System.out.println(comX.getNumStudents());
                                System.out.println(comX.getAppStatus());
                            }
                        }
                        else{
                            System.out.println("No Such Company Found");
                        }
                        break;

                    case 8: case 9:
                        int rollNum = Integer.parseInt(check[1]);
                        Studentx stu = new Studentx();
                        boolean flagX = false;
                        for (int v=0;v<studentsNotPlaced.size();v++){
                            if (studentsNotPlaced.get(v).getRollNum()==rollNum){
                                stu = studentsNotPlaced.get(v);

                                flagX = true;
                                break;
                            }
                        }
                        if (flagX) {
                            if (start == 8) {
                                System.out.println(stu.getRollNum());
                                System.out.println(stu.getcgpa());
                                System.out.println(stu.getBranch());
                                if (!stu.getisPlaced()) {
                                    System.out.println("Not Placed");

                                } else {
                                    System.out.println("Placed");
                                    System.out.println(stu.getCompany());
                                }
                            } else {
                                ArrayList<Score> scores = stu.getScores();
                                for (int k = 0; k < scores.size(); k++) {
                                    System.out.println("Score: " + scores.get(k).getScoreX() + " Company: " + scores.get(k).getComName());
                                }
                            }
                        }
                        else{
                            System.out.println("No Student With Given Roll Num");
                        }
                        break;
                }
            }
        }

    }
}
