package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
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
    var thief = new Thief(Integer.toString(i), 10, 10, queue);
    var staff1 = new Weapon("1", 0, 12, "STAFF");
    var staff2 = new Weapon("2", 0, 20, "STAFF");
    var staff3 = new Weapon("2", 0, 20, "STAFF");
    thief.equip(staff1);
    thief.waitTurn();

    i += 1;
    var engineer = new Engineer(Integer.toString(i), 10, 10, queue);
    var bow = new Weapon("", 0, rng.nextInt(50), "BOW");
    engineer.equip(bow);
    engineer.waitTurn();

    i += 1;
    var knight = new Knight(Integer.toString(i), 10, 10, queue);
    var sword = new Weapon("", 0, rng.nextInt(50), "SWORD");
    knight.equip(sword);
    knight.waitTurn();

    i += 1;
    var knife = new Weapon("", 0, rng.nextInt(50), "KNIFE");
    var blackmage1 = new BlackMage(Integer.toString(i), 10, 10, 10, queue);
    var blackmage2 = new BlackMage(Integer.toString(i), 10, 10, 10, queue);
    blackmage1.equip(knife);
    blackmage1.waitTurn();

    i += 1;
    var whitemage1 = new WhiteMage(Integer.toString(i), 10, 10, 10, queue);
    var whitemage2 = new WhiteMage(Integer.toString(0), 10, 10, 10, queue);
    var axe = new Weapon("", 0, rng.nextInt(50), "AXE");
    whitemage1.equip(axe);
    whitemage1.waitTurn();

    var enemy1 = new Enemy(Integer.toString(1), 10, 10, 10, queue);
    var enemy2 = new Enemy(Integer.toString(2), 10, 10, 10, queue);
    var enemy3 = new Enemy(Integer.toString(2), 10, 10, 10, queue);
    // endregion

    // region: TEST NEW METHODS SINCE MASTER
    try { // Exception handling
      whitemage1.setCurrentHp(5);
    } catch (InvalidStatValueException e) {
      whitemage1.setCurrentHp(e.bound);
    }

    // Testing WhiteMage equals
    System.out.println("Testing WhiteMage equals: ");
    System.out.println("whitemage1 equals whitemage2?: " + whitemage1.equals(blackmage2));
    System.out.println(whitemage1.toString());
    System.out.println(whitemage2.toString() + "\n");

    try { // Exception handling
      blackmage1.setCurrentHp(5);
    } catch (InvalidStatValueException e) {
      blackmage1.setCurrentHp(e.bound);
    }

    // Testing BlackMage equals
    System.out.println("Testing BlackMage equals: ");
    System.out.println("blackmage1 equals blackmage2?: " + blackmage1.equals(blackmage2));
    System.out.println(blackmage1.toString());
    System.out.println(blackmage2.toString() + "\n");

    System.out.println("blackmage1 equals whitemage2?: " + blackmage1.equals(whitemage2));
    System.out.println(blackmage1.toString());
    System.out.println(whitemage2.toString() + "\n");

    // Exception handling confimation
    System.out.println("Exception handling confimation: ");
    System.out.println("blackmage1 current Hp: " + blackmage1.getCurrentHp());
    try { // Exception handling
      blackmage1.setCurrentHp(-1);
    } catch (InvalidStatValueException e) {
      blackmage1.setCurrentHp(e.bound);
    }
    System.out.println("blackmage1 current Hp: " + blackmage1.getCurrentHp() + "\n");
    try { // Exception handling
      blackmage1.setCurrentHp(11);
    } catch (InvalidStatValueException e) {
      blackmage1.setCurrentHp(e.bound);
    }

    // Testing characters equals
    System.out.println("Testing characters equals: ");
    System.out.println("blackmage1 current Hp: " + blackmage1.getCurrentHp());
    System.out.println("blackmage1 equals blackmage2?: " + blackmage1.equals(blackmage2));
    System.out.println(blackmage1.toString());
    System.out.println(blackmage2.toString() + "\n");

    System.out.println("engineer equals blackmage2?: " + engineer.equals(blackmage2));
    System.out.println(engineer.toString());
    System.out.println(blackmage2.toString() + "\n");

    System.out.println("thief equals knight?: " + thief.equals(knight));
    System.out.println(thief.toString());
    System.out.println(knight.toString() + "\n");

    System.out.println("knight equals whitemage2?: " + knight.equals(whitemage2));
    System.out.println(knight.toString());
    System.out.println(whitemage2.toString() + "\n");

    System.out.println("enemy1 equals enemy2?: " + staff1.equals(enemy2));
    System.out.println(enemy1.toString());
    System.out.println(enemy2.toString() + "\n");

    System.out.println("enemy2 equals enemy3?: " + enemy2.equals(enemy3));
    System.out.println(enemy2.toString());
    System.out.println(enemy3.toString() + "\n");

    System.out.println("staff1 equals staff2?: " + staff1.equals(staff2));
    System.out.println(staff1.toString());
    System.out.println(staff2.toString() + "\n");

    // Testing new weapon implementation
    System.out.println("Testing new weapon implementation: ");
    System.out.println("staff2 equals staff3?: " + staff2.equals(staff3));
    System.out.println(staff2.toString());
    System.out.println(staff3.toString() + "\n");

    Thread.sleep(6000);
    System.out.println("Characters:");
    while (!queue.isEmpty()) {
      // Pops and prints the names of the characters of the queue to illustrate the turns
      // order
      System.out.println(queue.poll().toString());
    }
    // endregion
  }
}
