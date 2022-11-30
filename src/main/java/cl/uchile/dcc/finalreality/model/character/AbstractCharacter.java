package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.state.Burnt;
import cl.uchile.dcc.finalreality.model.character.state.CharacterState;
import cl.uchile.dcc.finalreality.model.character.state.Envenomed;
import cl.uchile.dcc.finalreality.model.character.state.Normal;
import cl.uchile.dcc.finalreality.model.character.state.Paralyzed;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
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
  protected ScheduledExecutorService scheduledExecutor;
  protected CharacterState actualState;

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
    this.actualState = new Normal(); //TODO implement StateFactory and singleton for StateFactory
  }

  /**
   * Creates a new {@code thread} to add a {@link GameCharacter} to a {@code queue} after some
   * {@code delay} depending on its {@code weight}.
   *
   */
  @Override
  public abstract void waitTurn();

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
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

  // region : STATES
  /**
   * Change this character's state.
   *
   * @param state
   *     the new character's state
   */
  public void changeState(CharacterState state) {
    actualState = state;
    actualState.setCharacter(this);
  }

  /**
   * Attack another {@link GameCharacter}.
   */
  public abstract void attack(GameCharacter character) throws InvalidStatValueException;

  /**
   * Recieve attack from another {@link GameCharacter}. Reduces the characters {@code Hp}.
   */
  public void beAttacked(int damage) throws InvalidStatValueException {
    setCurrentHp(Math.max(getCurrentHp() - damage, 0));
  }

  /**
   * Tell if this character is {@link Burnt}.
   *
   * @return
   *     wether it is {@link Burnt} or not
   */
  public boolean isBurnt() {
    return actualState.isBurnt();
  }

  /**
   * Tell if this character is {@link Envenomed}.
   *
   * @return
   *     wether it is {@link Envenomed} or not
   */
  public boolean isEnvenomed() {
    return actualState.isEnvenomed();
  }

  /**
   * Tell if this character is {@link Normal}.
   *
   * @return
   *     wether it is {@link Normal} or not
   */
  public boolean isNormal() {
    return actualState.isNormal();
  }

  /**
   * Tell if this character is {@link Paralyzed}.
   *
   * @return
   *     wether it is {@link Paralyzed} or not
   */
  public boolean isParalyzed() {
    return actualState.isParalyzed();
  }
  // endregion
}
