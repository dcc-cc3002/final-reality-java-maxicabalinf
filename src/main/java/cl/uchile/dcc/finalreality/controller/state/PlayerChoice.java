package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;

/**
 * A Final Reality game is in {@code EnemyChoice} state if the {@code actualCharacter} playing
 * its turn is a {@link PlayerCharacter}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class PlayerChoice extends AbstractTargetChoice {

  /**
   * Change a {@link PlayerCharacter}'s equipped {@link Weapon}.
   *
   * @param character
   *     the {@link PlayerCharacter} to have it's {@link Weapon} changed
   * @param weapon
   *     the new {@link Weapon}
   * @throws RestrictedWeaponException
   *     if the given {@link PlayerCharacter} is unable to equip such {@link Weapon}
   */
  @Override
  public void changeWeapon(PlayerCharacter character, Weapon weapon)
      throws RestrictedWeaponException {
    character.equip(weapon);
  }

  /**
   * Change a {@link Mage}'s equipped {@link Spell}.
   *
   * @param mage
   *     the {@link Mage} to have it's {@link Spell} changed
   * @param spell
   *     the new {@link Spell}
   * @throws RestrictedSpellException
   *     if the given {@link Mage} is unable to cast such {@link Spell}
   */
  @Override
  public void changeSpell(Mage mage, Spell spell) throws RestrictedSpellException {
    mage.equip(spell);
  }

  /**
   * Tell a {@link Mage} to cast its {@code equippedSpell} towards the chosen {@code target}.
   * After that, change the {@link GameController} {@code state} to {@link FinishedTurn}.
   *
   * @param mage
   *     the {@link Mage} who will cast the {@link Spell}
   * @throws RestrictedSpellException
   *     if the given {@link Mage} is unable to cast such {@link Spell}
   * @throws InvalidStatValueException
   *     if the {@code target}'s {@code Hp} turns out to be invalid in the process
   * @throws MissingStatException
   *     if the given {@link Mage} is not carrying a {@link Staff}
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void cast(Mage mage)
      throws RestrictedSpellException, InvalidStatValueException,
      MissingStatException, InvalidTransitionException {
    mage.cast(target);
    changeState(new FinishedTurn());
  }

  /**
   * Check if the {@code game} is in {@link PlayerChoice} state.
   */
  @Override
  public boolean inPlayerChoice() {
    return true;
  }
}
