package labAP3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Studentx {
    private String branch;
    private float cgpa;
    private static int count = 1;
    private int rollNum;
    private String company;
    private boolean isPlaced = false;


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
}

class Company{
    private String name;
    private String[] courses;
    private int numStudents;
    private String appStatus = "Open";

    public Company(String _name, String[] _courses, int _numStudents, String _appStatus){
        this.name = _name;
        this.courses = _courses;
        this.numStudents = _numStudents;
        this.appStatus = _appStatus;
    }

    public int[] checkStudent(ArrayList<Studentx> x){
        while(!x.isEmpty()){

        }
    }

}

class App{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Studentx> students = new ArrayList<Studentx>(n);
        ArrayList<Company> companies = new ArrayList<Company>();
        for (int i=0; i<n;i++){
            String[] x = br.readLine().split(" ");
            float a = Float.parseFloat(x[0]);
            Studentx s = new Studentx(a,x[1]);
            students.add(s);
        }

        while (!students.isEmpty()){
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

                    case 2:
                        pass;
                    case 3:
                        pass;
                    case 4:
                        pass;
                    case 5:
                        pass;
                }

            }
            else{
                String[] check = a.split(" ");
                int start = Integer.parseInt(check[0]);
            }
        }

    }
}
