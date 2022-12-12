package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

/**
 * A class that holds all the information of a {@link Spell}.
 * All {@link Spell}s have a {@code cost}, and can only be cast by a {@link Mage}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public abstract class AbstractSpell implements Spell {

  protected int cost;

  /**
   * Return this {@link Spell}'s cost.
   */
  @Override
  public int getCost() {
    return cost;
  }

  /**
   * Apply the current {@link Spell} with a {@link BlackMage}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param blackMage
   *     the {@link BlackMage} who casts the current {@link Spell}
   * @throws RestrictedSpellException
   *     when the {@link BlackMage} cast an unavailable {@link Spell} for its class
   */
  public void affect(GameCharacter character, BlackMage blackMage)
    throws RestrictedSpellException, InvalidStatValueException, MissingStatException, NullWeaponException {
    throw new RestrictedSpellException(
      "%ss cannot cast %ss.".formatted(
        blackMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  /**
   * Apply the current {@link Spell} with a {@link WhiteMage}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param whiteMage
   *     the {@link WhiteMage} who casts the current {@link Spell}
   * @throws RestrictedSpellException
   *     when the {@link WhiteMage} cast an unavailable {@link Spell} for its class
   */
  public void affect(GameCharacter character, WhiteMage whiteMage)
    throws RestrictedSpellException, InvalidStatValueException, MissingStatException, NullWeaponException {
    throw new RestrictedSpellException(
      "%ss cannot cast %ss.".formatted(
        whiteMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
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
    throw new RestrictedSpellException(
      "%ss cannot equip %ss.".formatted(
        blackMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
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
    throw new RestrictedSpellException(
      "%ss cannot equip %ss.".formatted(
        whiteMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }
}
