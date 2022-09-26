package cl.uchile.dcc.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 */
public class Weapon {

  protected final String name;
  protected final int damage;
  protected final int weight;
  protected final String type;

  /**
   * Creates a new weapon.
   *
   * @param name
   *     the character's name
   * @param damage
   *     the character's max hp
   * @param weight
   *     the character's defense
   * @param type
   *     the queue with the characters waiting for their turn
   */
  public Weapon(final String name, final int damage, final int weight,
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

  private String getName() {
    return name;
  }

  /**
   * Returns the weapon's damage.
   */
  private int getDamage() {
    return damage;
  }

  /**
   * Returns the weapon's weight.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the weapon's type.
   */
  private String getType() {
    return type;
  }
  // endregion

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Weapon weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
        && damage == weapon.damage
        && weight == weapon.weight
        && name.equals(weapon.name)
        && type.equals(weapon.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Weapon.class, name, damage, weight, type);
  }

  @Override
  public String toString() {
    return "Weapon{name='%s', damage=%d, weight=%d, type=%s}"
        .formatted(name, damage, weight, type);
  }
}
