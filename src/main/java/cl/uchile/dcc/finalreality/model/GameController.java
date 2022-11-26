package cl.uchile.dcc.finalreality.model;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController {
  private static BlockingQueue<GameCharacter> turnsQueue;
  private static List<PlayerCharacter> playerCharacters;
  private static List<Enemy> enemyCharacters;

  public GameController(int numPlayers, int numEnemies)
    throws InvalidStatValueException, RestrictedWeaponException {
    turnsQueue = new LinkedBlockingQueue<>();
    playerCharacters = new ArrayList<>();
    enemyCharacters = new ArrayList<>();
    Weapon staff = new Staff("staff", 20, 30, 24);
    for (int i = 0; i < numPlayers; i++) {
      waitTurn(createPlayer("blackMage" + i, 100, 100, staff));
    }
    for (int i = 0; i < numEnemies; i++) {
      waitTurn(createEnemy("enemy" + i, 10, 100, 100));
    }
  }

  private static PlayerCharacter createPlayer(String name, int Hp, int defense, Weapon weapon)
    throws InvalidStatValueException, RestrictedWeaponException {
    PlayerCharacter character = new BlackMage(name, Hp, defense, 100, turnsQueue);
    character.equip(weapon);
    return character;
  }

  private static Enemy createEnemy(String name, int Hp, int defense, int weight)
    throws InvalidStatValueException {
    return new Enemy(name, weight, Hp, defense, turnsQueue);
  }

  private static void attack(GameCharacter attacker, GameCharacter target) {
    //target.setCurrentHp(target.getCurrentHp() - attacker.);
  }

  private static void useMagic(Mage attacker, GameCharacter target) {
    // TODO implement useMagic()
  }

  private static void waitTurn(GameCharacter character) {
    character.waitTurn();
  }

  private static void onPlayerWin() {
    System.out.println("You win!");
  }

  private static void onEnemyWin() {
    System.out.println("You lose :(");
  }

  static boolean checkWinner() {
    boolean win = true;
    for (PlayerCharacter ally: playerCharacters) {
      if (ally.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      onPlayerWin();
      return true;
    }
    win = true;
    for (Enemy enemy: enemyCharacters) {
      if (enemy.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      onEnemyWin();
      return true;
    }
    return false;
  }

  public static void main(String[] args)
    throws InvalidStatValueException, RestrictedWeaponException {
    GameController game = new GameController(5, 5);
    while (!checkWinner()) { // End of game condition.
      // TODO implement game dynamic
    }
    System.out.println("GAME OVER");
  }
}
