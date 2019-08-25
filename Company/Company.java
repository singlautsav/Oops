package Company;


import java.util.*;

class Student {

    private static int ROLL_NUMBER_counter = 1;
    private final int ROLL_NUMBER;
    public int getRollNumber() {
        return ROLL_NUMBER;
    }

    private final float CGPA;
    private final String Branch;
    private int Tech_round_score;

    public int getTech_round_score() {
        return Tech_round_score;
    }

    // to store the name of the company for which the student is applying and also store the marks of tech round of the company
    public static ArrayList<Company> applied_companies_students = new ArrayList<>();
    public static boolean check_company_applied(String company_name){
        for (int i = 0; i < applied_companies_students.size(); i++) {
            if (applied_companies_students.get(i).getCompany_Name().equals(company_name)){
                return true;
            }
        }
        return false;
    }

    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    //
    public  ArrayList<Company> getApplied_companies_students() {
        return applied_companies_students;
    }
//
//    public static void setApplied_companies_students(String company_name) {
//        Student.applied_companies_students = applied_companies_students;
//    }

    public Student(float CGPA, String branch) {
        this.CGPA = CGPA;
        Branch = branch;
        Status = "UNPLACED";
        ROLL_NUMBER = ROLL_NUMBER_counter;
        ROLL_NUMBER_counter++;
    }

    public float getCGPA() {
        return CGPA;
    }

    public String getBranch() {
        return Branch;
    }

}
class Company {

    private String Company_Name;
    private static int Tech_round_score;
    private String[] course_name;
    public static int number_of_student;

    public static ArrayList<Student> student_name = new ArrayList<>();

    public static ArrayList<Student> getStudent_name() {
        return student_name;
    }

    public static void setStudent_name(ArrayList<Student> student_name) {
        Company.student_name = student_name;
    }

    // to get and set company name
    public String getCompany_Name() {
        return Company_Name;
    }
    //
//    public void setCompany_Name(String company_Name) {
//        Company_Name = company_Name;
//    }
//
//    //to get and set tech round score
    public static int getTech_round_score() {
        return Tech_round_score;
    }

    public void setTech_round_score(int tech_round_score) {
        Tech_round_score = tech_round_score;
//        return Tech_round_score;
    }

    public static String Company_Status;

    public String[] getCourse_name() {
        return course_name;
    }

    public int getNumber_of_student() {
        return number_of_student;
    }

    public String getCompany_Status() {
        return Company_Status;
    }

    public static void setCompany_Status(String company_Status) {
        Company_Status = company_Status;
    }

    public Company(String company_Name, String[] course_name, int number_of_student) {
        Company_Name = company_Name;
        this.course_name = course_name;
        this.number_of_student = number_of_student;
        Company_Status = "OPEN";
    }
    public boolean check_company_course_criteria(String course_name){
        for (int i = 0; i < this.course_name.length; i++) {
//            System.out.println("reaches");
//            System.out.println(Arrays.toString(this.course_name));
//            System.out.println(course_name);
            if (this.course_name[i].endsWith(course_name) ){
                return true;
            }
        }
        return false;
    }

    public static void display_course_criteria(String[] Course_Criteria){
        for (int i = 0; i < Course_Criteria.length; i++) {
            System.out.println(Course_Criteria[i]);
        }
    }
}

class Placement_cell{
    public static Scanner s = new Scanner(System.in);

    // ARRAYLIST TO STORE THE SAVE THE SCORE IN THE TECH ROUND
    public static ArrayList<Student> student_list = new ArrayList<>();

    //Arraylist of companies
    public static ArrayList<Company> Company_details = new ArrayList<>();
    // 1
    public void ADD_Company(String company_name,String[] course_name,int number_of_student) {
        Company c1 = new Company(company_name, course_name, number_of_student);
        for (int i = 0; i < course_name.length; i++) {
//            setCompany_Name(company_name);
            if (c1.check_company_course_criteria(student_list.get(i).getBranch())) {
                student_list.get(i).applied_companies_students.add(c1);
                Company_details.add(c1);
            }
        }
        System.out.println(company_name);
        System.out.println("Course Criteria ");
        for (int i = 0; i < course_name.length; i++) {
            System.out.println(course_name[i]);
        }

        System.out.println("Number of Required Student = " + number_of_student);
        System.out.println("Application Status = " + c1.Company_Status);
        System.out.println("Enter scores for the technical round.");
        // here take the input of the required field student
        for (int i = 0; i < student_list.size(); i++) {
            if (c1.check_company_course_criteria(student_list.get(i).getBranch())) {
                System.out.println("Enter the score of Roll no " + (i + 1));
                int tech_score = s.nextInt();
                c1.setTech_round_score(tech_score);
            }
        }
    }
    // 2
    public void remove_account_of_placed_student(){
        for (int i = 0; i < student_list.size(); i++) {
            if (student_list.get(i).getStatus().equals("PLACED")){
                System.out.println(student_list.get(i).getRollNumber());
                student_list.remove(i);
            }
        }
    }
    //3
    public void remove_account_of_company(){
        for (int i = 0; i < Company_details.size(); i++) {
            if (Company_details.get(i).Company_Status.equals("CLOSED")){
                System.out.println("Account removed for ");
                System.out.println(Company_details.get(i).getCompany_Name());
                Company_details.remove(i);
            }
        }
    }
    //4
    public void number_of_unplaced_student(){
        int counter = 0;
        String t = "UNPLACED";
        for (int i = 0; i < student_list.size(); i++) {
            if (student_list.get(i).getStatus().equals(t)){
                counter++;
            }
        }
        System.out.println("Number of unplaced student = "+counter);
    }
    //5
    public void companies_application_open(){
        String t = "OPEN";

//        System.out.println(Arrays.toString(Company_details.toArray()));
//        System.out.println(Company_details.size());

        for (int i = 0; i < Company_details.size(); i++) {
            if (Company_details.get(i).Company_Status.equals(t)){
                System.out.println(Company_details.get(i).getCompany_Name());
            }
        }
    }
    //6
    public void select_rollnumber(String Company_name){
        ArrayList<Student> students = new ArrayList<>();
//        int j =0;
        for (int i = 0; i < student_list.size(); i++) {
            if (Student.check_company_applied(Company_name)){
                students.add(student_list.get(i));
            }
        }
//        System.out.println(students.size());
//        Collections.sort(students);
        students.sort(Comparator.comparing(Student::getTech_round_score));
        int k = Company.number_of_student;
        int[] roll_number = new int[k];
        for (int j =0;j<k;j++){
            roll_number[j] = students.get(j).getRollNumber();
            System.out.println(roll_number[j]);
//            .setCompany_Status("CLOSED");
//            student_list.get().setStatus("PLACED");
        }
        for (int i = 0; i < student_list.size(); i++) {
//            System.out.println(student_list.get(i).getRollNumber() +"-> rollno" );
//            System.out.println(student_list.get(i).getStatus() +"-> status initiall" );

            for (int j = 0; j < roll_number.length; j++) {
                if (student_list.get(i).getRollNumber() == roll_number[j]){
//                    System.out.println("here");
                    student_list.get(i).setStatus("PLACED");
                }
            }
//            System.out.println(student_list.get(i).getRollNumber() +"-> rollno" );
//            System.out.println(student_list.get(i).getStatus() +"-> status finAL" );
//            student_list.get(i).setStatus("UNPLACED");
            if (Company.number_of_student == roll_number.length){
                Company.setCompany_Status("CLOSED");
            }
        }

    }
    //7
    public void company_details(String Company_Name){
        for (int i = 0; i <Company_details.size() ; i++) {
            if (Company_details.get(i).getCompany_Name().equals(Company_Name)){
                System.out.println(Company_details.get(i).getCompany_Name());
                System.out.println("Course Criteria ");
                Company.display_course_criteria(Company_details.get(i).getCourse_name());
                System.out.println("Number of required student = " + Company_details.get(i).getNumber_of_student());
                System.out.println("Application Status = " + Company_details.get(i).Company_Status);

            }
        }
    }
    //8
    public void student_detail(int roll_number) {
        for (int i = 0; i < student_list.size(); i++) {
            if (student_list.get(i).getRollNumber() == roll_number) {
                System.out.println(student_list.get(i).getRollNumber());
                System.out.println(student_list.get(i).getCGPA());
                System.out.println(student_list.get(i).getBranch());
                System.out.println("Placement Status : " + student_list.get(i).getStatus());
            }
        }
    }
    //9
    public void appilied_company_by_student(int roll_number){
        int flag = 0;

//            System.out.println(student_list.get(i).getRollNumber());
        if (student_list.get(roll_number-1).getRollNumber() == roll_number){
            flag = 1;
            System.out.println("Companies Applied :");
            for (int j = 0; j < Student.applied_companies_students.size(); j++) {
                System.out.println(Student.applied_companies_students.get(j).getCompany_Name());
                System.out.println(Student.applied_companies_students.get(j).getTech_round_score());
            }
        }
        else {
            System.out.println("No student with the given roll number has an account.");
        }
    }

    public static boolean check_branch(String Branch){
        String Branch1 = "CSE";
        String Branch2 = "ECE";
        String Branch3 = "CSD";
        String Branch4 = "CSAM";
        String Branch5 = "CSB";
        String Branch6 = "CSSS";

        if (Branch.equals(Branch1) || Branch.equals(Branch2) || Branch.equals(Branch3) || Branch.equals(Branch4) || Branch.equals(Branch5) || Branch.equals(Branch6)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // write your code here
        int Numner_of_Students = s.nextInt();
        Placement_cell p = new Placement_cell();
        for (int i = 0; i < Numner_of_Students; i++) {
            float CGPA = s.nextFloat();
            String Branch = s.next();
            if(!check_branch(Branch)){
                System.out.println("Wrong branch ");
            }
            //call the student class
            Student s1 = new Student(CGPA,Branch);
            student_list.add(s1);

//            System.out.println(Arrays.toString(student_list.toArray()));
        }
        int flag = 0;
        while(true) {
            flag = 1;
            int choice = s.nextInt();
            if (choice == 1) {
                String Company_name = s.next();
                System.out.println("Number of Eligible Courses = ");
                int course_criteria = s.nextInt();
                String[] course_name = new String[course_criteria];
                for (int i = 0; i < course_criteria; i++) {
                    course_name[i] = s.next();
                }
                System.out.println("Number of required students = ");
                int number_of_student = s.nextInt();
                p.ADD_Company(Company_name, course_name, number_of_student);
            } else if (choice == 2) {
                p.remove_account_of_placed_student();
            } else if (choice == 3) {
                p.remove_account_of_company();
            } else if (choice == 4) {
                p.number_of_unplaced_student();
            } else if (choice == 5) {
                p.companies_application_open();
            } else if (choice == 6) {
                String Company_Name = s.next();
                p.select_rollnumber(Company_Name);

            } else if (choice == 7) {
                String Company_Name = s.next();
                p.company_details(Company_Name);
            } else if (choice == 8) {
                int roll_number = s.nextInt();
                p.student_detail(roll_number);
            } else if (choice == 9) {
                int roll_number = s.nextInt();
                p.appilied_company_by_student(roll_number);
            }
            else {
                System.out.println("Wrong Choice");
                System.out.println("Do you want to continue (Y/N) ");
                String t = s.next();
                if (t.equals("N")){
                    break;
                }

            }
        }


    }
}
