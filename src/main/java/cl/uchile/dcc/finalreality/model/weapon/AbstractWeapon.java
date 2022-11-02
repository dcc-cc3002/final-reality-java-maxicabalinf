package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;

/**
 * An abstract class that holds the common behaviour of all {@link Weapon}s in the game.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public abstract class AbstractWeapon implements Weapon {

  protected final String name;
  protected final int damage;
  protected final int weight;
  protected final String type;

  /**
   * Creates a new weapon.
   *
   * @param name
   *     the weapon's name
   * @param damage
   *     the weapon's max hp
   * @param weight
   *     the weapon's defense
   * @param type
   *     the weapon's type
   */
  public AbstractWeapon(final String name, final int damage, final int weight,
                        final String type) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.type = type;
  }

  // region: UTILITY METHODS
  /**
   * Returns the weapon's name.
   */

  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the weapon's damage.
   */
  @Override
  public int getDamage() {
    return damage;
  }

  /**
   * Returns the weapon's magic damage.
   */
  @Override
  public int getMagicDamage() throws MissingStatException {
    throw new MissingStatException(this.getClass().getSimpleName() + " does not have magic damage.");
  }

  /**
   * Returns the weapon's weight.
   */
  @Override
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the weapon's type.
   */
  @Override
  public String getType() {
    return type;
  }

  @Override
  public void equipTo(Thief thief) throws RestrictedWeaponException {
    throw new RestrictedWeaponException(
      "%ss cannot equip %ss.".formatted(
        thief.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  @Override
  public void equipTo(Engineer engineer) throws RestrictedWeaponException {
    throw new RestrictedWeaponException(
      "%ss cannot equip %ss.".formatted(
        engineer.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  @Override
  public void equipTo(Knight knight) throws RestrictedWeaponException {
    throw new RestrictedWeaponException(
      "%ss cannot equip %ss.".formatted(
        knight.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  @Override
  public void equipTo(BlackMage blackMage) throws RestrictedWeaponException {
    throw new RestrictedWeaponException(
      "%ss cannot equip %ss.".formatted(
        blackMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }

  @Override
  public void equipTo(WhiteMage whiteMage) throws RestrictedWeaponException {
    throw new RestrictedWeaponException(
      "%ss cannot equip %ss.".formatted(
        whiteMage.getClass().getSimpleName(), this.getClass().getSimpleName()
      )
    );
  }
  // endregion

  @Override
  public String toString() {
    return "Weapon{name='%s', damage=%d, weight=%d, type=%s}"
        .formatted(name, damage, weight, type);
  }
}
