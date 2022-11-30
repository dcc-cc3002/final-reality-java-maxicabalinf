package cl.uchile.dcc.finalreality.model.character.state;

//TODO add javadoc

import cl.uchile.dcc.finalreality.model.character.GameCharacter;

public class Paralyzed extends AbstractCharacterState {

  /**
   * Tell if the associated character is {@link Paralyzed}.
   *
   * @return
   *     true as this class is instantiated by some {@link GameCharacter}
   */
  @Override
  public boolean isParalyzed() {
    return true;
  }
}
