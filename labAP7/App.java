package labAP7;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;



public class App {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException,VultureBiteException,CricketBiteException,TrampolineException,SnakeBiteException, WinningException {

        System.out.println("Enter Total Number of paths");
        int x;
        try {
            x = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e){
            System.out.println("ERROR: Type in a integral Value");
            x = Integer.parseInt(br.readLine());
        }
        Game g = new Game(x);
        g.run();
    }
}


class SnakeBiteException extends Exception{
    public SnakeBiteException(String message){
        super(message);
    }
}
class CricketBiteException extends Exception{
    public CricketBiteException(String message){
        super(message);
    }
}
class VultureBiteException extends Exception{
    public VultureBiteException(String message){
        super(message);
    }
}
class TrampolineException extends Exception{
    public TrampolineException(String message){
        super(message);
    }
}
class WinningException extends Exception{
    public WinningException(String message){super(message);}
}



class Game{
    Random r = new Random();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<tileX> boardX;
//    private int playerTile = 1;
    private int numRoll = 1;
    private int rollVal;
    private Snake objs = new Snake(0);
    private Vulture objv = new Vulture(0);
    private Cricket objc = new Cricket(0);
    private Trampoline objt = new Trampoline(0);
    private Player x;

//    public int

    private int val;
    private int numSnake;
    private int numCrickets;
    private int numVultures;
    private int numTramp;



    public Game(int a){
        this.val = a;
    }

    public void desearlize(String name){
        ObjectInputStream in = null;
        String fileName = name +".txt";
        try{
//            FileInputStream fis = new FileInputStream("student.ser");
            in = new ObjectInputStream(new FileInputStream(fileName));
            x = (Player) in.readObject();
            System.out.println("deserialized");
            this.val = x.getVal();
            this.numTramp = x.getNumTrampoline();
            this.numCrickets = x.getNumCricket();
            this.numVultures = x.getNumVulture();
            this.numSnake = x.getNumSnake();
            this.numRoll = x.getRollVal();
            System.out.println("Number of tiles restored to "+ x.getVal());
        }
        catch (Exception e) {
            x = new Player(name, val);

        }
    }

    public void serialize(String name){
        ObjectOutputStream out = null;
        String fileName = name +".txt";
        try{
            System.out.println(fileName);
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            x.setNumCricket(this.numCrickets);
            x.setNumSnake(this.numSnake);
            x.setNumVulture(this.numVultures);
            x.setNumTrampoline(this.numTramp);
            x.setRollVal(this.numRoll);
            out.writeObject(x);
            out.close();
            System.out.println("serialized");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void serializeHelper(String nm) throws IOException {
        System.out.println("Do You want to Save [s] or Continue [c] the game");

        String opt = br.readLine();
        if (opt.equals("s")){
            serialize(nm);
            System.exit(0);
        }
        else if (!opt.equals("c")){
            System.out.println("Incorrect Input Exiting");
            System.exit(0);
        }
    }


    public void run() throws IOException,VultureBiteException,CricketBiteException,TrampolineException,SnakeBiteException, WinningException{
//        this.createBoard();
        System.out.println("Write the name of player :) : ");
        String nm =  br.readLine();
        this.desearlize(nm);
        boardX = x.getBoard();
        System.out.println("Danger: " + x.getTotalCricket()+" Crickets, "
                + x.getTotalSnakes()+ " Snakes, " + x.getTotalVulture()+" Vultures are on track ");
        System.out.println("");
        System.out.println("Good News: There are "+ x.getTotalTrampoline() +" Trampoline");
        System.out.println("");

        System.out.println("Transfered Controls to the Computer, Hit ENTER to start");
        String a = br.readLine();
//        int player = 0;
        if (a.equals("")) {
            System.out.println("Starting Game =====================================>");
            while (x.getPlayerTile() < val) {

                if((x.getPlayerTile()>(int)(0.25*this.val))&&(x.getPlayerTile()<(int)(0.5*this.val))&& !x.isSaved25()){
                    x.setSaved25();
                    serializeHelper(nm);
                }
                else if((x.getPlayerTile()>((int)(0.5*this.val)))&& (x.getPlayerTile()<(int)(0.75*this.val)&& !x.isSaved50())){
                    x.setSaved50();
                    serializeHelper(nm);
                }
                else if((x.getPlayerTile()>(int)(0.75*this.val))&& !x.isSaved75()) {
                    x.setSaved75();
                    serializeHelper(nm);
                }

                if (x.getPlayerTile() == 1) {
                    zerotile();
                }
                else {
                    rollVal = rollDice();
                    soutX();
                    x.setPlayerTile(x.getPlayerTile()+rollVal);
//                    playerTile += rollVal;
                    System.out.print(" landed on " + x.getPlayerTile());
//                exceptionCheck();
                    if (x.getPlayerTile() < this.val) {
                        try {
                            exceptionCheck();
                        } catch (WinningException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(WinMessage());
                    }
                }

            }
        }
        else System.out.println("Incorrect Input, Exiting the Game");

    }

    private void exceptionCheck()throws WinningException{
        try{
            shakeIt();
        }
        catch (VultureBiteException e){
            System.out.println(e.getMessage());
        }
        catch (CricketBiteException e){
            System.out.println(e.getMessage());
        }
        catch (TrampolineException e){
            System.out.println(e.getMessage());
        }
        catch (SnakeBiteException e){
            System.out.println(e.getMessage());
        }
        catch (WinningException e){
            System.out.println(e.getMessage());
        }

        tileX obj = boardX.get(x.getPlayerTile());
//        this.playerTile+=obj.getval();
        x.setPlayerTile(x.getPlayerTile()+obj.getval());
//        System.out.println(this.playerTile);
        numRoll+=1;
        if (x.getPlayerTile()<1){
            x.setPlayerTile(1);
            System.out.println("Josh moved to to tile 1");
        }
        else if(x.getPlayerTile()>=this.val){
//            playerTile=this.val;
            x.setPlayerTile(this.val);
            System.out.println("landed on, "+ this.val+", Wait for winning announcement");
            throw new WinningException(this.WinMessage());
        }
    }

    private void zerotile(){
        rollVal = rollDice();
        if (rollVal==6){
            soutX();
            System.out.println(" You are out of the cage! You get a free roll.");
//            System.out.println("");
            numRoll+=1;
            rollVal = rollDice();
            soutX();
            x.setPlayerTile(x.getPlayerTile()+rollVal);
            System.out.println(" landed on "+ x.getPlayerTile());
            try {
                exceptionCheck();
            }
            catch (WinningException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            soutX();
            System.out.println(" Ooops you need 6 to break");
            numRoll+=1;
        }
    }

    private int rollDice(){
        return r.nextInt(6)+1;
    }

    private void soutX(){
        System.out.print("[Roll-"+this.numRoll+"]: "+x.name+ " rolled "+ rollVal+ " at Tile-"+ x.getPlayerTile());
    }

    private void shakeIt()throws SnakeBiteException,VultureBiteException,CricketBiteException,TrampolineException, WinningException{
        System.out.println(" Trying to shake the Tile-"+x.getPlayerTile());
        tileX obj = boardX.get(x.getPlayerTile());

        if (obj.getClass().equals(objs.getClass())){
            this.numSnake+=1;

            throw new SnakeBiteException("hisss...! I am Snake, you go back " + (-obj.getval())+ " Steps") ;
//            numSnake+=1;
        }
        else if(obj.getClass().equals(objv.getClass())){
            this.numVultures+=1;

            throw new VultureBiteException("Yapping..! I am a Vulture, you go back "+ (-obj.getval())+ " Steps");
//            numVultures+=1;
        }
        else if(obj.getClass().equals(objc.getClass())){
            this.numCrickets+=1;
            throw new CricketBiteException("Chirp..! I am a Cricket, you go back "+ -obj.getval()+ " Steps");
//            numCrickets+=1;
        }
        else if(obj.getClass().equals(objt.getClass())){
            this.numTramp+=1;
            throw new TrampolineException("PingPong! I am Trampoline, you advance " + obj.getval()+" Steps");
//            numTramp+=1;
        }
        else {
            System.out.println("I am White Tile!!");
        }

    }

    private String WinMessage(){
        return String.join("\n",
                "Race won in "+this.numRoll,
                "Total Snake Bites= "+ this.numSnake,
                "Total Vulture Bites= "+ this.numVultures,
                "Total Cricket Bites= "+ this.numCrickets,
                "Total Trampolines= "+ this.numTramp);
//        return s;
    }

}

class Player implements Serializable{
    Random r =  new Random();
    public final String name;
    private final int val;
    private ArrayList<tileX> boardX = new ArrayList<>();
    private int playerTile=1;
    private int totalSnakes;
    private int totalVulture;
    private int totalCricket;
    private int totalTrampoline;
    private boolean isSaved25 = false;
    private boolean isSaved50 = false;
    private boolean isSaved75 = false;
    private int totalWhite;

    private int numSnake;
    private int numVulture;
    private int numCricket;
    private int numTrampoline;

    private int rollVal;



    public Player(String name,int a){
        this.name = name;
        this.val = a;
        createBoard();
    }

    private void createBoard(){
        int sn = (int)((r.nextInt(this.val-5)+1)/10);
        System.out.println(sn);
        int cn = (int)((r.nextInt(this.val-5)+1)/10);
        int vn = (int)((r.nextInt(this.val-5)+1)/10);
        int tn = (int)((r.nextInt(this.val-5)+1)/5);

//        boardX.add(1,);
        for (int i = 0;i<this.val;i++){
            int x = r.nextInt(5+1);
            if (x==0){
                tileX a = new Snake(sn);
                boardX.add(a);
                totalSnakes+=1;
            }
            else if(x==3){
                tileX a = new Cricket(cn);
                boardX.add(a);
                totalCricket+=1;
            }else if(x==1){
                tileX a = new Vulture(vn);
                boardX.add(a);
                totalVulture+=1;
            }else if(x==2){
                tileX a = new Trampoline(tn);
                boardX.add(a);
                totalTrampoline+=1;
            }
            else{
                tileX a = new White();
                boardX.add(a);
                totalWhite+=1;
            }
        }
        tileX a = new White();
        boardX.set(1,a);
    }

    public ArrayList<tileX> getBoard(){
        return boardX;
    }

    public int getTotalSnakes(){
        return totalSnakes;
    }
    public int getTotalVulture(){
        return totalVulture;
    }
    public int getTotalCricket(){
        return totalCricket;
    }
    public int getTotalTrampoline(){
        return totalTrampoline;
    }
    public int getPlayerTile(){
        return playerTile;
    }

    public void setPlayerTile(int a){
        this.playerTile = a;
    }

    public boolean isSaved25(){ return isSaved25; }
    public boolean isSaved50() {return isSaved50;}
    public boolean isSaved75() {return isSaved75;}

    public void setSaved25() { isSaved25 = true; }
    public void setSaved75() { isSaved75 = true; }
    public void setSaved50() { isSaved50 = true; }

    public int getVal(){
        return this.val;
    }

    public int getNumSnake(){return numSnake;}
    public int getNumCricket() {
        return numCricket;
    }
    public int getNumTrampoline() {
        return numTrampoline;
    }
    public int getNumVulture() {
        return numVulture;
    }

    public void setNumSnake(int a){
        this.numSnake = a;
    }

    public void setNumCricket(int numCricket) {
        this.numCricket = numCricket;
    }

    public void setNumTrampoline(int numTrampoline) {
        this.numTrampoline = numTrampoline;
    }

    public void setNumVulture(int numVulture) {
        this.numVulture = numVulture;
    }

    public int getRollVal(){
        return this.rollVal;
    }

    public void setRollVal(int a){
        this.rollVal = a;
    }
}

abstract class tiles implements Serializable {
    protected int val;

    public abstract int getval();
}

class Snake extends tiles implements tileX{
    public Snake(int a){
        this.val = a;
    }

    @Override
    public int getval() {
        return -this.val;
    }
}

class Cricket extends tiles implements tileX{
    public Cricket(int a){
        this.val = a;
    }

    @Override
    public int getval() {
        return -this.val;
    }
}

class Vulture extends tiles implements tileX{
    public Vulture(int a){
        this.val = a;
    }

    @Override
    public int getval() {
        return -this.val;
    }
}

class Trampoline extends tiles implements tileX{
    public Trampoline(int a){
        this.val = a;
    }

    @Override
    public int getval() {
        return this.val;
    }
}

class White extends tiles implements tileX{
    public White(){
        this.val = 0;
    }

    @Override
    public int getval() {
        return 0;
    }
}