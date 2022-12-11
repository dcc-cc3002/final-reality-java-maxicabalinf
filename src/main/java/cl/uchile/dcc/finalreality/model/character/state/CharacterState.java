package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;

/**
 * CharacterState's restrict {@link GameCharacter}'s abilities for a period of time,
 * usually one turn.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public interface CharacterState {

  /**
   * Associate a {@link GameCharacter} to this {@link CharacterState}.
   *
   * @param character
   *     the {@link GameCharacter} to be associated
   */
  void setCharacter(GameCharacter character);

  /**
   * Tell if the associated {@link GameCharacter} is {@link Burnt}.
   *
   * @return
   *     wether it is {@link Burnt} or not
   */
  boolean isBurnt();

  /**
   * Tell if the associated {@link GameCharacter} is {@link Envenomed}.
   *
   * @return
   *     wether it is {@link Envenomed} or not
   */
  boolean isEnvenomed();

  /**
   * Tell if the associated {@link GameCharacter} is {@link Normal}.
   *
   * @return
   *     wether it is {@link Normal} or not
   */
  boolean isNormal();

  /**
   * Tell if the associated {@link GameCharacter} is {@link Paralyzed}.
   *
   * @return
   *     wether it is {@link Paralyzed} or not
   */
  boolean isParalyzed();
}
