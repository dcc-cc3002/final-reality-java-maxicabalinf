package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.GreaterStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.LesserStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A whole trial of the modifications made from the master branch.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Main {

  /**
   * Runs the trial.
   *
   * @param args
   *     the command line arguments
   * @throws InvalidStatValueException
   *     when {@link GameCharacter} is given out-of-bounds values
   * @throws InterruptedException
   *     to make the excecution wait some given time
   */
  public static void main(String[] args) throws InvalidStatValueException, InterruptedException {
    BlockingQueue<GameCharacter> queue = new LinkedBlockingQueue<>();
    Random rng = new Random();

    // region: CHARACTER-WEAPON CREATION
    int i = 0;
    var knife = new Weapon("", 0, rng.nextInt(50), "KNIFE");
    var blackmage = new BlackMage(Integer.toString(i), 10, 10, 10, queue);
    blackmage.equip(knife);
    blackmage.waitTurn();
    i += 1;
    var bow = new Weapon("", 0, rng.nextInt(50), "BOW");
    var engineer = new Engineer(Integer.toString(i), 10, 10, queue);
    engineer.equip(bow);
    engineer.waitTurn();
    i += 1;
    var sword = new Weapon("", 0, rng.nextInt(50), "SWORD");
    var knight = new Knight(Integer.toString(i), 10, 10, queue);
    knight.equip(sword);
    knight.waitTurn();
    i += 1;
    var staff = new Weapon("", 0, rng.nextInt(50), "STAFF");
    var thief = new Thief(Integer.toString(i), 10, 10, queue);
    thief.equip(staff);
    thief.waitTurn();
    i += 1;
    var axe = new Weapon("", 0, rng.nextInt(50), "AXE");
    var whitemage = new WhiteMage(Integer.toString(i), 10, 10, 10, queue);
    whitemage.equip(axe);
    whitemage.waitTurn();
    // endregion

    // region: TEST MODIFIYNG CHARACTERS
    Thread.sleep(3000);
    try {
      blackmage.setCurrentHp(rng.nextInt(50) - 10);
    } catch (LesserStatValueException e) {
      blackmage.setCurrentHp(0);
    } catch (GreaterStatValueException e) {
      blackmage.setCurrentHp(blackmage.getMaxHp());
    }
    // endregion
  }
}
