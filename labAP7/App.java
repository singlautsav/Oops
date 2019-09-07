package labAP7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class App {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Enter Total Number of paths");
        int x = Integer.parseInt(br.readLine());
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





class Game{
    Random r = new Random();
//    ArrayList<tileX> boardX = new ArrayList<>();

    private final int val;
//    private final int numSnake;
//    private final int numCrickets;
//    private final int numVultures;


    public Game(int a){
        this.val = a;
    }



    public void run(){
//        this.createBoard();
    }




    public int rollDice(){
        return r.nextInt(6)+1;
    }
}

class Player{
    Random r =  new Random();
    private final String name;
    private final int val;
    private ArrayList<tileX> boardX = new ArrayList<>();

    public Player(String name,int a){
        this.name = name;
        this.val = a;
        createBoard();
    }

    private void createBoard(){
        for (int i = 0;i<this.val;i++){
            int x = r.nextInt(5);
            if (x==0){
                tileX a = new Snake();
                boardX.add(i,a);
            }
            else if(x==1){
                tileX a = new Cricket();
                boardX.add(i,a);
            }else if(x==2){
                tileX a = new Vulture();
                boardX.add(i,a);
            }else if(x==3){
                tileX a = new Trampoline();
                boardX.add(i,a);
            }
            else{
                tileX a = new White();
                boardX.add(i,a);
            }
        }
    }

    public ArrayList<tileX> getBoard(){
        return boardX;
    }

}


class tiles{
}

class Snake extends tiles implements tileX{

}

class Cricket extends tiles implements tileX{

}

class Vulture extends tiles implements tileX{

}

class Trampoline extends tiles implements tileX{

}

class White extends tiles implements tileX{

}