package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author ~Your name~
 */
public abstract class AbstractCharacter implements GameCharacter {

  protected int currentHp;
  protected final int maxHp;
  protected final int defense;
  protected final BlockingQueue<GameCharacter> turnsQueue;
  protected final String name;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param maxHp
   *     the character's max HP
   * @param defense
   *     the character's defense
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  protected AbstractCharacter(@NotNull String name, int maxHp, int defense,
      @NotNull BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException {
    Require.statValueAtLeast(1, maxHp, "Max HP");
    Require.statValueAtLeast(0, defense, "Defense");
    this.maxHp = maxHp;
    this.currentHp = maxHp;
    this.defense = defense;
    this.turnsQueue = turnsQueue;
    this.name = name;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter player) {
      scheduledExecutor.schedule(
          /* command = */ this::addToQueue,
          /* delay = */ player.getEquippedWeapon().getWeight() / 10,
          /* unit = */ TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor.schedule(
          /* command = */ this::addToQueue,
          /* delay = */ enemy.getWeight() / 10,
          /* unit = */ TimeUnit.SECONDS);
    }
  }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    try {
      turnsQueue.put(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    scheduledExecutor.shutdown();
  }

  // region : ACCESSORS
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the character's current HP to {@code newHp}.
   */
  @Override
  public void setCurrentHp(int newHp) throws InvalidStatValueException {
    Require.statValueAtLeast(0, newHp, "Current HP");
    Require.statValueAtMost(maxHp, newHp, "Current HP");
    currentHp = newHp;
  }

  /**
   * Returns the character's current HP.
   */
  @Override
  public int getCurrentHp() {
    return currentHp;
  }

  /**
   * Returns the character's max HP.
   */
  @Override
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * Returns the character's max HP.
   */
  @Override
  public int getDefense() {
    return defense;
  }


  // endregion
}
