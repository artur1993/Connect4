import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Artur on 2017-11-27.
 */
@Setter
@Getter
public class GameBoard {
    private Integer emptyField = 0;
    private Integer userField = 1;
    private Integer computerField = 2;
    private Integer numberOfRow = 6;
    private Integer numberOfColumn = 7;
    Status status;

    Integer[][] board = new Integer[numberOfRow][numberOfColumn];

    void initializeGrid() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = emptyField;
            }
        }
    }

    void printGrid(){
        String rowNumber = "   ";
        Boolean first = true;
        for (Integer i = board.length-1; i >= 0; i--) {
            System.out.print(i.toString() + ": ");
            for (Integer j = 0; j < board[i].length; j++){
                if(first) rowNumber += (j.toString() +": ");
                System.out.print(board[i][j].toString()+ "  ");
            }
            first = false;
            System.out.println();
        }
        System.out.print(rowNumber);

    }

    Status move(Integer columnNumber, Integer valueOfPlayer){
        if(columnFull(columnNumber, board)){
            return status.COLUMN_FULL;
        }
        doMove(columnNumber, valueOfPlayer, board);

        return checkIfPlayerWin(valueOfPlayer, board);

    }

    private void doMove(Integer columnNumber, Integer valueOfPlayer, Integer[][] actualBoard) {
        for (int i = 0; i < actualBoard.length; i++) {
            if(actualBoard[i][columnNumber] == 0){
                actualBoard[i][columnNumber] = valueOfPlayer;
                return;
            }
        }
    }

    boolean columnFull(Integer columnToCheck, Integer[][] actualBoard){
        int last = numberOfRow - 1;
        if(actualBoard[last][columnToCheck] != 0){
            System.out.println("kolumna pełna powtórz ruch");
            return true;
        }
        return false;
    }

    Status checkIfPlayerWin(Integer player, Integer[][] actualBoard){
        if(checkHorizontal(player, actualBoard) || checkVertical(player, actualBoard) || checkDiagonal00To67(player, actualBoard) || checkDiagonal60To07(player, actualBoard)){
            System.out.println("wygrana");
            return status.WIN;
        } else if(gridFull(actualBoard)){
            System.out.println("remis");
            return status.DRAW;
        }
        return status.CONTINUE_GAME;
    }

    int addOneIfPlayersField(Integer field, Integer player, int sum){
        if(field == player){
            return sum += 1;
        } else if(field == emptyField){
            return 0;
        } else {
            return sum = 0;
        }
    }

    boolean checkHorizontal(Integer player, Integer[][] actualBoard){
        for (int i = 0; i < actualBoard.length; i++) {
            if(checkRow(new ArrayList<Integer>(Arrays.asList(actualBoard[i])), player) >= 4){
                return true;
            }
        }
        return false;
    }


    boolean checkVertical(Integer player, Integer[][] actualBoard){
        for (int i = 0; i < actualBoard[0].length; i++) {
            int sum = 0;
            List<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < actualBoard.length; j++) {
                column.add(actualBoard[j][i]);
            }
            if(checkRow(column, player) >= 4){
                return true;
            }
        }
        return false;
    }



    boolean checkDiagonal00To67(Integer player, Integer[][] actualBoard) {
        Integer[][] starts = new Integer[6][2];
        starts[0][0] = 2;
        starts[0][1] = 0;
        starts[1][0] = 1;
        starts[1][1] = 0;
        starts[2][0] = 0;
        starts[2][1] = 0;
        starts[3][0] = 0;
        starts[3][1] = 1;
        starts[4][0] = 0;
        starts[4][1] = 2;
        starts[5][0] = 0;
        starts[5][1] = 3;

        for (int i = 0; i < starts.length; i++) {
            Integer[] start = starts[i];
            if(checkRow(returnDiagonalArray(start, actualBoard, 1), player) >= 4){
                return true;
            }
        }

        return false;
    }

    boolean checkDiagonal60To07(Integer player, Integer[][] actualBoard) {
        Integer[][] starts = new Integer[6][2];
        starts[0][0] = 3;
        starts[0][1] = 0;
        starts[1][0] = 4;
        starts[1][1] = 0;
        starts[2][0] = 5;
        starts[2][1] = 0;
        starts[3][0] = 5;
        starts[3][1] = 1;
        starts[4][0] = 5;
        starts[4][1] = 2;
        starts[5][0] = 5;
        starts[5][1] = 3;

        for (int i = 0; i < starts.length; i++) {
            Integer[] start = starts[i];
            if(checkRow(returnDiagonalArray(start, actualBoard, -1), player) >= 4){
                return true;
            }
        }

        return false;
    }

    Integer checkRow(List<Integer> row, Integer player) {
        int sum = 0;
        for (int field : row) {
            sum = addOneIfPlayersField(field, player, sum);
            if (sum >= 4) {
                return sum;
            }
        }
        return sum;
    }

    public List<Integer> returnDiagonalArray(Integer[] start, Integer[][] actualBoard, int operator) {
        List<Integer> diagonal = new ArrayList<Integer>();
        boolean rowColumnSizeConnect = true;
        for(int j = 0; rowColumnSizeConnect; j++){
            int row = start[0] + j*operator;
            int column = start[1] + j;
            if(0 <= row && row < numberOfRow && column < numberOfColumn){
                diagonal.add(returnValue(row, column, actualBoard));
            } else {
                rowColumnSizeConnect = false;
            }
        }
        return diagonal;
    }

    Integer returnValue(int i, int j, Integer[][] actualBoard){
        return actualBoard[i][j];
    }

    boolean gridFull(Integer[][] actualBoard){
        for(int i = 0; i < numberOfColumn; i++){
            if(!columnFull(i, actualBoard)){
                return false;
            }
        }
        return true;
    }

}
