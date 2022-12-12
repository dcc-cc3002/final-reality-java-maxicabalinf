package cl.uchile.dcc.finalreality.model.items.weapon;

import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import java.util.Objects;

/**
 * A {@link Weapon} equippable by {@link Mage}s.
 *
 * @author <a href="https://github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public final class Staff extends AbstractWeapon {
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
   * Tell if this Weapon is a Staff.
   */
  @Override
  public boolean isStaff() {
    return true;
  }

  /**
   * Returns the weapon's magic damage.
   */
  @Override
  public int getMagicDamage() {
    return magicDamage;
  }

  /**
   * Equips a {@code staff} to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@link BlackMage} to be added a {@code staff}
   */
  public void equipTo(BlackMage blackMage) {
    blackMage.setEquippedWeapon(this);
  }

  /**
   * Equips a {@code staff} to a {@link WhiteMage}.
   *
   * @param whiteMage
   *     the {@link WhiteMage} to be added a {@code staff}
   */
  public void equipTo(WhiteMage whiteMage) {
    whiteMage.setEquippedWeapon(this);
  }
  // endregion

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Staff staff)) {
      return false;
    }
    return hashCode() == staff.hashCode()
      && name.equals(staff.name)
      && damage == staff.damage
      && magicDamage == staff.magicDamage
      && weight == staff.weight
      && type.equals(staff.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Staff.class, name, damage, magicDamage, weight, type);
  }
}