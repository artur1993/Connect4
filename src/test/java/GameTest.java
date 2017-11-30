import org.junit.Test;

/**
 * Created by Artur on 2017-11-30.
 */
public class GameTest {
    @Test
   public void randomTest(){
        Game game = new Game();
        game.randomValue(0, 6);
    }
}
