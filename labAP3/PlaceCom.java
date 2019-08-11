package labAP3;
import java.awt.geom.FlatteningPathIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private String company;
    private float cgpa;
    private int rollNum;
    private boolean isPlaced = false;

    private ArrayList<Score> sc = new ArrayList<>();

    private static int count = 1;

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
    private ArrayList<Studentx> selectedStudent = new ArrayList<>();
    private ArrayList<Studentx> shortListedStudents = new ArrayList<>();
    private List<float[]> list = new ArrayList<>();
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
                float[] all = new float[3];
                all[0]=a.getRollNum();
                all[1]=a.getcgpa();
                all[2]=sss;
                list.add(all);
                shortListedStudents.add(a);
            }
        }

    }

    public void SelectStudents(){

        Collections.sort(list,new Comparator<float[]>() {
            public int compare(float[] r1, float[] r2) {
                int c = Float.compare(r1[2],r2[2]);
                if (c==0){
                    c = Float.compare(r1[1],r2[1]);
                }
                return c;
            }

        });
        for(int a = 0; a<list.size();a++){
            System.out.println(list.get(a)[0]);
        }

//        System.out.println("sorted");

        int stus = numStudents;
        if (list.size()<stus){
            stus = list.size();
        }
        System.out.println("stus: " + stus);
        for (int x=0;x<stus;x++){
            int rollNum = (int) list.get(list.size()-x-1)[0];
//            System.out.println(rollNum);
            for(int z=0;z<shortListedStudents.size();z++){
                Studentx stu = shortListedStudents.get(z);
                if (stu.getRollNum()==rollNum){
                    stu.setIsPlaced();
                    stu.setCompany(this.name);
                    selectedStudent.add(stu);
                    System.out.println(rollNum);
                    numStudents-=1;
                }
            }
        }
        if (numStudents==0){
            this.appStatus="Closed";
        }

    }

}

class PlaceCom{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Studentx> studentsNotPlaced = new ArrayList<>();
        ArrayList<Company> companies = new ArrayList<>();
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
                        System.out.print("Number of Eligible Courses: ");
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
                        int countX = 0;
                        for (int b = 0;b<studentsNotPlaced.size();b++){
                            Studentx stu = studentsNotPlaced.get(b);
                            if (!stu.getisPlaced()){
                                countX+=1;
                            }
                        }
                        System.out.println(countX + " Students Left.");
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
                                System.out.println("here?");
                                comX.SelectStudents();
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
