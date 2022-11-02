package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;

/**
 * {@code Weapon}s are objects used by {@link PlayerCharacter}s to attack {@link GameCharacter}s.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public interface Weapon {

  /**
   * Returns the weapon's name.
   */
  String getName();

  /**
   * Returns the weapon's damage.
   */
  int getDamage();

  /**
   * Returns the weapon's magic damage.
   *
   * @throws MissingStatException
   *     when a weapon does not have magic damage
   */
  int getMagicDamage() throws MissingStatException;

  /**
   * Returns the weapon's weight.
   */
  int getWeight();

  /**
   * Returns the weapon's type.
   */
  String getType();

  /**
   * Equips a weapon to a {@link Thief}.
   *
   * @param thief
   *     the {@link Thief} to be equipped a weapon
   * @throws RestrictedWeaponException
   *     if {@link Thief} is unable to equip such weapon
   */
  void equipTo(Thief thief) throws RestrictedWeaponException;

  /**
   * Equips a weapon to a {@link Engineer}.
   *
   * @param engineer
   *     the {@link Engineer} to be equipped a weapon
   * @throws RestrictedWeaponException
   *     if {@link Engineer} is unable to equip such weapon
   */
  void equipTo(Engineer engineer) throws RestrictedWeaponException;

  /**
   * Equips a weapon to a {@link Knight}.
   *
   * @param knight
   *     the {@link Knight} to be equipped a weapon
   * @throws RestrictedWeaponException
   *     if {@link Knight} is unable to equip such weapon
   */
  void equipTo(Knight knight) throws RestrictedWeaponException;

  /**
   * Equips a weapon to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@link BlackMage} to be equipped a weapon
   * @throws RestrictedWeaponException
   *     if {@link BlackMage} is unable to equip such weapon
   */
  void equipTo(BlackMage blackMage) throws RestrictedWeaponException;

  /**
   * Equips a weapon to a {@link WhiteMage}.
   *
   * @param whiteMage
   *     the {@link WhiteMage} to be equipped a weapon
   * @throws RestrictedWeaponException
   *     if {@link WhiteMage} is unable to equip such weapon
   */
  void equipTo(WhiteMage whiteMage) throws RestrictedWeaponException;
}
