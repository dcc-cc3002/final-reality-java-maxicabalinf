package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.GameController;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.view.GameView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
  public static void main(String[] args)
      throws RestrictedWeaponException, InvalidStatValueException {
    BlockingQueue<GameCharacter> turnsQueue = new LinkedBlockingQueue<>();
    List<PlayerCharacter> playerCharacters = new ArrayList<>();
    List<Enemy> enemyCharacters = new ArrayList<>();
    GameView view = new GameView();
    GameController controller = new GameController(
        turnsQueue, playerCharacters, enemyCharacters, view
    );
    controller.play();
  }
}
