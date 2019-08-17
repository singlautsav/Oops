package labAP4;
//
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
//
//

//
//
//    @Override
//    public String toString() {
//        String s = this.id + " " + this.name + " " + this.price + " " + this.quant + " " + this.offer + " " + this.category;
//        return s;
//    }
////
////}
////
////class Merchant{
////    private String name;
////    private ArrayList<Item> items = new ArrayList<>();
////    private int numSlots = 10;
////
////
////    public Merchant(String _name){
////        this.name = _name;
////    }
////
////    public String getName(){
////        return this.name;
////    }
////
////    public int getNumSlots(){
////        return this.numSlots;
////    }
////
////    public void addItem(Item itemx){
////        if (items.size()<numSlots){
////            items.add(itemx);
////            System.out.println(itemx);
////        }
////        else{
////            System.out.println("You dont have enough slots to add Item");
////        }
////    }
////
////
////}
////
////class Customer{
////    private String name;
////    private int mainAcc = 100;
////    private int reward = 0;
////
////    public Customer(String _name){
////        this.name = _name;
////    }
////
////    public String getName(){
////        return this.name;
////    }
////}
////
////
////
////
////
////class App{
////    static ArrayList<Merchant> merchs = new ArrayList<>();
////    static ArrayList<Customer> customers = new ArrayList<>();
////
////    public static void printMenu(){
////        String s = String.join("\n",
////                "Welcome To Mercury",
////                "1) Enter as Merchant",
////                "2) Enter As Customer",
////                "3) See User Details",
////                "4) Company account Bal",
////                "5) Exit"
////        );
////        System.out.println(s);
////    }
////    public static void printMerch(Merchant merch){
////        String s = String.join("\n",
////                "Welcome " + merch.getName(),
////                "1) Add Item",
////                "2) Edit Item",
////                "3) Search by Category",
////                "4) Add Offer",
////                "6) Exit");
////        System.out.println(s);
////    }
////
////
////    public static void main(String[] args)throws IOException {
////        merchs.add(new Merchant("jack"));
////        merchs.add(new Merchant("john"));
////        merchs.add(new Merchant("james"));
////        merchs.add(new Merchant("jeff"));
////        merchs.add(new Merchant("joseph"));
////
////        customers.add(new Customer("ali"));
////        customers.add(new Customer("nobby"));
////        customers.add(new Customer("bruna"));
////        customers.add(new Customer("borat"));
////        customers.add(new Customer("aladeen"));
////
////        boolean exitAppFlag = true;
////
////        while (exitAppFlag){
////            printMenu();
////            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////            int opt = Integer.parseInt(br.readLine());
////            switch (opt){
////                case 1:
////                    for (int i =0;i<merchs.size();i++){
////                        System.out.println(i+") "+ merchs.get(i).getName());
////                    }
////
////                    int selMerch = Integer.parseInt(br.readLine());
////                    boolean exitMerch = true;
////                    while (exitMerch){
////                        Merchant merch = merchs.get((selMerch-1));
////                        printMerch(merch);
////                        int optMerch = Integer.parseInt(br.readLine());
////                        switch (optMerch){
////                            case 1:
////                                Item itemAdd = new Item();
////                                itemAdd.make();
////                                merch.addItem(itemAdd);
////                            case 2:
////
////
////                            case 3:
////
////                            case 4:
////
////                            case 5:
////
////                            case 6:
////                                exitMerch=false;
////                        }
////                    }
////
////                case 2:
////                    for (int i =0;i<customers.size();i++){
////                        System.out.println(i+") "+ customers.get(i).getName());
////                    }
////                    int selCust = Integer.parseInt(br.readLine());
////
////                case 3:
////                    String[] a = br.readLine().split(" ");
////                    if (a[0].equals("M")){
////                        //do something with a[1] in merchant class
////                    }
////                    else{
////                        //do something with a[1] in customer class
////                    }
////                case 4:
////                    //show balance in companies account
////
////                case 5:
////                    exitAppFlag = false;
////
////            }
////        }
////
////    }
////}
//
public class App {

public static void main(String[] args) throws IOException {
    System.out.println("HI");
        Company c = new Company();
        c.startCompany();
    }
}

class Company {
    private Users _User;
//    private Users _costumer;
    private ArrayList<Merchant> merchs = new ArrayList<>();
    private ArrayList<Customers> customers = new ArrayList<>();

//    public void details(UserX user){
//        this.printX();
//    }

    public void printMenu() {
        String s = String.join("\n",
                "Welcome To Mercury",
                "1) Enter as Merchant",
                "2) Enter As Customer",
                "3) See User Details",
                "4) Company account Bal",
                "5) Exit"
        );
        System.out.println(s);
    }

    public Company() {
        _User = new Users();
        merchs.add(new Merchant("jack"));
        merchs.add(new Merchant("john"));
        merchs.add(new Merchant("james"));
        merchs.add(new Merchant("joseph"));
        merchs.add(new Merchant("joseph"));
        System.out.println(merchs.get(0).getName());
        customers.add(new Customers("aladeen"));
        customers.add(new Customers("ali"));
        customers.add(new Customers("bruno"));
        customers.add(new Customers("borat"));
        customers.add(new Customers("nobby"));
    }

    public void startCompany() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean exitAppFlag = true;
        while (exitAppFlag) {
            printMenu();
            int opt = Integer.parseInt(br.readLine());
            switch (opt) {
                case 1:
                    for (int i = 0; i < merchs.size(); i++) {
                        System.out.println(i+1 + ") " + (merchs.get(i).getName()));
                    };
                    int selMerch = Integer.parseInt(br.readLine());
                    boolean exitMerch = true;
                    while (exitMerch) {
                        Merchant merch = merchs.get((selMerch - 1));
                        merch.printX();
                        int optMerch = Integer.parseInt(br.readLine());
                        switch (optMerch) {
                            case 1:
                                Item itemAdd = new Item();
                                itemAdd.make();
//                                merch.addItem(itemAdd);
                                System.out.println("HI");
                                _User.addItemX(merch,itemAdd,1);
                                break;
                            case 2:
                                System.out.println("Choose Item by Code");
                                merch.EditItems();
                                _User.editItemX(merch,2);
                            case 3:
                                _User.useSearch(merch, merchs);
                            case 4:

                            case 5:

                            case 6:
                                exitMerch = false;
                        }
                    }

                case 2:
                    for (int i = 0; i < customers.size(); i++) {
                        System.out.println(i + ") " + customers.get(i).getName());
                    }
                    int selCust = Integer.parseInt(br.readLine());

                case 3:
                    String[] a = br.readLine().split(" ");
                    if (a[0].equals("M")) {
                        //do something with a[1] in merchant class
                    } else {
                        //do something with a[1] in customer class
                    }
                case 4:
                    //show balance in companies account

                case 5:
                    exitAppFlag = false;

            }
        }

    }
}

class Users{
    public Users(){

    }

    public void useSearch(UserX _user,ArrayList<Merchant> merchants)throws IOException{
        String cat = _user.Search();

    }

    public void addItemX(UserX _user, Item itemX,int a)throws IOException{
//        System.out.println("hiiii");
        _user._helper(itemX,a);
    }
    public void editItemX(UserX _user, int b)throws IOException{
        _user._helper(b);
    }




}


class Merchant implements UserX {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private int id;
    private int numSlots = 10;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> cats = new ArrayList<>();
    private int countd;
    {
        countd += 1;
    }

    public Merchant(String _name) {
        this.name = _name;
        this.id = countd;
    }

    public void printOpts(ArrayList<Merchant> merchs){
        for (int i = 0; i < merchs.size(); i++) {
            System.out.println(i+1 + ") " + (merchs.get(i)));
        };
    }
    public void _helper(Object... values)throws IOException{

        if (values.length==2){
            Item x = (Item) values[0];
            this.addItem(x);
        }
        else if (values.length==1){
            Item x = (Item) values[0];
            this.EditItems();
        }
        else{

        }

    }

    public void addItem(Item itemx) {
        if (items.size() < numSlots) {
            items.add(itemx);
            boolean flagCat = true;
            for (int i=0; i<cats.size();i++){
                if (cats.get(i).equals(itemx.getCat())){
                    flagCat = false;
                    break;
                }
            }
            if (flagCat){
                cats.add(itemx.getCat());
            }

            System.out.println(itemx);

        } else {
            System.out.println("You dont have enough slots to add Item");
        }
    }

    public void EditItems()throws IOException{
        for (int i=0;i<items.size();i++){
            System.out.println(items.get(i));
        }
        int n = Integer.parseInt(br.readLine());
        Item it = items.get(n-1);
        it.edit();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String Search()throws IOException{
        for (int i=0;i<cats.size();i++){
            System.out.println(i+") "+ cats.get(i));
        }
        int x = Integer.parseInt(br.readLine());
        return cats.get(x);

    }


    @Override
    public void seeReward() {

    }

    //    @Override
    public void printX() {
        String s = String.join("\n",
                "Welcome " + this.getName(),
                "1) Add Item",
                "2) Edit Item",
                "3) Search by Category",
                "4) Add Offer",
                "6) Exit");
        System.out.println(s);
    }

}


class Customers implements UserX {

    private String name;
    private int id;
    private int countd;
//        private static int countd;

    {
        countd += 1;
    }

    public Customers(String _name) {
        this.name = _name;
//            this.id = countd;
    }

    public void _helper(Object... values){

    }

    public String getName() {
        return this.name;
    }

    @Override
    public void Search() {

    }

    @Override
    public void seeReward() {

    }

    public void printX() {

    }
}

class Item {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private int price;
    private int quant;
    private String category;
    private String offer;
    private int id;

    private static int countd;

    {
        countd += 1;
    }

    public String getName(){
        return this.name;
    }
//    public

    public void make() throws IOException {
        this.id = countd;
        System.out.println("Enter Item Details");
        System.out.println("item Name:");
        this.name = br.readLine();
        System.out.println("item Price:");
        this.price = Integer.parseInt(br.readLine());
        System.out.println("item Quantity");
        this.quant = Integer.parseInt(br.readLine());
        System.out.println("item Category");
        this.category = br.readLine();
//        System.out.println(this);
    }

    public void edit()throws IOException{
        System.out.println("Enter Edit Details");
        System.out.println("item Price:");
        this.price = Integer.parseInt(br.readLine());
        System.out.println("item Quantity");
        this.quant = Integer.parseInt(br.readLine());

    }

    public String getCat(){
        return this.category;
    }

    @Override
    public String toString(){
        String s = (this.id + " " + this.name + " " +this.price+ " " + this.quant+ " " + this.offer+ " " + this.category);
        return s;
    }
}
