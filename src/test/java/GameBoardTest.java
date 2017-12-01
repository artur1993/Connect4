import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Artur on 2017-11-30.
 */
public class GameBoardTest {
    int playerOne = 1;
    int playerTwo = 2;
    Status status;
    @Test
    public void checkHorizontalTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
//        Integer[][] board = gameBoard.getBoard();
        gameBoard.move(1, playerOne);
        gameBoard.move(2, playerOne);
        gameBoard.move(3, playerOne);
        gameBoard.move(4, playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkHorizontal(playerOne, gameBoard.getBoard()).isWin());

    }

    @Test
    public void checkHorizontalFiledTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        Integer[][] board = gameBoard.getBoard();
        gameBoard.move(1, playerOne);
        gameBoard.move(2, playerOne);
        gameBoard.move(3, playerTwo);
        gameBoard.move(4, playerOne);
        gameBoard.move(4, playerOne);

        gameBoard.setBoard(board);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkHorizontal(playerOne, gameBoard.getBoard()).isWin());

    }

    @Test
    public void checkVerticalTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkVertical(1, gameBoard.getBoard()).isWin());
    }
    @Test
    public void checkVerticalFiledTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkVertical(1, gameBoard.getBoard()).isWin());
    }

    @Test
    public void checkDiagonal00To67Test(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(0,playerOne);
        gameBoard.move(1,playerTwo);
        gameBoard.move(1,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkDiagonal00To67(1, gameBoard.getBoard()).isWin());
    }

    @Test
    public void checkDiagonal00To67FiledTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(0,playerOne);
        gameBoard.move(1,playerTwo);
        gameBoard.move(1,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(4,playerOne);
        gameBoard.move(4,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkDiagonal00To67(1, gameBoard.getBoard()).isWin());
    }

    @Test
    public void checkDiagonal60To07Test(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(6,playerOne);
        gameBoard.move(5,playerTwo);
        gameBoard.move(5,playerOne);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkDiagonal60To07(1, gameBoard.getBoard()).isWin());
    }

    @Test
    public void checkDiagonal60To07FiledTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(6,playerOne);
        gameBoard.move(5,playerTwo);
        gameBoard.move(5,playerOne);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkDiagonal60To07(1, gameBoard.getBoard()).isWin());
    }

    @Test
    public void checkIfPlayerWinTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(1, playerOne);
        gameBoard.move(2, playerOne);
        gameBoard.move(3, playerOne);
        gameBoard.move(4, playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);
//        Assert.assertFalse(gameBoard.checkIfPlayerWin(2));

    }

    @Test
    public void checkIfPlayerWinNotTest(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(1, playerOne);
        gameBoard.move(2, playerOne);
        gameBoard.move(3, playerTwo);
        gameBoard.move(4, playerOne);
        gameBoard.move(4, playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);

    }

    @Test
    public void checkIfPlayerWinTest1(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);
//        Assert.assertFalse(gameBoard.checkIfPlayerWin(2));

    }

    @Test
    public void checkIfPlayerWinNotTest1(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);

    }

    @Test
    public void checkIfPlayerWinTest2(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(0,playerOne);
        gameBoard.move(1,playerTwo);
        gameBoard.move(1,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);
//        Assert.assertFalse(gameBoard.checkIfPlayerWin(2));

    }

    @Test
    public void checkIfPlayerWinNotTest2(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(0,playerOne);
        gameBoard.move(1,playerTwo);
        gameBoard.move(1,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(4,playerOne);
        gameBoard.move(4,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);

    }

    @Test
    public void checkIfPlayerWinTest3(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(6,playerOne);
        gameBoard.move(5,playerTwo);
        gameBoard.move(5,playerOne);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerOne);
        gameBoard.printGrid();
        Assert.assertTrue(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);
//        Assert.assertFalse(gameBoard.checkIfPlayerWin(2));

    }

    @Test
    public void checkIfPlayerWinNotTest3(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeGrid();
        gameBoard.move(6,playerOne);
        gameBoard.move(5,playerTwo);
        gameBoard.move(5,playerOne);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerTwo);
        gameBoard.move(4,playerOne);
        gameBoard.move(3,playerOne);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(3,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerTwo);
        gameBoard.move(2,playerOne);
        gameBoard.move(2,playerOne);
        gameBoard.printGrid();
        Assert.assertFalse(gameBoard.checkIfPlayerWin(1, gameBoard.getBoard()) == status.WIN);

    }
}
