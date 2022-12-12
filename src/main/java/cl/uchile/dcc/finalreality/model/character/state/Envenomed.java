package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.spell.Venom;
import java.util.Objects;

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

  @Override
  public int hashCode() {
    return Objects.hash(Envenomed.class);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Envenomed that)) {
      return false;
    }
    return hashCode() == that.hashCode();
  }
}