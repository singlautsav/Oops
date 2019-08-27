package labAP5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;


class App {
    public static void main(String[] args)throws IOException {
        Game g = new Game();
        g.run();
    }

}
class Graph{
    int size = 0;

    public int L;
    public Node[] arr;

    public class Node{
        int data;
        int distance;
        monster monsterX;
        Node next;
        Node(int data){
            this.data = data;
            next = null;
        }
    }

    Graph(int L){
        this.L = L;
        arr = new Node[L];
        for(int i=0;i<L;i++){
            Node n = new Node(i);
            arr[i] = n;
        }
    }

    public void edge(int x,int y, monster m){
        MonsterX z = new MonsterX();
        Node n = new Node(y);
        Node n1 = arr[x];
        while(n1.next!=null){
            n1 = n1.next;
        }
        n1.next = n;
        n.distance = z.getLevel(m);
        n.monsterX = m;

        Node nn = new Node(x);
        Node nn1 = arr[y];
        while(nn1.next!=null){
            nn1 = nn1.next;
        }
        nn1.next = n;
        nn.distance = z.getLevel(m);
        nn.monsterX = m;
    }
}

class Game{
    Random r =  new Random();
    HeroX _hero = new HeroX();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Hero> players = new ArrayList<>();


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
        Menu();

        int n  = Integer.parseInt(br.readLine());
        if (n==1){
            Hero x = new Hero();
        }
        else if (n==2){
            //Use old Player
        }
        else if (n==3){
            return;
        }

    }
    public void CreateHero()throws IOException {
        String name = br.readLine();

    }

    public monster randomMonster(){
        int z = r.nextInt(3);
        if (z==1){
            monster a = new Goblin();
            return a;
        }
        else if(z==2){
            monster a = new Zombies();
            return a;
        }
        else{
            monster a = new Fiends();
            return a;
        }
    }
}


class HeroX{
    public HeroX(){
    }

    public void SpecialCheck(hero _hero, Object o)throws IOException{
        int x = _hero.getCount();
        if (x%3!=0){
            System.out.println("Invalid Response");
        }
        else{
            _hero.SpecialPower(o);
        }
    }

    public void Defense(hero _hero, monster m) throws IOException{
        _hero.Defense(m);
    }
}

class MonsterX{
    public MonsterX(){
    }

    public int getHP(monster m){
        return m.gethp();
    }

    public void setHP(monster m, int a){
        m.sethp(a);
    }

    public int Attack(monster m){
        return m.attack();
    }

    public int getLevel(monster m){
        return m.getlev();
    }
}


class Monster{
    Random r = new Random();
    protected int hp = 100;

    public int attack() {
        double val = r.nextGaussian() +(double) gethp()/8;
        int finVal = (int) Math.round(val);
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


class Goblin extends Monster implements monster{

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

class Zombies extends Monster implements monster{
    public final int level = 1;
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

class Fiends extends Monster implements monster{
    public final int level = 2;
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

class LionFang extends Monster implements monster{
    public final int level = 1;
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
    Game g =  new Game();
    MonsterX x = new MonsterX();
    protected int countMoves = 0;
    protected int xp = 0;
    protected int hp = 100;
    protected int temphp;
    protected int z;
    protected int a;
//    protected boolean SpecialActive;
    protected int moveX = 0;
    protected Graph graph = new Graph(10);

    public void createGraph(){
//        monster m = g.randomMonster();
        graph.edge(1,2,g.randomMonster());
        graph.edge(1,4,g.randomMonster());
        graph.edge(1,5,g.randomMonster());
        graph.edge(1,6,g.randomMonster());
        graph.edge(2,4,g.randomMonster());
        graph.edge(2,5,g.randomMonster());
        graph.edge(2,6,g.randomMonster());
        graph.edge(2,3,g.randomMonster());
        graph.edge(3,4,g.randomMonster());
        graph.edge(3,5,g.randomMonster());
        graph.edge(3,6,g.randomMonster());
        graph.edge(4,5,g.randomMonster());
        graph.edge(4,7,g.randomMonster());
        graph.edge(4,8,g.randomMonster());
        graph.edge(4,9,g.randomMonster());
        graph.edge(5,9,g.randomMonster());
        graph.edge(5,7,g.randomMonster());
        graph.edge(5,8,g.randomMonster());
        graph.edge(5,6,g.randomMonster());
        graph.edge(6,7,g.randomMonster());
        graph.edge(6,8,g.randomMonster());
        graph.edge(6,9,g.randomMonster());
        graph.edge(7,8,g.randomMonster());
        graph.edge(9,8,g.randomMonster());

    }

    public int getHP(){
        return this.hp;
    }
    public void setHp(int x){
        this.hp = x;
    }

    public void Attack(monster m)throws IOException{
        int mhp = x.getHP(m);
        x.setHP(m,mhp-z);
        countMoves+=1;
        moveX+=1;
        if (moveX==3){
            this.hp = this.temphp;
        }
    }
    public void Defense(monster m) throws IOException {
        int z = x.Attack(m);

        if (z>a){
            z = z-a;
        }
        else{
            z =0;
        }
        this.hp-=z;
        moveX+=1;
        if (moveX==3){
            restore();
        }
    }

    public int getCount(){
        return this.countMoves;
    }
    public void restore(){
    }
}

class Warrior extends Hero implements hero{
    { this.z = 10;
    this.a = 3;}

    @Override
    public void Attack(monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(monster m) throws IOException {
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
    public int getCount() {
        return super.getCount();
    }

    @Override
    public void SpecialPower(Object o) throws IOException {
        this.a+=5;
        this.z +=5;
//        this.countMoves+=1;
    }

    @Override
    public void restore(){
        this.a-=5;
        this.z -=5;
        this.moveX=0;
    }
}

class Mage extends Hero implements hero{
    {this.z = 5;
    this.a = 5;}

    @Override
    public void Attack(monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(monster m) throws IOException {
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
    public int getCount() {
        return super.getCount();
    }

    @Override
    public void SpecialPower(Object o) throws IOException {

    }

    @Override
    public void restore(){
        this.moveX=0;
    }

}

class Thief extends Hero implements hero{
    {this.z = 6;
    this.a = 4;}

    @Override
    public void Attack(monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(monster m) throws IOException {
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
    public int getCount() {
        return super.getCount();
    }

    @Override
    public void SpecialPower(Object o) throws IOException {
//        this.hp+=0.05*hp;
        Monster x = (Monster) o;
        int mhp = x.gethp();
        this.hp +=0.3*mhp;
        x.sethp((int) (mhp-(0.3*mhp)));
    }
    @Override
    public void restore(){
//        this.hp -= 0.05*hp;
        this.moveX=0;
    }

}

class Healer extends Hero implements hero{
    {this.z = 4;
    this.a = 8;}

    @Override
    public void Attack(monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(monster m) throws IOException {
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
    public int getCount() {
        return super.getCount();
    }

    @Override
    public void SpecialPower(Object o) throws IOException {
        this.hp+=0.05*hp;
    }
    @Override
    public void restore(){
        this.hp -= 0.05*hp;
        this.moveX=0;
    }

}
















