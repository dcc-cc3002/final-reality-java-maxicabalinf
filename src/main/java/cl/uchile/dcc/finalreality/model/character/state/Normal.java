package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import java.util.Objects;

/**
 * A {@link GameCharacter} is in {@link Normal} state if no {@link Spell} is affecting it.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
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

  @Override
  public int hashCode() {
    return Objects.hash(Normal.class);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Normal that)) {
      return false;
    }
    return hashCode() == that.hashCode();
  }
}
