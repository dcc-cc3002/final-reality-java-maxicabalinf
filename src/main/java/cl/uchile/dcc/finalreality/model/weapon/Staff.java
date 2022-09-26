package cl.uchile.dcc.finalreality.model.weapon;

/**
 * A.
 *
 * @author <a href="https://github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public final class Staff extends Weapon {
  private final int magicDamage;

  /**
   * Creates a new staff.
   *
   * @param name
   *     the staff's name
   * @param damage
   *     the staff's damage
   * @param magicDamage
   *     the staff's magic damage
   * @param weight
   *     the staff's weight
   */
  public Staff(final String name, final int damage, final int magicDamage, final int weight) {
    super(name, damage, weight, "STAFF");
    this.magicDamage = magicDamage;
  }

  // region: UTILITY METHODS
  /**
   * Returns the weapon's magic damage.
   */
  private int getMagicDamage() {
    return magicDamage;
  }
  // endregion

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o.getClass() == this.getClass())) {
      return false;
    }
    Staff staff = (Staff) o;
    return hashCode() == staff.hashCode()
      && damage == staff.damage
      && magicDamage == staff.magicDamage
      && weight == staff.weight
      && name.equals(staff.name)
      && type.equals(staff.type);
  }
}
