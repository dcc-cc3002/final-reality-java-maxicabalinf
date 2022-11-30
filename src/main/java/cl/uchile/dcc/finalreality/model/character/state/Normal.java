package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;

public class Normal extends AbstractCharacterState {

  /**
   * Tell if the associated character is {@link Normal}.
   *
   * @return
   *     true as this class is instantiated by some {@link GameCharacter}
   */
  @Override
  public boolean isNormal() {
    return true;
  }
}
