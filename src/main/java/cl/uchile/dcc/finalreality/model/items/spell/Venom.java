package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import cl.uchile.dcc.finalreality.model.character.state.Envenomed;

/**
 * A {@link Spell} that makes the target go to a {@link Envenomed} state. Costs 40 Mp.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class Venom extends AbstractSpell {

  /**
   * Set this {@link Spell} cost.
   */
  public Venom() {
    cost = 40;
  }

  /**
   * Apply this {@link Spell} to a {@link GameCharacter}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param whiteMage
   *     the {@link WhiteMage} who casts the current {@link Spell}
   */
  @Override
  public void affect(GameCharacter character, WhiteMage whiteMage) {
    character.changeState(new Envenomed());
  }

  /**
   * Equip this {@link Spell} to a {@link WhiteMage}.
   *
   * @param whiteMage
   *     the {@link WhiteMage} to be equipped this {@link Spell}
   */
  @Override
  public void equipTo(WhiteMage whiteMage) {
    whiteMage.setEquippedSpell(this);
  }
}
