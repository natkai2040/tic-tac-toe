import java.util.InputMismatchException;
import java.util.Scanner;
public class Game
{
    private int[][] grid;
    // private int[][][] winConditions = { { {0, 0}, {0, 1}, {0, 2} }, { {1, 0}, {1, 1}, {1, 2} } };
    private Scanner scan = new Scanner(System.in);
    boolean win;
    int round;
    public Game ()
    {
        win = false;
        round = 0;
        grid = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                grid[i][j] = 0; 
            }
        }
        this.printGrid();
        this.startTurns();
    }
    public void startTurns(){
        while (!win){
            round++;
            if (round % 2 == 1){
                System.out.println("O's Turn: Insert X and Y coordinates");
                this.add(1, scan.nextInt(), scan.nextInt());
            }
            else {
                System.out.println("X's Turn: Insert X and Y coordinates");
                this.add(-1, scan.nextInt(), scan.nextInt());
            }
            this.printGrid();
        }
        scan.close();
    }
    /**
    Param: two integer values from 0 to 2, inclusive
    Enter x value and y value of the desired position
    If there is an X or O in the desired position, return false
    If there is no X nor O in the desired position,
    change the value in the grid and return true
    */
    public boolean add(int symbol, int x, int y)
    {
        if (grid[x][y] == 0){
            grid[x][y] = symbol;
            if (symbol == this.detectWin(symbol, x, y)){
                if (symbol == 1){
                    System.out.println("[O Wins!]");
                }
                else if (symbol == -1){
                    System.out.println("[X Wins!]");
                }
            }
            return true; 
        }
        return false;
    }

    /**
    Returns: String of the tictactoe grid depending on the grid 2D array
    Any values of "0" are printed as " "
    Any values of "1" are printed as "O"
    Any values of "-1" are printed as "X"
    */
    public void printGrid()
    {
        System.out.println("---");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (grid[i][j] == -1){
                    System.out.print("X");
                }
                else if (grid[i][j] == 1){
                    System.out.print("O");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("---");
    }

    /**
    If there is a row, column, or diagonal with consecutive values of -1 or 1,
    return that number, else, return 0. 
     */
    public int detectWin(int symbol, int x, int y)
    {
        //checking horizontals
        for (int i = 0; i <= 2; i++){
            win = true;
            for (int j = 0; j <= 2; j++){
                if (grid[i][j] != symbol){
                    win = false;
                    break;
                }
            }
            if (win){
                return symbol;
            }
        }
        //checking verticals
        for (int i = 0; i <= 2; i++){
            win = true;
            for (int j = 0; j <= 2; j++){
                if (grid[j][i] != symbol){
                    win = false;
                    break;
                }
            }
            if (win){
                return symbol;
            }
        }
        //check diagonals
        if (symbol == grid[0][0] && symbol == grid[1][1] && symbol == grid[2][2]){
            win = true;
            return symbol;
            }
        if (symbol == grid[0][2] && symbol == grid[1][1] && symbol == grid[2][0]){
            win = true;
            return symbol;
        }
        return 0;
    }

}