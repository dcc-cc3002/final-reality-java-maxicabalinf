package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

public class Cure extends AbstractSpell {

  public Cure() {
    cost = 15;
  }

  /**
   * Applies the current {@link Spell} to a {@link GameCharacter}. Discounts 15 Mp to the
   * {@link BlackMage} who cast this {@link Spell}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param blackMage
   *     the {@link BlackMage} who casts the current {@link Spell}
   * @throws RestrictedSpellException
   *     when the {@link BlackMage} cast an unavailable {@link Spell} for its class
   */
  @Override
  public void affect(GameCharacter character, BlackMage blackMage)
    throws InvalidStatValueException, MissingStatException {
    character.beAttacked(blackMage.getEquippedWeapon().getMagicDamage());
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
}
