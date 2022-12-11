package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;

/**
 * A {@link Spell} that reduces the target's Hp by the {@link Staff}'s {@code magicDamage}. Has a
 * 30% chance of success, and costs 15 Mp.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class Thunder extends AbstractSpell {

  /**
   * Set this {@link Spell} cost.
   */
  public Thunder() {
    cost = 15;
  }

  /**
   * Apply the current {@link Spell} to a {@link GameCharacter}. Discounts 15 Mp to the
   * {@link BlackMage} who cast this {@link Spell}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param blackMage
   *     the {@link BlackMage} who casts the current {@link Spell}
   * @throws RestrictedSpellException
   *     when the {@link BlackMage} cast an unavailable {@link Spell} for its class
   */
  public void affect(GameCharacter character, BlackMage blackMage)
      throws RestrictedSpellException, InvalidStatValueException, MissingStatException {
    character.beAttacked(blackMage.getEquippedWeapon().getMagicDamage());
    // TODO implement probability
  }

  /**
   * Equip this {@link Spell} to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@link BlackMage} to be equipped this {@link Spell}
   * @throws RestrictedSpellException
   *     if {@link BlackMage} is unable to equip this {@link Spell}
   */
  @Override
  public void equipTo(BlackMage blackMage) throws RestrictedSpellException {
    blackMage.setEquippedSpell(this);
  }
}
