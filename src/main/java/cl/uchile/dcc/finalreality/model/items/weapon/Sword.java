package cl.uchile.dcc.finalreality.model.items.weapon;

import cl.uchile.dcc.finalreality.model.character.player.normal.Knight;
import cl.uchile.dcc.finalreality.model.character.player.normal.Thief;
import java.util.Objects;

/**
 * A {@link Weapon} equippable by {@link Knight}s and {@link Thief}s.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Sword extends AbstractWeapon {

  /**
   * Creates a new weapon.
   *
   * @param name
   *     the sword's name
   * @param damage
   *     the sword's max hp
   * @param weight
   *     the sword's defense
   */
  public Sword(final String name, final int damage, final int weight) {
    super(name, damage, weight, "SWORD");
  }

  /**
   * Equips a {@code sword} to a {@link Knight}.
   *
   * @param knight
   *     the {@link Knight} to be equipped a {@code sword}
   */
  public void equipTo(Knight knight) {
    knight.setEquippedWeapon(this);
  }

  /**
   * Equips a {@code sword} to a {@link Thief}.
   *
   * @param thief
   *     the {@link Thief} to be equipped a {@code sword}
   */
  public void equipTo(Thief thief) {
    thief.setEquippedWeapon(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Sword sword)) {
      return false;
    }
    return hashCode() == sword.hashCode()
      && name.equals(sword.name)
      && damage == sword.damage
      && weight == sword.weight
      && type.equals(sword.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Sword.class, name, damage, weight, type);
  }
}
