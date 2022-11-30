package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Fire;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;

/**
 * A {@link GameCharacter} is {@code burnt} when it was applied the {@link Fire} {@link Spell}
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Burnt extends AbstractCharacterState {

  /**
   * Tell if the associated character is {@link Burnt}.
   *
   * @return
   *     true as this class is instantiated by some {@link GameCharacter}
   */
  @Override
  public boolean isBurnt() {
    return true;
  }
}
