package cl.uchile.dcc.finalreality;

import static cl.uchile.dcc.finalreality.controller.GameController.getGameController;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.view.GameView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Run Final Reality.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class Main {

  /**
   * Run Final Reality.
   *
   * @param args
   *     no args required
   * @throws InvalidTransitionException
   *     if a state transition is executed at the wrong moment
   * @throws InterruptedException
   *     if a {@link BlockingQueue} operation is interrupted
   */
  public static void main(String[] args)
    throws InvalidTransitionException, InterruptedException, NullWeaponException {
    BlockingQueue<GameCharacter> turnsQueue = new LinkedBlockingQueue<>();
    List<PlayerCharacter> playerCharacters = new ArrayList<>();
    List<Enemy> enemyCharacters = new ArrayList<>();
    GameView view = new GameView();
    GameController controller = getGameController(
        turnsQueue, playerCharacters, enemyCharacters, view
    );
    controller.play();
  }
}
