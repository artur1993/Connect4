import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Artur on 2017-11-30.
 */
public class Game {
    Integer player = 1;
    Integer computer = 2;
    Scanner input = new Scanner(System.in);
    GameBoard gameBoard = new GameBoard();
    Status status;
    void gameStart(){
        gameBoard.initializeGrid();
        System.out.println("Wybierz kto zaczyna pierwszy: gracz-1, komputer-2");
        Integer whoStart = input.nextInt();
        chosenPlayer(whoStart);
    }

    private void chosenPlayer(Integer whoStart) {
        if(whoStart == player){
            playerMove();
        } else if (whoStart == computer){
            computerMove();
        } else {
            gameStart();
        }
    }

    public void playerMove(){
        System.out.println("Wybierz kolumnę z zakresu 0-6 lub 9 jeżeli ruch ma być wykonany przez algorytm");
        Integer chosenColumn = input.nextInt();
        if(0 <= chosenColumn && chosenColumn <= 6){
            gameResult(chosenColumn, player);
        } else if(chosenColumn == 9){
            MinMax minMax = new MinMax();
            ArrayList<ColumnValue> path = new ArrayList<ColumnValue>();
            ColumnValue columnValue = minMax.treeGenerate(gameBoard.getBoard(), player, player, path, 0, MinMaxEnum.MAX);
            gameResult(columnValue.getColumn(), player);
        } else {
            System.out.println(chosenColumn.toString() + " nie jest poprawnym wyborem, kolumny są numerowane 0-6, 9 jeżeli ruch ma być wykonany przez algorytm");
            playerMove();
        }

    }

    public void computerMove(){
        MinMax minMax = new MinMax();
        ArrayList<ColumnValue> path = new ArrayList<ColumnValue>();
        ColumnValue columnValue = minMax.treeGenerate(gameBoard.getBoard(), computer, computer, path, 0, MinMaxEnum.MAX);
        gameResult(columnValue.getColumn(), computer);
    }

    public Integer randomValue(Integer min, Integer max){
        Random random = new Random();
        return random.nextInt((max-min)+1) + min;
    }

    private void gameResult(Integer chosenColumn, Integer whoseMove) {
        System.out.println();
        switch (gameBoard.move(chosenColumn, whoseMove)){
            case WIN:
                gameBoard.printGrid();
                winMessage(whoseMove);
                break;
            case DRAW:
                gameBoard.printGrid();
                System.out.println("Remis");
                break;
            case COLUMN_FULL:
                System.out.println("Kolumna " + chosenColumn.toString() + " jest pełna wybierz inną");
                chosenPlayer(whoseMove);
                break;
            case CONTINUE_GAME:
                gameBoard.printGrid();
                chosenPlayer(changePlayer(whoseMove));
                break;
        }
        System.out.println();
    }

    private void winMessage(Integer whoseMove) {
        if(whoseMove == player){
            System.out.println("Gratulacje wygrałeś");
        } else {
            System.out.println("Niestety komputer okazał się lepszy");
        }
    }
    Integer changePlayer(Integer actualPlayer){
        if(actualPlayer == player){
            return computer;
        } else {
            return player;
        }
    }
}
