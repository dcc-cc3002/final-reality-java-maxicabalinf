package cl.uchile.dcc.finalreality.model.items.spell;

import static cl.uchile.dcc.finalreality.controller.GameController.rand;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.state.Burnt;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import java.util.Objects;

/**
 * A {@link Spell} that makes the target go to a {@link Burnt} state. Reduces the target's
 * {@code Hp} by the {@link Staff}'s {@code magicDamage}. Has a 20% chance of success and
 * costs 15 Mp.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class Fire extends AbstractSpell {

  /**
   * Set this {@link Spell} cost.
   */
  public Fire() {
    cost = 15;
  }

  /**
   * Apply the current {@link Spell} to a {@link GameCharacter}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param blackMage
   *     the {@link BlackMage} who casts the current {@link Spell}
   */
  @Override
  public void affect(GameCharacter character, BlackMage blackMage)
      throws InvalidStatValueException, MissingStatException, NullWeaponException {
    character.beAttacked(blackMage.getEquippedWeapon().getMagicDamage());
    if (rand.nextInt(10) < 2) {
      character.changeState(new Burnt());
    }
  }

  /**
   * Equip this {@link Spell} to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@link BlackMage} to be equipped this {@link Spell}
   */
  @Override
  public void equipTo(BlackMage blackMage) {
    blackMage.setEquippedSpell(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Fire.class, cost);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Fire that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && cost == that.cost;
  }
}
