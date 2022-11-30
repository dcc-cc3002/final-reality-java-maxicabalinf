package cl.uchile.dcc.finalreality.model.items.weapon;

import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.normal.Knight;
import cl.uchile.dcc.finalreality.model.character.player.normal.Thief;
import java.util.Objects;

/**
 * A {@link Weapon} equippable by {@link Knight}s, {@link Thief}s and {@link BlackMage}s.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Knife extends AbstractWeapon {

  /**
   * Creates a new weapon.
   *
   * @param name
   *     the knife's name
   * @param damage
   *     the knife's max hp
   * @param weight
   *     the knife's defense
   */
  public Knife(final String name, final int damage, final int weight) {
    super(name, damage, weight, "KNIFE");
  }

  /**
   * Equips a {@code knife} to a {@link Knight}.
   *
   * @param knight
   *     the {@code knight} to be equipped a {@code knife}
   */
  @Override
  public void equipTo(Knight knight) {
    knight.setEquippedWeapon(this);
  }

  /**
   * Equips a {@code knife} to a {@link Thief}.
   *
   * @param thief
   *     the {@code thief} to be equipped a {@code knife}
   */
  @Override
  public void equipTo(Thief thief) {
    thief.setEquippedWeapon(this);
  }

  /**
   * Equips a {@code knife} to a {@link BlackMage}.
   *
   * @param blackMage
   *     the {@code blackMage} to be equipped a {@code knife}
   */
  @Override
  public void equipTo(BlackMage blackMage) {
    blackMage.setEquippedWeapon(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Knife knife)) {
      return false;
    }
    return hashCode() == knife.hashCode()
      && name.equals(knife.name)
      && damage == knife.damage
      && weight == knife.weight
      && type.equals(knife.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Knife.class, name, damage, weight, type);
  }
}
