package labAP7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


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
    ArrayList<tileX> boardX;
    private int playerTile = 1;
    private int numRoll = 0;
    private int rollVal;
    private Snake objs = new Snake(0);
    private Vulture objv = new Vulture(0);
    private Cricket objc = new Cricket(0);
    private Trampoline objt = new Trampoline(0);
    private Player x;

//    public int

    private final int val;
    private int numSnake;
    private int numCrickets;
    private int numVultures;
    private int numTramp;



    public Game(int a){
        this.val = a;
    }

    public void run() throws IOException,VultureBiteException,CricketBiteException,TrampolineException,SnakeBiteException, WinningException{
//        this.createBoard();
        System.out.println("Write the name of player :) : ");
        String nm =  br.readLine();
        x =  new Player(nm,val);
        boardX = x.getBoard();
        System.out.println("Danger: " + x.getTotalCricket()+" Crickets, "
                + x.getTotalSnakes()+ " Snakes, " + x.getTotalVulture()+" Vultures are on track ");
        System.out.println("");
        System.out.println("Good News: There are "+ x.getTotalTrampoline() +" Trampoline");
        System.out.println("");
//        int player = 0;
        while (playerTile<val){

            if (playerTile==1){
                zerotile();
            }
            else{
                rollVal = rollDice();
                soutX();
                playerTile+=rollVal;
                System.out.print(" landed on "+ this.playerTile);
//                exceptionCheck();
                if (playerTile<100) {
                    try {
                        exceptionCheck();
                    } catch (WinningException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    System.out.println(WinMessage());
                }
            }

        }

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

        tileX obj = boardX.get(this.playerTile);
        this.playerTile+=obj.getval();
        numRoll+=1;
        if (playerTile<1){
            playerTile=1;
            System.out.println("Josh moved to to tile 1");
        }
        else if(playerTile>=100){
            playerTile=100;
            System.out.println("landed on 100, Wait for winning announcement");
            throw new WinningException(this.WinMessage());
        }
    }

    public void zerotile()throws WinningException{
        rollVal = rollDice();
        if (rollVal==6){
            soutX();
            System.out.println(" You are out of the cage! You get a free roll.");
//            System.out.println("");
            numRoll+=1;
            rollVal = rollDice();
            soutX();
            playerTile+=rollVal;
            System.out.println(" landed on "+ playerTile);
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
        System.out.print("[Roll-"+this.numRoll+"]: "+x.name+ " rolled "+ rollVal+ " at "+ this.playerTile);
    }

    private void shakeIt()throws SnakeBiteException,VultureBiteException,CricketBiteException,TrampolineException, WinningException{
        System.out.println(" Trying to shake the Tile-"+this.playerTile);
        tileX obj = boardX.get(this.playerTile);
//        playerTile+=obj.getval();
//        numRoll+=1;
////        if (this.playerTile<1){
//            playerTile=1;
//            System.out.println("Josh moved to to tile 1");
//        }
//        else if(playerTile>100){
//            playerTile=100;
//            System.out.println("landed on 100, Wait for winning announcement");
//        }
        if (obj.getClass().equals(objs.getClass())){
            this.numSnake+=1;

            throw new SnakeBiteException("hisss...! I am Snake, you go back " + obj.getval()+ " Steps") ;
//            numSnake+=1;
        }
        else if(obj.getClass().equals(objv.getClass())){
            this.numVultures+=1;

            throw new VultureBiteException("Yapping..! I am a Vulture, you go back "+ obj.getval()+ " Steps");
//            numVultures+=1;
        }
        else if(obj.getClass().equals(objc.getClass())){
            this.numCrickets+=1;
            throw new CricketBiteException("Chirp..! I am a Cricket, you go back "+ obj.getval()+ " Steps");
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
        String s = String.join("\n",
                "Race won in "+this.numRoll,
                "Total Snake Bites= "+ this.numSnake,
                "Total Vulture Bites= "+ this.numVultures,
                "Total Cricket Bites= "+ this.numCrickets,
                "Total Trampolines= "+ this.numTramp);
        return s;
    }

}

class Player{
    Random r =  new Random();
    public final String name;
    private final int val;
    private ArrayList<tileX> boardX = new ArrayList<>();

    private int totalSnakes;
    private int totalVulture;
    private int totalCricket;
    private int totalTrampoline;
    private int totalWhite;


    public Player(String name,int a){
        this.name = name;
        this.val = a;
        createBoard();
    }

    private void createBoard(){
        int sn = (int)((r.nextInt(this.val-5)+1)/5);
        System.out.println(sn);
        int cn = (int)((r.nextInt(this.val-5)+1)/5);
        int vn = (int)((r.nextInt(this.val-5)+1)/5);
        int tn = (int)((r.nextInt(this.val-5)+1)/2);

//        boardX.add(1,);
        for (int i = 0;i<this.val;i++){
            int x = r.nextInt(5+1);
            if (x==0){
                tileX a = new Snake(sn);
                boardX.add(a);
                totalSnakes+=1;
            }
            else if(x==1){
                tileX a = new Cricket(cn);
                boardX.add(a);
                totalCricket+=1;
            }else if(x==2){
                tileX a = new Vulture(vn);
                boardX.add(a);
                totalVulture+=1;
            }else if(x==3){
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

}


abstract class tiles{
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