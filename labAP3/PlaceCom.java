package labAP3;
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

//    branch: Branch which the student belongs to
//    company: Company in which student gets placed
//    cgpa: Cgpa of student
//    rollNum: is autoSet rollNumber which uses static count
//    isPlaced: tells if the student is placed/not
//    sc: ArrayList is the scores of student in all the respective exams of company

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

    public Score addScore(int _score, String _name){
        Score ss = new Score(_score,_name);
        this.sc.add(ss);
        return ss;
    }

    public ArrayList<Score> getScores(){
        return this.sc;
    }

    @Override
    public String toString(){
        String s = String.join("\n",
                String.valueOf(this.rollNum),
                String.valueOf(this.cgpa),
                this.branch,
                "isPlaced: "+ this.isPlaced,
                "Comapany: "+ this.company
                );
        return s;
    }

}

class Company {

//  name: Name of Company
//  Courses: List of courses,
//  selectedStudent: ArrayList of Students selected by query 6
//  list: list of all students eligible with their scores
//  numStudents: number of students companies want to take
//  appStatus is closed only when Selected Students equal numStudents

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private String[] courses;
    private ArrayList<Studentx> selectedStudent = new ArrayList<>();
    private List<Object[]> list = new ArrayList<>();
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
                Score ss = a.addScore(sss,this.name);
                Object[] Z = new Object[2];
                Z[0]=(a);
                Z[1]=(ss);
//                Studentx z = (Studentx) Z[0];
//                System.out.println(z);
                list.add(Z);
            }

        }

    }

    public void SelectStudents(){
//        System.out.println("Sorting Started");

//      Sort the list of Shortlisted student according to the score in Test and CGPA.
        Collections.sort(list,new Comparator<Object[]>() {
            public int compare(Object[]r1, Object[]r2) {
                Studentx R1s =(Studentx) r1[0];
                Studentx R2s =(Studentx) r2[0];
                Score R1ss = (Score) r1[1];
                Score R2ss = (Score) r2[1];

                int c = Float.compare(R1s.getcgpa(),R2s.getcgpa());
                if (c==0){
                    c = Float.compare(R1ss.getScoreX(),R2ss.getScoreX());
                }
                return c;
            }

        });
//        System.out.println("Sorted");

        int stuNeeded = this.numStudents - selectedStudent.size();
        int count = list.size()-1;

        while(stuNeeded>0 & count>=0){
            Studentx stu = (Studentx) list.get(count)[0];
            if (!stu.getisPlaced()){
                stu.setCompany(this.name);
                stu.setIsPlaced();
                selectedStudent.add(stu);
                System.out.println(stu.getRollNum());
                stuNeeded -=1;
            }
            count-=1;
        }

        if (this.numStudents==selectedStudent.size()){
            this.appStatus="Closed";
        }
    }

    @Override
    public String toString(){
        String s = String.join("\n",
                this.name,
                "Course Criteria: ",
                String.join("\n", this.courses),
                "Number of Required Students: "+ this.numStudents,
                "Application Status: "+ this.appStatus
        );
        return s;
    }

}

class PlaceCom{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

//      ArrayList for students and companies with object type
//      Studentx and Comapny respectively
        ArrayList<Studentx> studentsNotPlaced = new ArrayList<>();
        ArrayList<Company> companies = new ArrayList<>();

//      Loop to get the students and add them to Student Array
        for (int i=0; i<n;i++){
            String[] x = br.readLine().split(" ");
            float a = Float.parseFloat(x[0]);
            Studentx s = new Studentx(a,x[1]);
            studentsNotPlaced.add(s);
        }
//        System.out.println("Hi");

//      Queries To be input here and this continues
//      till all the students are not placed

        while (!studentsNotPlaced.isEmpty()){
            String a = br.readLine();
            if (a.length()==1){
                int start = Integer.parseInt(a);
                switch (start){

//                  Case when input is 1
//                  We can add companies using the Query,
//                  Add courses Eligible,
//                  Add Required Students and their Scores in the
//                  respective Companies Test

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
                        System.out.println(com);
                        com.findStudent(studentsNotPlaced);
                        break;

//                  Case when input is 2,
//                  It removes all the students that are placed
                    case 2:
                        System.out.println("Accounts Removed For Students: ");
                        for (Iterator<Studentx> iterator = studentsNotPlaced.iterator(); iterator.hasNext();) {
                            Studentx stu = iterator.next();

                            if (stu.getisPlaced()) {

                                System.out.println(stu.getRollNum());
                                iterator.remove();
                            }
                        }
                        break;
//                  Case when input is 3,
//                  It removes all the companies that have required number of students
                    case 3:
                        System.out.println("Accounts Removed for Company: ");
                        for (Iterator<Company> iterator = companies.iterator(); iterator.hasNext();) {
                            Company comp = iterator.next();
                            if (comp.getAppStatus().equals("Closed")) {
                                System.out.println(comp.getName());
                                iterator.remove();
                            }
                        }
                        break;

//                  Case When input is 4,
//                  It displays number of unplaced Students
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

//                  Case when Input is 5,
//                  Displays name of companies whose Applications are Open,
//                  Applications are not closed until all the seats are not filled
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

//                  Case When Input is 6,
//                  Select Students for the said Company,
//                  Remember Students are not removed until query 2 is not called

//                  Case When Input is 7,
//                  Displays details of company if they are in the list of companies,
//                  and they are not removed until query 3 is not called
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
                            }
                            else { System.out.println(comX);}
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
                            if (start == 8) { System.out.println(stu); }
                            else {
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
