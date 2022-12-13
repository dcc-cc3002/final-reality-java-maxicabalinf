package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;

/**
 * A class that holds all the information of a {@link CharacterState} character in the game.
 * {@link CharacterState}s have an associated {@link GameCharacter} whose abilities get
 * restricted depending on the state type.
 *
 * @author <a href="https://github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public abstract class AbstractCharacterState implements CharacterState {

  private GameCharacter character;

  /**
   * Associate a {@link GameCharacter} to this {@link CharacterState}.
   *
   * @param character
   *     the {@link GameCharacter} who gets associated
   */
  public void setCharacter(GameCharacter character) {
    this.character = character;
  }

  /**
   * Return the associated character of this state.
   */
  @Override
  public GameCharacter getCharacter() {
    return character;
  }

  /**
   * Change the state of the associated {@link GameCharacter}.
   *
   * @param state
   *     the {@link CharacterState} to update to the actual associated {@link GameCharacter}
   */
  @Override
  public void changeState(CharacterState state) {
    character.changeState(state);
  }

  /**
   * Tell if the associated {@link GameCharacter} is {@link Burnt}.
   *
   * @return
   *     wether it is {@link Burnt} or not
   */
  public boolean isBurnt() {
    return false;
  }

  /**
   * Tell if the associated {@link GameCharacter} is {@link Envenomed}.
   *
   * @return
   *     wether it is {@link Envenomed} or not
   */
  public boolean isEnvenomed() {
    return false;
  }

  /**
   * Tell if the associated {@link GameCharacter} is {@link Normal}.
   *
   * @return
   *     wether it is {@link Normal} or not
   */
  public boolean isNormal() {
    return false;
  }

  /**
   * Tell if the associated {@link GameCharacter} is {@link Paralyzed}.
   *
   * @return
   *     wether it is {@link Paralyzed} or not
   */
  public boolean isParalyzed() {
    return false;
  }
}
