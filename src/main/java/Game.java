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
        System.out.println("Wybierz kolumnę z zakresu 0-6");
        Integer chosenColumn = input.nextInt();
        if(0 <= chosenColumn && chosenColumn <= 6){
            gameResult(chosenColumn, player);
        } else {
            System.out.println(chosenColumn.toString() + " nie jest poprawnym wyborem, kolumny są numerowane 0-6");
            playerMove();
        }

    }

    public void computerMove(){
        gameResult(randomValue(0, 6), computer);
    }

    public Integer randomValue(Integer min, Integer max){
        Random random = new Random();
        return random.nextInt((max-min)+1) + min;
    }

    private void gameResult(Integer chosenColumn, Integer whoseMove) {
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
