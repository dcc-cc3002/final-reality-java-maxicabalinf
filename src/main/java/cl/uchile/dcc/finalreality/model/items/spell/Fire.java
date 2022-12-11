package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.state.Burnt;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;

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
      throws InvalidStatValueException, MissingStatException {
    character.beAttacked(blackMage.getEquippedWeapon().getMagicDamage());
    character.changeState(new Burnt());
    // TODO implement probability
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
}
