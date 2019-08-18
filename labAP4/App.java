package labAP4;
//
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class App {

public static void main(String[] args) throws IOException {
//    System.out.println("HI");
        Company c = new Company();
        c.startCompany();
    }
}

class Company {
    private static double earning;
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
        merchs.add(new Merchant("jeff"));
        merchs.add(new Merchant("joseph"));
//        System.out.println(merchs.get(0).getName());
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
                        _User.printX(merch);
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
//                                merch.EditItems(1);
                                _User.helperX(merch,1);
                                break;
                            case 3:
                                _User.useSearch(merch);
                                break;
                            case 4:
                                _User.helperX(merch,2);
                                break;
                            case 5:
                                _User.seeReward(merch);
                                break;
                            case 6:
                                exitMerch = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    for (int i = 0; i < customers.size(); i++) {
                        System.out.println(i+1 + ") " + customers.get(i).getName());
                    }
                    int selCust = Integer.parseInt(br.readLine());
                    boolean exitCust = true;
                    while (exitCust) {
                        Customers cust = customers.get((selCust - 1));
                        _User.printX(cust);
                        int optCust = Integer.parseInt(br.readLine());
                        switch (optCust) {
                            case 1:
                                _User.useSearch(cust);
                                break;
                            case 2:
                                _User.helperX(cust,1);
                                break;
                            case 3:
                                _User.seeReward(cust);
                                break;
                            case 4:
                                _User.helperX(cust,2);
                                break;
                            case 5:
                                exitCust = false;
                                break;
                        }
                    }
                    break;


                case 3:
                    String[] a = br.readLine().split(" ");
                    int x = Integer.parseInt(a[1]);
                    if (a[0].equals("M")) {
                        //do something with a[1] in merchant class
                        Merchant merch = merchs.get(x);
                        System.out.println(merch);

                    } else {
                        //do something with a[1] in customer class
                        Customers cust = customers.get(x);
                        System.out.println(cust);
                    }
                case 4:
                    //show balance in companies account
                    System.out.println(earning);

                case 5:
                    exitAppFlag = false;

            }
        }


    }
    public void setEarning(double a){
        earning+=a;
    }
}

class Users{
    public Users(){

    }

    public void useSearch(UserX _user)throws IOException{
        _user.Search();
    }

    public void addItemX(UserX _user, Item itemX,int a)throws IOException{
        _user._helper(itemX,a);
    }
    public void helperX(UserX _user, int b)throws IOException{
        _user._helper(b);
    }


    public void seeReward(UserX _user)throws IOException{
        System.out.println(_user.seeReward());
    }

    public void printX(UserX _user){
        _user.printX();
    }


}


class Merchant implements UserX {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private int id;
    private int numSlots = 10;
    private double reward=0;
    public static ArrayList<ArrayList<Object>> shared = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> cats = new ArrayList<>();
    private static int countd;
    {
        countd += 1;
    }

    public Merchant(String _name) {
        this.name = _name;
        this.id = countd;
    }

//    public void printOpts(ArrayList<Merchant> merchs){
//        for (int i = 0; i < merchs.size(); i++) {
//            System.out.println(i+1 + ") " + (merchs.get(i)));
//        };
//    }
    public ArrayList<String> getCats(){
        return this.cats;
    }
    public void _helper(Object... values)throws IOException{

        if (values.length==2){
            Item x = (Item) values[0];
            this.addItem(x);
        }
        else if (values.length==1){
//            Item x = (Item) values[0];
            int x = (int) values[0];
            this.EditItems(x);
        }

    }

    public void addItem(Item itemx) {
        itemx.setMerchant(this);
//        System.out.println(itemx);
        String category = itemx.getCat();
        if (items.size() < numSlots) {
            items.add(itemx);
            boolean flagCat = false;
            for (int i=0;i<cats.size();i+=1){
                if (cats.get(i).equals(category)){
                    flagCat = true;
                    break;
                }
            }
            System.out.println(flagCat);
            if (!flagCat){
                cats.add(itemx.getCat());
            }
            System.out.println(itemx);
        }

        else {
            System.out.println("You dont have enough slots to add Item");
            return;
        }

        boolean flagCheck = false;
        for (int j=0;j<shared.size();j+=1){
            if (shared.get(j).get(0).equals(category)){
                flagCheck=true;
                break;
            }
        }

        if (!flagCheck){
            ArrayList<Object> adder= new ArrayList<>();
            adder.add(itemx.getCat());
            adder.add(itemx);
            shared.add(adder);
        }
        else{
            for (int x=0;x<shared.size();x++){
                if (shared.get(x).get(0).equals(itemx.getCat())){
                    shared.get(x).add(itemx);
                }
            }
        }
    }

    public void EditItems(int a)throws IOException{

        for (int i=0;i<items.size();i++){
            System.out.println((i+1)+")  " + items.get(i));
        }
        int n = Integer.parseInt(br.readLine());
        Item it = items.get(n-1);
        if (a==1) {
            it.edit();
        }
        else{
            it.addOffer();
        }
    }

    public String getName() {
        return this.name;
    }
    public void setReward(double a){
        reward+=a;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    @Override
    public void Search()throws IOException{
        for (int i=0;i<cats.size();i++){
            System.out.println(i+") "+ cats.get(i));
        }
        int x = Integer.parseInt(br.readLine());
        String s =  cats.get(x);
        for (int z=0;z<shared.size();z++){
            if (shared.get(x).get(0).equals(s)){
                for (int j=1;j<shared.get(x).size();j++){
                    System.out.println(shared.get(x).get(j));
                }
                break;
            }
        }

    }


    @Override
    public int seeReward() {
        int checkRe = (int)((2*this.reward)/100);
        this.numSlots+=checkRe;
        this.reward = this.reward%100;
        if (numSlots>10){
            return 10-this.numSlots;
        }
        else{
            return 0;
        }
//        return (10-this.numSlots);
    }

    //    @Override
    public void printX() {
        String s = String.join("\n",
                "Welcome " + this.getName(),
                "1) Add Item",
                "2) Edit Item",
                "3) Search by Category",
                "4) Add Offer",
                "5) Rewards won",
                "6) Exit");
        System.out.println(s);
    }

    @Override
    public String toString(){
        String s = String.join("\n",
                "Name: "+this.name,
                "Income: "+ (this.seeReward()*100+this.reward)
                );
        System.out.println(s);
        return s;
    }

}


class Customers implements UserX {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<ArrayList<Item>> bought = new ArrayList<>();
    private ArrayList<ArrayList<Object>> cart = new ArrayList<>();
    private ArrayList<ArrayList<Transact>> transacttions = new ArrayList<>();
    private String name;
    private int money=100;
    private int reward = 0;
    private int countReward = 0;
    private int id;
//    private int countd;

    private static int countd;

    {
        countd += 1;
    }

    public Customers(String _name) {
        this.name = _name;
        this.id = countd;
    }

    public void _helper(Object... values){
        int x = (int) values[0];
        if (x==1){
            this.checkout();
        }
        else{
            this.displayTransaction();
        }
    }

    public void checkout(){
        int x = 0;
        ArrayList<Transact> tx = new ArrayList<>();
        while (x<cart.size()){
            Item it = (Item) cart.get(x).get(0);
            int quant = (int) cart.get(x).get(1);
            int price = it.getPrice();
            String offer = it.getOffer();
            double cost =0;
            double merchReward = 0;
            if (offer!=null){
                if (offer.equals("buy one get one")){
                    cost = (price +price*0.005)*quant;
                    merchReward = quant*price*0.005;
                    if (it.getQuant()>2*quant) {

                        quant = 2 * quant;
                    }
                    else{
                        quant=it.getQuant();
                    }
                }
                else{
                    price -=0.25*price;
                    merchReward = quant*price*0.005;
                    cost = (price +price*0.005)*quant;
                }
            }

//            double finalPrice = price + price*0.005;
//            finalPrice = finalPrice*quant;
            if (cost>money+reward){
                System.out.println("do not have enough balance");
                break;
            }
            else{
                money -=cost;
                if (money<0){
                    reward+=money;
                    money=0;
                }
            }
            ArrayList<Item> itX = new ArrayList<>();

            Merchant merch = it.getMerch();
            merch.setReward(merchReward);
            Company comp = new Company();
            comp.setEarning(2*merchReward);
            itX.add(it);
            bought.add(itX);
            Transact txx = new Transact(it.getName(),merch.getName(),quant,cost);
            tx.add(txx);
            it.setQuant(quant);
            System.out.println("Successfully Bought");
            countReward+=1;
            x+=1;
        }
        transacttions.add(tx);
    }


    public void displayTransaction(){
        int x = 10;
        int z = transacttions.size();
        while (x>0 & z>0){
            ArrayList<Transact> t = transacttions.get(z-1);
            for (int i=0;i<t.size();i++){
                Transact tt = t.get(i);
                String s = ("Item: "+ tt.name+ " Quantity: "+ tt.quant+" Price: "+ tt.price+ " Merchant: " + tt.nameMerch);
                System.out.println(s);
                x-=1;
            }
            z-=1;

        }
    }
    public String getName() {
        return this.name;
    }

    @Override
    public void Search()throws IOException {
        Merchant merchX = new Merchant("Rand");
        ArrayList<ArrayList<Object>> sharedList = merchX.shared;
        for (int i=0;i<sharedList.size();i++){
            System.out.println(i+") "+sharedList.get(i).get(0));
        }
        int x = Integer.parseInt(br.readLine());
        ArrayList<Object> selected = sharedList.get(x);
        for (int j=1;j<selected.size();j++){
            System.out.println(j+") "+selected.get(j));
        }
        boolean checkEx = true;
        while (checkEx) {
            System.out.println("Enter Code");
            int code = Integer.parseInt(br.readLine());
            if (code+1 > selected.size()) {
                System.out.println("Wrong Code For the Product");
                checkEx = false;
                break;
            }
            Item it = (Item) selected.get(code);
            System.out.println("Enter Quantity");
            int quantity = Integer.parseInt(br.readLine());
            if (quantity>it.getQuant()){
                System.out.println("Not in stock Sorry");
                break;
            }
//            this.printMenu();
            this.printMenu();
            int opt = Integer.parseInt(br.readLine());
            if (opt==1){
//                this.transact(it,quantity);
                ArrayList<Object> z = new ArrayList<>();
                z.add(it);
                z.add(quantity);
                cart.add(z);

                this.checkout();
                checkEx = false;
            }
            else if (opt==2){
                ArrayList<Object> z = new ArrayList<>();
                z.add(it);
                z.add(quantity);
                cart.add(z);
            }
            else{
                checkEx = false;
            }

        }

    }

    public int getNumTransactions(){
        int count = 0;
        for (int i=0;i<transacttions.size();i+=1){
            ArrayList<Transact> t = transacttions.get(i);
            count+=t.size();
        }
        return count;
    };

    @Override
    public int seeReward() {
        int val = countReward/5;
        this.reward+=(val*10);
        int subVal = countReward%5;
        countReward= subVal;
        return this.reward;

    }
    public void printMenu(){
        String s = String.join("\n",
               "1) Buy item",
                "2) Add item to cart",
                "3) Exit");
        System.out.println(s);
    }

    public void printX() {
        String s = String.join("\n",
                "Welcome " + this.name,
                "1) Search Item",
                "2) Checkout Cart",
                "3) Reward Won",
                "4) print Latest Order",
                "5) Exit");
        System.out.println(s);
    }

    public String toString(){
        String s = String.join("\n",
                "Name: "+ this.name,
                "numTransactions: "+ this.getNumTransactions()
                );
        return s;
    }

    class Transact{
        public String name;
        public int quant;
        public double price;
        public String nameMerch;

        public Transact(String n, String nM, int q, double price){
            this.name = n;
            this.quant = q;
            this.price = price;
            this.nameMerch = nM;
        }

    }
}

class Item {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private Merchant merch;
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

    public void addOffer()throws IOException{
        System.out.println("Choose Offer");
        System.out.println("1) buy one get one");
        System.out.println("2) 25% off");
        int x = Integer.parseInt(br.readLine());
        if(x==1){
            this.offer = "buy one get one";
        }
        else{
            this.offer= "25% off";
        }
        System.out.println(this.offer);
    }

    public String getOffer(){return this.offer;}
    public Merchant getMerch(){return this.merch;}
    public void setMerchant(Merchant merch){this.merch = merch;}
    public String getCat(){
        return this.category;
    }
    public int getPrice(){return this.price;}
    public void setQuant(int a){this.quant-=a;}
    public int getQuant(){ return this.quant;}
    @Override
    public String toString(){
        String s = (this.id + " " + this.name + " " +this.price+ " " + this.quant+ " " + this.offer+ " " + this.category);
        return s;
    }
}
