package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import cl.uchile.dcc.finalreality.model.items.weapon.AbstractWeapon;

/**
 * A class that holds all the information of a player-controlled character in the game.
 *
 * <p>All player characters have a {@code name}, a maximum amount of <i>hit points</i>
 * ({@code maxHp}), a {@code defense} value, a queue of {@link GameCharacter}s that are
 * waiting for their turn ({@code turnsQueue}), and can equip a {@link AbstractWeapon}.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author ~Your name~
 */
public abstract class AbstractSpell implements Spell {

  protected int cost;

  /**
   * Return this {@link Spell}'s cost.
   */
  public int getCost() {
    return cost;
  }

  /**
   * Applies the current {@link Spell} to a {@link BlackMage}.
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
    throw new RestrictedSpellException(
      "%ss cannot cast %ss.".formatted(
        blackMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  /**
   * Applies the current {@link Spell} to a {@link WhiteMage}.
   *
   * @param character
   *     the {@link GameCharacter} to be affected
   * @param whiteMage
   *     the {@link WhiteMage} who casts the current {@link Spell}
   * @throws RestrictedSpellException
   *     when the {@link WhiteMage} cast an unavailable {@link Spell} for its class
   */
  public void affect(GameCharacter character, WhiteMage whiteMage)
      throws RestrictedSpellException {
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
