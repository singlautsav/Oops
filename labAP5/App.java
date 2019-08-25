package labAP5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class App {
    public static void main(String[] args)throws IOException {
        Game g = new Game();
        g.run();
    }

}

class Game{
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

    public void Defense(hero _hero, Monster m) throws IOException{
        _hero.Defense(m);
    }
}

class MonsterX{
    public MonsterX(){

    }

    public void Attack(monster m){
        m.attack();
    }
}


class Monster{
    protected int hp = 100;
    public void sethp(int a){
        this.hp = a;
    }
    public int gethp(){
        return this.hp;
    }

}


class Goblin extends Monster implements monster{
    @Override
    public int gethp() {
        return super.gethp();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }
}

class Zombies extends Monster implements monster{
    @Override
    public void sethp(int a) {
        super.sethp(a);
    }

    @Override
    public int gethp() {
        return super.gethp();
    }
}

class Fiends extends Monster implements monster{
    {this.hp = 200;}

    @Override
    public int gethp() {
        return super.gethp();
    }

    @Override
    public void sethp(int a) {
        super.sethp(a);
    }
}

class LionFang extends Monster implements monster{
    {this.hp = 250;}
}




class Hero{
    protected int countMoves = 0;
    protected int xp = 0;
    protected int hp = 100;
    protected int temphp;
    protected int z;
    protected int a;
//    protected boolean SpecialActive;
    protected int moveX = 0;


    public int getHP(){
        return this.hp;
    }
    public void setHp(int x){
        this.hp = x;
    }

    public void Attack(monster m)throws IOException{
        int mhp = m.gethp(m);
        m.sethp(mhp-z);
        countMoves+=1;
        moveX+=1;
        if (moveX==3){
            this.hp = this.temphp;
        }
    }
    public void Defense(monster m) throws IOException {
        int z = m.attack();

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
    public void Attack(Monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Monster m) throws IOException {
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
    public void Attack(Monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Monster m) throws IOException {
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
    public void Attack(Monster m) throws IOException {
        super.Attack(m);
    }

    @Override
    public void Defense(Monster m) throws IOException {
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
















