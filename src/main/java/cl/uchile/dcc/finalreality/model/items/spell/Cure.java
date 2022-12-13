package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import java.util.Objects;

/**
 * A {@link Spell} that adds to the target's {@code Hp} 30% of its {@code maxHp}.
 * Costs 15 {@code Mp}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class Cure extends AbstractSpell {

  /**
   * Set this {@link Spell} cost.
   */
  public Cure() {
    cost = 15;
  }

  /**
   * Apply this {@link Spell} to a {@link GameCharacter}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param whiteMage
   *     the {@link BlackMage} who casts the current {@link Spell}
   */
  @Override
  public void affect(GameCharacter character, WhiteMage whiteMage)
      throws InvalidStatValueException, MissingStatException, NullWeaponException {
    character.beAttacked(- (int) Math.floor(character.getMaxHp() * 0.3));
  }

  /**
   * Equip this {@link Spell} to a {@link WhiteMage}.
   *
   * @param whiteMage
   *     the {@link WhiteMage} to be equipped this {@link Spell}
   * @throws RestrictedSpellException
   *     if {@link WhiteMage} is unable to equip this {@link Spell}
   */
  @Override
  public void equipTo(WhiteMage whiteMage) throws RestrictedSpellException {
    whiteMage.setEquippedSpell(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Cure.class, cost);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Cure that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && cost == that.cost;
  }
}
