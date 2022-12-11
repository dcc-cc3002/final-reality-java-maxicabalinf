package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;

/**
 * A {@link GameCharacter} is in {@link Normal} state if no {@link Spell} is affecting it.
 */
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
