package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

/**
 * A {@link Spell} is an item only equippable by {@link Mage}s. These can change a
 * {@link GameCharacter}'s state.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public interface Spell {

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
  void affect(GameCharacter character, BlackMage blackMage)
      throws RestrictedSpellException, InvalidStatValueException, MissingStatException;

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
  void affect(GameCharacter character, WhiteMage whiteMage)
      throws RestrictedSpellException, InvalidStatValueException, MissingStatException;

  /**
   * Equip this {@link Spell} to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@link BlackMage} to be equipped this {@link Spell}
   * @throws RestrictedSpellException
   *     if {@link BlackMage} is unable to equip this {@link Spell}
   */
  void equipTo(BlackMage blackMage) throws RestrictedSpellException;

  /**
   * Equip this {@link Spell} to a {@link WhiteMage}.
   *
   * @param whiteMage
   *     the {@link WhiteMage} to be equipped this {@link Spell}
   * @throws RestrictedSpellException
   *     if {@link WhiteMage} is unable to equip this {@link Spell}
   */
  void equipTo(WhiteMage whiteMage) throws RestrictedSpellException;

  /**
   * Return this {@link Spell}'s cost.
   */
  int getCost();
}
