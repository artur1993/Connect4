import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 2017-11-30.
 */
public class minMax {

    Game game = new Game();
    Status status;
    int maxDeep = 2;
    MinMaxEnum minMaxEnum;


    ColumnValue treeGenerate(Integer[][] board, Integer startPlayer, Integer actualPlayer, List path, int deep) {
        GameBoard gameBoard = new GameBoard();
        ArrayList<ColumnValue> tree = new ArrayList<ColumnValue>();

        for (int i = 0; i < 7; i++) {
            if (!gameBoard.columnFull(i, board)){
                gameBoard.move(i, startPlayer);
                if(deep == maxDeep){
                    tree.add(new ColumnValue(i, ratingFunction(gameBoard.getBoard(), startPlayer)));
                } else {
                    tree.add(new ColumnValue(i, treeGenerate(gameBoard.getBoard(), startPlayer, game.changePlayer(actualPlayer), path, deep+1).getValue()));
                }
            }
        }
//        TODO:max
        ColumnValue columnValue = choseBetter(tree, minMaxEnum.MAX);
        path.add(columnValue);
        return columnValue;

    }

    private ColumnValue maxDeepGenerateBoard(Integer[][] board, Integer startPlayer, Integer actualPlayer) {
        GameBoard gameBoard = new GameBoard();
        ArrayList<ColumnValue> tree = new ArrayList<ColumnValue>();
        for (int i = 0; i < 7; i++) {
            if (!gameBoard.columnFull(i, board)){
                gameBoard.move(i, startPlayer);
                tree.add(new ColumnValue(i, ratingFunction(gameBoard.getBoard(), startPlayer)));
            }
        }
        return choseBetter(tree, minMaxEnum.MAX);
    }

    private ColumnValue choseBetter(ArrayList<ColumnValue> tree, MinMaxEnum minOrMax) {
        return null;
    }

    int ratingFunction(Integer[][] board, Integer player){
        GameBoard gameBoard = new GameBoard();
        if(gameBoard.checkIfPlayerWin(game.changePlayer(player), board) == status.WIN){
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