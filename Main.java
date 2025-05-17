import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    //assets
    static ArrayList<Character> history = new ArrayList<Character>();
    static char turn = 'x';
    static char winner;
    static final char[][] WIN = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'1', '4', '7'}, {'2', '5', '8'}, {'3', '6', '9'}, {'1', '5', '9'}, {'3', '5', '7'}};
    static int count;
    static char[] player_o = new char[9];
    static char[] player_x = new char[9];
    static char[][] allInputs = {player_o, player_x};
    static String message = "";
    static char[] boardData;
    static boolean run = true;
    static int[] score = {0, 0};
   
    static {
        boardData = new char[9];
        for(int i = 0; i < 9; i++){ 
            boardData[i] = ' ';
        }
    }

    //Resets the games assets
    static void resetGame() {
        Cls.cls();
        history.clear();
        turn = 'o';
        winner = '\u0000';
        count = 0;
        player_o = new char[9];
        player_x = new char[9];
        allInputs[0] = player_o;
        allInputs[1] = player_x;
      
        message = "";

        for(int i = 0; i < boardData.length; i++) {
            boardData[i] = ' ';
        }
        
        run = true;
    }

    //Counts the number of target
    static int countElement(char target) {
        int counter = 0;
        for(char[] i : allInputs){
            for(char j : i) {
                if(target != j) {
                    counter += 1;
                }
            }
            
        }
        return counter;
    }

    //Removes the most early element
    static void gc(){
        if(countElement('\u0000') >= 7 ) {
            if(countElement('\u0000') == 8 ) {
                message = ">Removed block : " + history.get(0) + "\n";
                
                boardData[Integer.parseInt(String.valueOf(history.get(0))) - 1] = ' ';
               

                outer:
                for(char[] i : allInputs) {
                    int index = 0;
                    for(char j : i) {
                        if(j == history.get(0)) {
                            i[index] = '\u0000';
                            break outer;
                        }
                        index += 1;
                    }
                }
                history.remove(0);

            }
            message += ">Next block to be removed: " + history.get(0);
        }
    }

    //Checks for winner and return a boolean
    static boolean checkWin(char[] lst) {
        boolean result = false;
        int checkCount = 0;
        outer:
        for(char[] i : WIN) {
            checkCount = 0;
            for(char j : i) {
                for(char k : lst) {
                    if(j == k) {
                        checkCount++; 
                        break;                      
                    }
                    
                }
                if(checkCount >= 3) {
                    result = true;
                    break outer;
                    
                    
                }
            }

        }
       
        return result;
    }

    //Display the game over message
    static void gameOver() {
        System.out.println("Winner : " + winner);
        Scanner ask2 = new Scanner(System.in);
        System.out.print("Do you want to play again [y]:");
        String ans = ask2.next();
        if(ans.toLowerCase().equals("y")){
            resetGame();
        }
        else{
            ask2.close();
        }
    }

    //the main game Flow
    static void gameFlow() {
        Scanner scan = new Scanner(System.in);
        

        while(run) {
            Cls.cls();
            boardSetup();
            System.out.println(message);
            message = "";
            System.out.print("Player " + turn + ">");
            String inp_ = scan.nextLine();
            boolean check = false;
            char inp = '\u0000';
            
            if(inp_.split(" ").length == 1){
                if(inp_.length() == 1) {
                    inp = inp_.charAt(0);
                    if(Character.isDigit(inp)){
                        check = true;
                    }
                }
            }
                
            if(check){
                boolean check2 = true;
               
                outerloop:
                for(char[] i : allInputs) {
                    for(char j : i) {
                        if(inp == j) {
                            check2 = false;
                            break outerloop;
                        }
                    }
                }
                System.out.println(check2);

                if(check2 == true) {
                    boardData[Integer.parseInt(String.valueOf(inp)) - 1] = turn;
                    
                    allInputs[count][Integer.parseInt(String.valueOf(inp)) - 1] = inp;
                    history.add(inp);
                    if(checkWin(allInputs[count]) == true){
                        winner = turn;
                        run = false;
                        Cls.cls();
                        score[count] += 1;
                        boardSetup();                       
                        gameOver();
                        
                    }
                    else{
                        gc();
                    }


                    if(turn == 'x') {
                        turn = 'o';
                        count = 1;
                    }
                    else {
                        turn = 'x';
                        count = 0;
                    }
                }
                else {
                    message = "! already used, try another;";
                }
            }
            else {
                message = "! Invalid input used 1-9 digits only;";
            }
        }
        scan.close();
        
    }

    //board setup
    static void boardSetup() {
        
        System.out.printf("Tic Tak Toe\n-----\nA Vansh Rana Production\n----- \nScores \nPlayer(x):%d\nPlayer(o):%d\n-----\n\n", score[0], score[1]);
       
        String board = String.format(" %c | %c | %c \n - | - | - \n %c | %c | %c \n - | - | - \n %c | %c | %c ", boardData[0], boardData[1], boardData[2], boardData[3], boardData[4], boardData[5], boardData[6], boardData[7], boardData[8]);
       
        System.out.println(board);
        
    }

    //the main method
    public static void main(String[] args) {
        gameFlow();
    }
}
//Written by Vansh Rana;