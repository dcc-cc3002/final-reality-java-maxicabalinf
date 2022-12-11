package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.spell.Venom;

/**
 * A {@link GameCharacter} is {@link Envenomed} when it was applied the {@link Venom} {@link Spell}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Envenomed extends AbstractCharacterState {

  /**
   * Tell if the associated character is {@link Envenomed}.
   *
   * @return
   *     true as this class is instantiated by some {@link GameCharacter}
   */
  @Override
  public boolean isEnvenomed() {
    return true;
  }
}
