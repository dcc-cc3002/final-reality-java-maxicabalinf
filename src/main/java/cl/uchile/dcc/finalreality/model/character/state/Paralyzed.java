package cl.uchile.dcc.finalreality.model.character.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Paralyze;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import java.util.Objects;

/**
 * A {@link GameCharacter} is {@link Paralyzed} when it was applied the
 * {@link Paralyze} {@link Spell}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Paralyzed extends AbstractCharacterState {

  /**
   * A {@link GameCharacter} is {@link Envenomed} when it was applied the
   * {@link Paralyze} {@link Spell}.
   *
   * @return
   *     true as this class is instantiated by some {@link GameCharacter}
   */
  @Override
  public boolean isParalyzed() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Paralyzed.class);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Paralyzed that)) {
      return false;
    }
    return hashCode() == that.hashCode();
  }
}
