package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

public class Venom extends AbstractSpell {

  public Venom() {
    cost = 40;
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
