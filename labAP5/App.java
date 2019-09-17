package labAP5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
//import labAP5.Xonster;


class App {
    public static void main(String[] args)throws IOException {
        Game g = new Game();
        g.run();
    }

}
class Graph{

    public Xonster randomMonster(){
        Random r = new Random();
        int z = r.nextInt(3);
        if (z==1){
            Xonster a = new Goblin();
            return a;
        }
        else if(z==2){
            Xonster a = new Zombies();
            return a;
        }
        else{
            Xonster a = new Fiends();
            return a;
        }
    }
    Monster m;
    int size = 0;

    public int L;
    public Node[] arr;

    public class Node{
        int data;
        int distance;
        Xonster monsterX;
        Node next;
        Node(int data){
            this.monsterX = randomMonster();
            this.data = data;
            next = null;
        }
        Node (int data, Xonster m ){
            this.monsterX = m;
            this.data = data;
            next = null;
        }
    }

    Graph(int L){
        this.L = L;
        arr = new Node[L+1];
        for(int i=1;i<L;i++){
            Node n = new Node(i);
            arr[i] = n;
        }
        Xonster x = new LionFang();
        Node n = new Node(10,x);
        arr[10] = n;
    }

    public void edge(int x,int y){
//        MonsterX z = new MonsterX();
        Node a = arr[x];
        Node b = arr[y];
    }
    public Node[] getArr(){
        return arr;
    }

    public Node search(int x){
        return arr[x];
    }
}

class Game{
    ArrayList[] al = new ArrayList[11];
    //    Monster g = new Monster();
//    Random r =  new Random();
//    HeroX _hero = new HeroX();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<ArrayList<Object>> players = new ArrayList<>();
    {
        createGraph();
    }
//    public void createGraph(Graph graph){
////        Xonster m = g.randomMonster();
//        Graph.Node a = new Graph.Node();
//        graph.edge(1,2,g.randomMonster());
//        graph.edge(1,4,g.randomMonster());
//        graph.edge(1,5,g.randomMonster());
//        graph.edge(1,6,g.randomMonster());
//        graph.edge(2,4,g.randomMonster());
//        graph.edge(2,5,g.randomMonster());
//        graph.edge(2,6,g.randomMonster());
//        graph.edge(2,3,g.randomMonster());
//        graph.edge(3,4,g.randomMonster());
//        graph.edge(3,5,g.randomMonster());
//        graph.edge(3,6,g.randomMonster());
//        graph.edge(4,5,g.randomMonster());
//        graph.edge(4,7,g.randomMonster());
//        graph.edge(4,8,g.randomMonster());
//        graph.edge(4,9,g.randomMonster());
//        graph.edge(5,9,g.randomMonster());
//        graph.edge(5,7,g.randomMonster());
//        graph.edge(5,8,g.randomMonster());
//        graph.edge(5,6,g.randomMonster());
//        graph.edge(6,7,g.randomMonster());
//        graph.edge(6,8,g.randomMonster());
//        graph.edge(6,9,g.randomMonster());
//        graph.edge(7,8,g.randomMonster());
//        graph.edge(9,8,g.randomMonster());
//        Xonster lf1 = new LionFang();
//        Xonster lf2 = new LionFang();
//        Xonster lf3 = new LionFang();
//
//        graph.edge(9,10,lf1);
//        graph.edge(9,10,lf2);
//        graph.edge(9,10,lf3);
//
//    }

    public void Menu(){
        String s = String.join("\n",
                "Welcome to ArchLegends",
                "Choose your Option",
                "1) New User",
                "2) Existing User",
                "3) Exit");
        System.out.println(s);
    }


    public Game(){
    }

    public void run()throws IOException {
        boolean isExiting = true;
        while(isExiting) {
            Menu();
            int n = Integer.parseInt(br.readLine());
            if (n == 1) {
                CreateHero();
            } else if (n == 2) {
                boolean playerFound=false;
                System.out.println("Enter Username");
                String s = br.readLine();
                for (int i = 0;i<players.size();i++){
                    String xs = (String)players.get(i).get(1);
                    if (xs.equals(s)){
                        Xero x = (Xero) players.get(i).get(0);
                        Graph g = (Graph) players.get(i).get(2);
                        StartGame(x,g,-1);
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound){
                    System.out.println("No user Found");
                }
            }
            else if (n == 3) {
                isExiting = false;
            }
        }

    }
    public void StartLocation(){
        String s = String.join("\n",
                "You are at starting Location. Choose Path:",
                "1) Go to Location 1 ",
                "2) Go to Location 2 ",
                "3) Go to Location 3",
                "Enter -1 to Exit");
        System.out.println(s);
    }

    public void displayFightMenu(){
        String s = String.join("\n",
                "Choose Move",
                "1) Attack",
                "2) Defense",
                "3) Special Attack");
        System.out.println(s);
    }
    public void winMenu(int z){
        System.out.println("You are at"+z+" Location. Choose Path:");
        for (int i = 0;i<al[z].size();i++){
            System.out.println(i+") Go to location "+ (int) al[z].get(i));
        }
        System.out.println("Enter -1 to Exit");
    }

    public void StartGame(Xero x, Graph g, int num) throws  IOException{
        HeroX h = new HeroX();
        Graph.Node[] arr = g.getArr();
        boolean checkx = true;
        Xonster m;
        int l;
        if (num==-1) {
            StartLocation();
            int a = Integer.parseInt(br.readLine());
            if (a==-1){
                checkx=false;
                a = 1;
                m = arr[a].monsterX;
                l=a;
            }
            else {
                m = arr[a].monsterX;
                l = a;
            }
        }
        else{
            winMenu(num);
            int a = Integer.parseInt(br.readLine());
            if (a==-1){
                checkx=false;
                a = 1;
                l = (int) al[num].get(a);
                m = arr[l].monsterX;

            }
            else {
                l = (int) al[num].get(a);
                m = arr[l].monsterX;
            }
        }
        System.out.println("You are fighting with Monster of " + m.getlev());
        int hpDef = m.gethp();
        while (checkx){
            displayFightMenu();
            int z = Integer.parseInt(br.readLine());
            if (z==1){
                h.Attack(x,m);
            }
            else if(z==2){
                h.Defense(x,m);
            }
            else{
                h.SpecialCheck(x,m);
            }
            if (h.getHP(x)<=0){
                System.out.println("You Lost Sorry");
                break;
            }
            else if(m.gethp()<=0){
                System.out.println("We won!!!!");
                m.sethp(hpDef);
                h.levelup(x);
                StartGame(x,g,l);
            }
        }
    }


    public void CreateHero()throws IOException {
        String s = String.join("\n",
                "Choose a Hero",
                "1) Warrior",
                "2) Thief",
                "3) Mage",
                "4) Healer");
        System.out.println("Enter Username");
        String name = br.readLine();
        System.out.println(s);
        int n = Integer.parseInt(br.readLine());
        switch (n) {
            case 1:
                Hero a = new Warrior(name);
                ArrayList<Object> x = new ArrayList<>();
                x.add(a);
                x.add(name);
                Graph g = new Graph(10);
//                createGraph(g);
                x.add(g);
                players.add(x);
                System.out.println("Hi "+name+ " You are type Warrior, Log in to play. Exiting");
                break;
            case 2:
                Hero b = new Thief(name);
                ArrayList<Object> y = new ArrayList<>();
                y.add(b);
                y.add(name);
                Graph g2 = new Graph(10);
//                createGraph(g2);
                y.add(g2);
                players.add(y);
                System.out.println("Hi "+name+ " You are type Thief, Log in to play. Exiting");
                break;
            case 3:
                Hero c = new Mage(name);
                ArrayList<Object> z = new ArrayList<>();
                z.add(c);
                z.add(name);
                Graph g3 = new Graph(10);
//                createGraph(g3);
                z.add(g3);
                players.add(z);
                System.out.println("Hi "+name+ " You are type Mage, Log in to play. Exiting");
                break;
            case 4:
                Hero d = new Healer(name);
                ArrayList<Object> w = new ArrayList<>();
                w.add(d);
                w.add(name);
                Graph g4 = new Graph(10);
//                createGraph(g4);
                w.add(g4);
                players.add(w);
                System.out.println("Hi "+name+ " You are type Healer, Log in to play. Exiting");
                break;
        }

    }

    public void createGraph(){

        int n = 11;
        for (int i=0;i<n;i++){
            al[i]= new ArrayList<>();
        }

        al[1].add(4);al[1].add(5);al[1].add(6);
        al[2].add(4);al[2].add(5);al[2].add(6);
        al[3].add(4);al[3].add(5);al[3].add(6);
        al[4].add(1);al[4].add(2);al[4].add(3);al[4].add(7);al[4].add(8);al[4].add(9);
        al[5].add(1);al[5].add(2);al[5].add(3);al[5].add(7);al[5].add(8);al[5].add(9);
        al[6].add(1);al[6].add(2);al[6].add(3);al[6].add(7);al[6].add(8);al[6].add(9);
        al[7].add(4);al[7].add(5);al[7].add(6);al[7].add(10);
        al[8].add(4);al[8].add(5);al[8].add(6);al[8].add(10);
        al[9].add(4);al[9].add(5);al[9].add(6);al[9].add(10);
        al[10].add(7);al[10].add(8);al[10].add(9);

    }

}


class HeroX{
    public HeroX(){
    }

    public void SpecialCheck(Xero _hero, Object o)throws IOException{

        if (!_hero.getCount()){
            System.out.println("Invalid Response");
        }
        else{
            _hero.SpecialPower(o);
        }
    }

    public void Defense(Xero _hero, Xonster m) throws IOException{
        _hero.Defense(m);
    }

    public void Attack(Xero _hero, Xonster m) throws IOException{
        _hero.Attack(m);
    }

    public int getHP(Xero _hero){
        return _hero.getHP();
    }

    public void levelup(Xero _hero){
        _hero.levelup();

    }
}


class Monster{
    Random r = new Random();
    protected int hp = 100;

    public int attack() {
        double val = r.nextGaussian() +(double) this.gethp()/8;
//        System.out.println(val);
        int finVal = (int) Math.round(val);
//        System.out.println(finVal);
//        int millisDelay = (int) Math.round(val);
        return finVal;
    }
    public void sethp(int a){
        this.hp = a;
    }
    public int gethp(){
        return this.hp;
    }

}


class Goblin extends Monster implements Xonster {

    public final int level = 1;
    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public int gethp() {
        return super.gethp();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }

    public int getlev(){ return this.level;}
}

class Zombies extends Monster implements Xonster {
    public final int level = 2;
    {this.hp = 150;}
    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }

    @Override
    public int gethp() {
        return super.gethp();
    }

    public int getlev(){ return this.level;}
}

class Fiends extends Monster implements Xonster {
    public final int level = 3;
    {this.hp = 200;}

    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public int gethp() {
        return super.gethp();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }

    public int getlev(){ return this.level;}
}

class LionFang extends Monster implements Xonster {
    public final int level = 4;
    {this.hp = 250;}
    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int gethp() {
        return super.gethp();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }

    public int getlev(){ return this.level;}

}




class Hero{
    //    private String userName;
    public Monster g;
    //    MonsterX x = new MonsterX();
    protected int countMoves = 0;
    protected int xp = 25;
    protected int hp = 100;
    protected int temphp;
    protected int z;
    protected int a;
    protected boolean SpecialActive;
    protected boolean canActivate;
    protected int moveX = 0;
//    protected Graph graph = new Graph(10);



    public int getHP(){
        return this.hp;
    }
    public void setHp(int x){
        this.hp = x;
    }

    public void Attack(Xonster m)throws IOException{
        int mhp = m.gethp();
        m.sethp(mhp-z);
        System.out.println("You attacked and inflicted "+ z + "damage");
        System.out.println("Your hp" +getHP()+ "Monsters: "+ m.gethp());
        int att = m.attack();
        if (att<0){
            att=0;
        }
        this.hp -= att;
        System.out.println("Monster Inflicted "+ att + " damage");
        System.out.println("Your hp" +getHP()+ "Monsters: "+ m.gethp());
        countMoves+=1;
        if (countMoves==3 && !SpecialActive){
            canActivate=true;
        }
        else if (SpecialActive && moveX<3){
            moveX+=1;
        }
        else if (moveX>=3){
            SpecialActive = false;
            countMoves=0;
            canActivate=false;
        }

    }
    public void Defense(Xonster m) throws IOException {
        System.out.println("You chose to defend");
        System.out.println("Monster Attack reduced by " + this.a);
        int l = m.attack();

        if (l>a){
            l -=a;
        }
        else{
            l =0;
        }
        this.hp-=l;
        System.out.println("Your Hp: " + getHP() + " Moster: " + m.gethp());
        countMoves+=1;
        if (countMoves>=3 && !SpecialActive){
            canActivate=true;
        }
        else if (SpecialActive && moveX<3){
            moveX+=1;
        }
        else if (moveX>=3){
            SpecialActive = false;
            countMoves=0;
            canActivate=false;
        }

    }

    public boolean getCount(){
        return this.canActivate;
    }
    public  void restore(){
    }
    public void SpecialPower(Object o)throws IOException {
    }
    public void levelup(){
        this.xp+=25;
        this.hp = 100;
        System.out.println("Level Up");
        System.out.println("25 XP awarded");
    }
}

class Warrior extends Hero implements Xero {
    private final String userName;
    { this.z = 10;
        this.a = 3; }

    @Override
    public void levelup() {
        super.levelup();
        this.z+=1;
        this.a+=1;
    }

    public Warrior(String x){
        this.userName=x;
    }
    @Override
    public void Attack(Xonster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Xonster m) throws IOException {
        super.Defense(m);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public void setHp(int x) {
        super.setHp(x);
    }

    @Override
    public boolean getCount() {
        return super.getCount();
    }

    @Override
    public void SpecialPower(Object o) throws IOException {
        this.SpecialActive = true;
        this.a+=5;
        this.z +=5;
        this.countMoves+=1;
    }

    @Override
    public void restore(){
        this.a-=5;
        this.z -=5;
        this.moveX=0;
        this.SpecialActive=false;
    }

    @Override
    public String getName(){
        return this.userName;
    }
}

class Mage extends Hero implements Xero {
    private final String userName;

    {this.z = 5;
        this.a = 5;}
    public Mage(String s){
        this.userName = s;
    }
    @Override
    public void Attack(Xonster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Xonster m) throws IOException {
        super.Defense(m);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public void setHp(int x) {
        super.setHp(x);
    }
    @Override
    public boolean getCount() {
        return super.getCount();
    }

    @Override
    public void levelup() {
        super.levelup();
        this.z+=1;
        this.a+=1;
    }
    //    @Override
    public void SpecialPower(Object o) throws IOException {
        this.SpecialActive = true;
        Monster x = (Monster) o;
        x.sethp((int) Math.round(x.gethp()-0.05*(x.gethp())));
        System.out.println("Reduced Monsters Damage by 5%");
        int att = x.attack();
        System.out.println("Monster Inflicted "+ att + " damage");
        System.out.println("Your hp" +super.getHP()+ "Monsters: "+ x.gethp());
        this.countMoves+=1;
    }

    @Override
    public void restore(){
        this.SpecialActive=false;
        this.moveX=0;
    }

    @Override
    public String getName(){
        return this.userName;
    }

}

class Thief extends Hero implements Xero {
    private final String userName;
    {this.z = 6;
        this.a = 4;
        this.hp = 100;}
    public Thief(String name){
        this.userName = name;
    }
    @Override
    public void Attack(Xonster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Xonster m) throws IOException {
        super.Defense(m);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public void setHp(int x) {
        super.setHp(x);
    }
    @Override
    public boolean getCount() {
        return super.getCount();
    }

    @Override
    public void levelup() {
        super.levelup();
        this.z+=1;
        this.a+=1;
    }

    @Override
    public void SpecialPower(Object o) throws IOException {
        this.SpecialActive = true;
        Monster x = (Monster) o;
        int mhp = x.gethp();
        int hpx = this.getHP();
        int l =(int) Math.round(0.3*mhp);
        this.setHp(hpx+l);
        x.sethp((int) (mhp-(0.3*mhp)));
        System.out.println("Stolen "+ l +" HP");
        System.out.println("Your Hp: "+ this.getHP() +" Monster: "+ x.gethp());
        int att = x.attack();
        if (att<0){
            att=0;
        }
        this.hp -= att;
        System.out.println("Monster Inflicted "+ att + " damage");
        System.out.println("Your hp" +super.getHP()+ "Monsters: "+ x.gethp());
        this.SpecialActive = false;
        this.canActivate = false;
        this.countMoves+=1;

    }
    @Override
    public void restore(){
//        this.hp -= 0.05*hp;
        this.SpecialActive=false;
        this.moveX=0;
    }

    @Override
    public String getName(){
        return this.userName;
    }

}

class Healer extends Hero implements Xero {
    private final String userName;
    {this.z = 4;
        this.a = 8;}
    public Healer(String s){
        this.userName = s;
    }
    @Override
    public void Attack(Xonster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Xonster m) throws IOException {
        super.Defense(m);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public void setHp(int x) {
        super.setHp(x);
    }

    @Override
    public void levelup() {
        super.levelup();
        this.z+=1;
        this.a+=1;
    }

    @Override
    public boolean getCount() {
        return super.getCount();
    }

    //    @Override
    public void SpecialPower(Object o) throws IOException {
        this.SpecialActive = true;
        this.hp+=(int)Math.round(0.5*this.hp);
        System.out.println("hp increased by" + 0.05*hp);
        this.countMoves+=1;
    }
    @Override
    public void restore(){
        this.SpecialActive=false;
//        this.hp -= 0.05*hp;
        this.moveX=0;
    }
    @Override
    public String getName(){
        return this.userName;
    }

}





