package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

public class Paralyze extends AbstractSpell {

  public Paralyze() {
    cost = 25;
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
