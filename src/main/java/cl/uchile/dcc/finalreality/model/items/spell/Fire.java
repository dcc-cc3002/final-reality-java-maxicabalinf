package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;

public class Fire extends AbstractSpell {

  public Fire() {
    cost = 15;
  }

  /**
   * Applies the current {@link Spell} to a {@link GameCharacter}.
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
