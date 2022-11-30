package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.state.Burnt;
import cl.uchile.dcc.finalreality.model.character.state.CharacterState;
import cl.uchile.dcc.finalreality.model.character.state.Envenomed;
import cl.uchile.dcc.finalreality.model.character.state.Normal;
import cl.uchile.dcc.finalreality.model.character.state.Paralyzed;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 */
public interface GameCharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's current HP.
   */
  int getCurrentHp();

  /**
   * Returns this character's max HP.
   */
  int getMaxHp();

  /**
   * Returns this character's defense.
   */
  int getDefense();

  /**
   * Sets this character's current HP to {@code newHp}.
   */
  void setCurrentHp(int hp) throws InvalidStatValueException;

  /**
   * Change this character's state.
   */
  void changeState(CharacterState state);

  /**
   * Tell if this character is {@link Burnt}.
   *
   * @return
   *     wether it is {@link Burnt} or not
   */
  boolean isBurnt();

  /**
   * Tell if this character is {@link Envenomed}.
   *
   * @return
   *     wether it is {@link Envenomed} or not
   */
  boolean isEnvenomed();

  /**
   * Tell if this character is {@link Normal}.
   *
   * @return
   *     wether it is {@link Normal} or not
   */
  boolean isNormal();

  /**
   * Tell if this character is {@link Paralyzed}.
   *
   * @return
   *     wether it is {@link Paralyzed} or not
   */
  boolean isParalyzed();
}
