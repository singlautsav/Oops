package labAP6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.FindException;
import java.util.*;
//import labAP5.Xonster;


class App {
    public static void main(String[] args)throws IOException,CloneNotSupportedException {
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
    boolean skActive;
    ArrayList[] al = new ArrayList[11];
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

    public void run()throws IOException,CloneNotSupportedException {

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

    public void StartGame(Xero x, Graph g, int num) throws  IOException,CloneNotSupportedException{
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
        if (skActive) {
            System.out.println("Do you want sideKick |yes/no|");
            String i = br.readLine();
            if (i.equals("yes")) {
                SideKick s = x.getSideKick();
                String a;
                System.out.println("Your selected SideKick is " + s.getName());
                if (s.getName().equals("Minion")) {
                    System.out.println("type c to clone & f to fight");
                    a = br.readLine();
                } else if (s.getName().equals("Knight")) {
                    if (m.getlev() != 2) {
                        System.out.println("Wont be able to use Special Power");
                        a = "";
                    } else {
                        System.out.println("type c to Use Special Power & f to fight");
                        a = br.readLine();
                    }
                } else {
                    a = "";
                }

                if (a.equals("c")) {
                    h.setSideKick(x);
                }

            }
        }
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
                h.incxp(x);
                SideKick ll = h.getSideKick(x);
                ll.levup();
                skActive = specialActive(x);
                h.reset(x);
//                specialActive();
                StartGame(x,g,l);
            }
        }
    }

    private boolean specialActive(Xero _xero)throws IOException{
        HeroX h = new HeroX();
        SideKick x;
        System.out.println("If you'd like to buy side kick, type yes else no to upgrade level");
        String s = br.readLine();
        if (s.equals("yes")){
            System.out.println("For Minion, Press 1) ");
            System.out.println("For Knight, Press 2) ");
            int choice = Integer.parseInt(br.readLine());
            System.out.println("XR to spend");
            int xr = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    if (h.getxp(_xero)>=5){
                        x = new Minions(xr);
                        h.addSide(_xero,x);
                    }
                    else{
                        System.out.println("insufficient XP");
                    }
                    break;
                case 2:
                    if(h.getxp(_xero)>=8){
                        x = new Knight(xr);
                        h.addSide(_xero,x);
                    }
                    else{
                        System.out.println("insufficient XP");
                    }
                    break;
            }
            return true;

        }
        else{
            h.levelup(_xero);
            return false;
        }

    }

    private void CreateHero()throws IOException {
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

    private void createGraph(){

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

    public void incxp(Xero _hero){
        _hero.incXp();
    }

    public int getxp(Xero _hero){
        return _hero.getxp();
    }

    public void addSide(Xero _hero, SideKick s){
        _hero.addSide(s);
    }

    public void reset(Xero _hero){
        _hero.reset();
    }

    public SideKick getSideKick(Xero _hero){
        return _hero.getSideKick();
    }

    public void setSideKick(Xero _hero) throws CloneNotSupportedException{
        _hero.setSideKick();
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



abstract class Hero{
    protected int level = 1;
//    protected int countMoves = 0;
    protected int xp = 25;
    protected int hp = 100;
    private int prevHp = 100;
//    protected int temphp;
    protected int z;
    protected int a;
    protected boolean A;
    protected boolean D;
    protected boolean SpecialActive;
    protected boolean canActivate;
    protected int moveX = 0;
    protected int specialMoveCounter = 0;

//    protected boolean isSideKickActive;
    protected ArrayList<SideKick> sk;
    protected ArrayList<SideKick> skSelected;
//    protected Graph graph = new Graph(10);
{
    sk = new ArrayList<>();
    skSelected = new ArrayList<>();
}


    public int getHP(){
        return this.hp;
    }
    public void setHp(int x){
        this.hp = x;
    }

    public void incXp(){
        this.xp+=20;
        System.out.println("20 XP awarded");
    }
    public int getxp(){
        return  this.xp;
    }
    public void Attack(Xonster m)throws IOException{
//        System.out.println("SpecialActive "+ SpecialActive + " CanActivate " + canActivate + " num Moves " + moveX+ " Special counter "+ specialMoveCounter);
        if (SpecialActive){
            this.SpecialPower(m);
        }
        int mhp = m.gethp();
        m.sethp(mhp-z);
        System.out.println("You attacked and inflicted "+ z + " damage");

        if (A){
            for (SideKick l:skSelected) {
                l.attack(m);
            }
        }
        System.out.println("Your hp" +getHP()+ "Monsters: "+ m.gethp());
        int att = m.attack();
        if (att<0){
            att=0;
        }
        this.hp -= att;
        System.out.println("Monster Inflicted "+ att + " damage");
        System.out.println("Your hp" +getHP()+ "Monsters: "+ m.gethp());

        int attx = (int) Math.round(1.5*att);
        System.out.println(skSelected.size());
        if (skSelected.size()!=0){
            for (SideKick s:skSelected) {
                s.setHp(attx);
                System.out.println("SideKicks HP: "+  s.getHp());
            }
            if(skSelected.get(0).getHp()<=0){
                skSelected.clear();
                sk.remove(sk.size()-1);
            }
        }

        moveX+=1;
//        moveX+=1;
        if (moveX>2){
            canActivate = true;
        }
        if (moveX==3 && SpecialActive){
            moveX = 0;
        }

    }
    public void Defense(Xonster m) throws IOException {
//        System.out.println("SpecialActve "+ SpecialActive + " CanActivate " + canActivate + " num Moves " + moveX + " Special counter "+ specialMoveCounter);
        if (SpecialActive){
            this.SpecialPower(m);
        }
        System.out.println("You chose to defend");
        int k = 0;
        if (D){
//            k = 5;
        }
        int kll = k+this.a;
        System.out.println("Monster Attack reduced by " + (kll));
        int l = m.attack();
        if (l>a){
            l -=(kll);
        }
        else{
            l =0;
        }
        this.hp-=l;
        System.out.println("Your Hp: " + getHP() + " Moster: " + m.gethp());
        moveX+=1;
        if (moveX>2){
            canActivate = true;
        }
        if (moveX>3 && SpecialActive){
            moveX = 0;
        }

    }

    public boolean getCount(){
        return this.canActivate;
    }
//    public  void restore(){
//    }
    public abstract void SpecialPower(Object o)throws IOException;
    public void levelup(){
//        this.xp+=20;
        this.hp = prevHp +50;
        this.prevHp = this.hp;
        this.level +=1;
        System.out.println("Level Up");
    }

    public void addSide(SideKick s){
        sk.add(s);
    }

    public void reset(){
        moveX = 0;
        specialMoveCounter = 0;
        SpecialActive = false;
        canActivate = false;
        hp = prevHp;
        skSelected.clear();
        A=false;
        D=false;
    }

    public SideKick getSideKick(){
        Collections.sort(sk,new Comparator<SideKick>(){
            @Override
            public int compare(SideKick s1, SideKick s2){
                return Integer.compare(s1.getXp(),s2.getXp());
            }
        });

        skSelected.add(sk.get(sk.size()-1));
        return skSelected.get(0);
    }

    public void setSideKick()throws CloneNotSupportedException{
        Minions m = new Minions(2);
        Knight k = new Knight(2);
        SideKick s =  skSelected.get(0);
        if (s.equals(m) && !s.isUsed){
            this.A = true;
            s.isUsed = true;
            Minions a = (Minions) s;
            skSelected.add(a.clone());
            skSelected.add(a.clone());
            skSelected.add(a.clone());
            System.out.println("Cloned");
        }
        else if(s.equals(k) && !s.isUsed){
            s.isUsed=true;
            this.D = true;
//            this.a+=5;
        }
        else{
            System.out.println("No Effect");

        }
    }
}

class Warrior extends Hero implements Xero {
    private final String userName;
//    boolean activatePower;
    { this.z = 10;
    this.a = 3; }

    @Override
    public void setSideKick()throws CloneNotSupportedException {
        super.setSideKick();
    }

    @Override
    public SideKick getSideKick() {
        return super.getSideKick();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void addSide(SideKick s) {
        super.addSide(s);
    }

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
        if (!this.SpecialActive && super.canActivate){
            this.a +=5;
            this.z +=5;
//            this.activatePower = true;
            super.SpecialActive = true;
            super.canActivate = false;
        }
        else if (this.specialMoveCounter==3){
            this.a-=5;
            this.z-=5;
//            this.activatePower=false;
            this.specialMoveCounter = 0;
            super.SpecialActive = false;
            super.canActivate=true;
        }
        else if (super.SpecialActive){
            this.specialMoveCounter+=1;
        }
    }

    @Override
    public String getName(){
        return this.userName;
    }

    @Override
    public void incXp() {
        super.incXp();
    }

    @Override
    public int getxp() {
        return super.getxp();
    }
}

class Mage extends Hero implements Xero {
    private final String userName;
//    public boolean SpecialActive;

    {this.z = 5;
    this.a = 5;}
    public Mage(String s){
        this.userName = s;
    }

    @Override
    public void setSideKick()throws CloneNotSupportedException {
        super.setSideKick();
    }

    @Override
    public SideKick getSideKick() {
        return super.getSideKick();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void addSide(SideKick s) {
        super.addSide(s);
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
//        this.SpecialActive = true;
        Monster x = (Monster) o;
        if (!super.SpecialActive && super.canActivate){
            super.SpecialActive = true;
            int att = x.attack();
            System.out.println("Monster Inflicted " + att + " damage");
            System.out.println("Your hp" + super.getHP() + "Monsters: " + x.gethp());
            super.canActivate = false;
//            this.specialMoveCounter += 1;
        }
        else if(this.specialMoveCounter<3) {
            x.sethp((int) Math.round(x.gethp() - 0.05 * (x.gethp())));
            System.out.println("Reduced Monsters Damage by 5%");
            this.specialMoveCounter+=1;
        }
        else if (this.specialMoveCounter==3){
            super.SpecialActive=false;
            super.specialMoveCounter = 0;
            super.canActivate = true;

        }
    }

    @Override
    public String getName(){
        return this.userName;
    }

    @Override
    public void incXp() {
        super.incXp();
    }

    @Override
    public int getxp() {
        return super.getxp();
    }


}

class Thief extends Hero implements Xero {
    private final String userName;
//    public boolean activatePower;
    {this.z = 6;
    this.a = 4;
    this.hp = 100;}
    public Thief(String name){
        this.userName = name;
    }

    @Override
    public void setSideKick()throws CloneNotSupportedException {
        super.setSideKick();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public SideKick getSideKick() {
        return super.getSideKick();
    }

    @Override
    public void addSide(SideKick s) {
        super.addSide(s);
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
//        this.SpecialActive = true;
        if (!this.SpecialActive && super.canActivate){
            this.SpecialActive=true;
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
            super.canActivate = false;
        }
        else if (this.specialMoveCounter==3){
//            this.activatePower=false;
            this.specialMoveCounter = 0;
            super.SpecialActive = false;
            super.canActivate=true;
        }
        else{
            this.specialMoveCounter+=1;
        }

    }

    @Override
    public String getName(){
        return this.userName;
    }

    @Override
    public void incXp() {
        super.incXp();
    }

    @Override
    public int getxp() {
        return super.getxp();
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
    public SideKick getSideKick() {
        return super.getSideKick();
    }

    @Override
    public void setSideKick()throws CloneNotSupportedException {
        super.setSideKick();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void addSide(SideKick s) {
        super.addSide(s);
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
        Monster x = (Monster) o;
        if (!SpecialActive){
            SpecialActive = true;
            int att = x.attack();
            System.out.println("Monster Inflicted " + att + " damage");
            System.out.println("Your hp" + super.getHP() + "Monsters: " + x.gethp());
            this.canActivate = false;
//            this.specialMoveCounter += 1;
        }
        else if(this.specialMoveCounter<3) {
            this.setHp((int) Math.round(this.getHP() + 0.05 * (this.getHP())));
            System.out.println("HP inc by 5%");
            this.specialMoveCounter+=1;

        }
        else if (this.specialMoveCounter==3){
            SpecialActive=false;
            this.canActivate = true;
        }
    }


    @Override
    public String getName(){
        return this.userName;
    }

    @Override
    public void incXp() {
        super.incXp();
    }

    @Override
    public int getxp() {
        return super.getxp();
    }


}




abstract class SideKick implements Cloneable{
    boolean isUsed;
    protected int xp;
    protected int hp;
    protected int a;
    protected int z;
    public String name;

    public void attack(Xonster m){
        int mhp = m.gethp();
        m.sethp(mhp-this.a);
        System.out.println("SideKick Attacked and inflicted a damage "+this.a+" to the monster");
        System.out.println("SideKick HP" + this.hp);
    };

    public void levup(){
        this.xp+=2;
        this.a +=1;
        this.hp =100;
        System.out.println("Yayy Level Up");
    }

    public int getXp(){
        return this.xp;
    }

    public String getName(){
        return  this.name;
    }

    public int getHp(){
        return this.hp;
    }
    public void setHp(int a){
        this.hp -=a;
    }

    @Override
    public boolean equals(Object o1){
        if (o1!=null && getClass()==o1.getClass()){
            SideKick s = (SideKick) o1;
            return (s.getName().equals(this.getName()));
        }
        else {
            return false;
        }
    }

    public SideKick clone()throws CloneNotSupportedException{
        SideKick p = (SideKick) super.clone();
        return p;
    }

}

class Minions extends SideKick implements Cloneable{
    {
        this.name = "Minion";
    }

//    {int z = 8}
    public Minions(int v){
        this.xp = 5;
        this.xp += v;
        this.a = 1+ (int) Math.round(v*0.5);
        this.hp =100;
    }

    @Override
    public void attack(Xonster m) {
        super.attack(m);
    }

    @Override
    public void levup() {
        super.levup();
    }

    @Override
    public int getXp() {
        return super.getXp();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setHp(int a) {
        super.setHp(a);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public boolean equals(Object o1){
        if (o1!=null && getClass()==o1.getClass()){
            Minions o = (Minions) o1;
            return (super.equals(o1) && o.getName().equals(this.name));
        }
        else {
            return false;
        }
    }

    public Minions clone(){
        try{
            Minions copy = (Minions) super.clone();
            return copy;
        }catch (CloneNotSupportedException e){
            return null;
        }
    }
}

class Knight extends SideKick implements Cloneable{
    {
        this.name = "Knight";
    }

    public Knight(int v){
        this.xp = 8;
        this.xp += v;
        this.a = 2+ (int) Math.round(v*0.5);
        this.hp =100;
    };

    @Override
    public void setHp(int a) {
        super.setHp(a);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void attack(Xonster m) {
        super.attack(m);
    }

    @Override
    public void levup() {
        super.levup();
    }

    @Override
    public int getXp() {
        return super.getXp();
    }

    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public boolean equals(Object o1){
        if (o1!=null && getClass()==o1.getClass()){
            Knight o = (Knight) o1;
            return (super.equals(o1) && o.getName().equals(this.name));
        }
        else {
            return false;
        }
    }

    public Knight copy(){
        try {
            Knight copy = (Knight) super.clone();
            return copy;
        }catch (CloneNotSupportedException e){
            return null;
        }
    }

}