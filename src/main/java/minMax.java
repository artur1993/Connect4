import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by Artur on 2017-11-30.
 */
public class MinMax {

    Game game = new Game();
    Status status;
    int maxDeep = 6;

    ColumnValue treeGenerate(Integer[][] board, Integer startPlayer, Integer actualPlayer, List path, int deep, MinMaxEnum valueMinOrMax) {
        if (board[0][3] == 0) {
            return new ColumnValue(3, 1000);
        }
        try {
            ArrayList<ColumnValue> tree = new ArrayList<ColumnValue>();
            for (int i = 0; i < 7; i++) {
                GameBoard gameBoard = new GameBoard();
                Integer[][] lastBorder = new Integer[6][7];
                copyBoard(lastBorder, board);
                gameBoard.setBoard(lastBorder);
                if (!gameBoard.columnFull(i, gameBoard.getBoard())) {
                    gameBoard.move(i, actualPlayer);
                    if (deep == maxDeep || abs(ratingFunction(gameBoard.getBoard(), actualPlayer)) == 100) {
                        tree.add(new ColumnValue(i, ratingFunction(gameBoard.getBoard(), actualPlayer)));
                    } else {
                        tree.add(new ColumnValue(i, treeGenerate(gameBoard.getBoard(), startPlayer, game.changePlayer(actualPlayer),
                                path, deep + 1, changeMinMax(valueMinOrMax)).getValue()));
                    }
                }
            }
            ColumnValue columnValue = choseBetter(tree, valueMinOrMax);
            path.add(columnValue);
            return columnValue;
        } catch (Exception ex) {
            return new ColumnValue(game.randomValue(0, 6), 0);
        }


    }

    void copyBoard(Integer[][] board, Integer[][] boardToCopy) {
        for (int i = 0; i < boardToCopy.length; i++) {
            for (int j = 0; j < boardToCopy[i].length; j++) {
                board[i][j] = boardToCopy[i][j];
            }
        }
    }

    MinMaxEnum changeMinMax(MinMaxEnum value) {
        if (value == MinMaxEnum.MIN) {
            return MinMaxEnum.MAX;
        } else {
            return MinMaxEnum.MIN;
        }
    }

    private ColumnValue choseBetter(ArrayList<ColumnValue> tree, MinMaxEnum minOrMax) {
        List<ColumnValue> bestMove = new ArrayList<ColumnValue>();
        for (int i = 0; i < tree.size(); i++) {
            if (bestMove.isEmpty() || tree.get(i).getValue() == bestMove.get(0).getValue()) {
                bestMove.add(tree.get(i));
            } else if (minMaxCheckIfValueBetter(tree.get(i).getValue(), bestMove.get(0).getValue(), minOrMax)) {
                bestMove.clear();
                bestMove.add(tree.get(i));
            }
        }
        try {
            return bestMove.get(game.randomValue(0, bestMove.size() - 1));
        } catch (Exception ex) {
            return new ColumnValue(game.randomValue(0, 6), 0);
        }
    }

    boolean minMaxCheckIfValueBetter(Integer value, Integer lastValue, MinMaxEnum minMaxValue) {
        MinMaxEnum minMaxEnum = null;
        if (minMaxValue == minMaxEnum.MAX) {
            return value > lastValue;
        } else if (minMaxValue == minMaxEnum.MIN) {
            return value < lastValue;
        } else {
            return false;
        }
    }

    int ratingFunction(Integer[][] board, Integer player) {
        GameBoard gameBoard = new GameBoard();
        if (gameBoard.checkIfPlayerWin(game.changePlayer(player), board) == status.WIN) {
            return -100;
        } else {
            switch (gameBoard.checkIfPlayerWin(player, board)) {
                case WIN:
                    return 100;
                case DRAW:
                    return 10;
                case COLUMN_FULL:
                    return 0;
                case CONTINUE_GAME:
                    return 0;
            }
            return 0;
        }
    }
}