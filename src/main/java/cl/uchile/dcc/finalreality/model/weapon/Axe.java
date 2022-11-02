package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import java.util.Objects;

/**
 * A {@link Weapon} equippable by {@link Knight}s and {@link Engineer}s.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a new {@code axe}.
   *
   * @param name
   *     the axe's name
   * @param damage
   *     the axe's max hp
   * @param weight
   *     the axe's defense
   */
  public Axe(final String name, final int damage, final int weight) {
    super(name, damage, weight, "AXE");
  }

  /**
   * Equips an {@code axe} to a {@code knight}.
   *
   * @param knight
   *     the {@code kight} to be equipped an axe
   */
  public void equipTo(Knight knight) {
    knight.setEquippedWeapon(this);
  }

  /**
   * Equips an {@code axe} to an {@code engineer}.
   *
   * @param engineer
   *     the {@code engineer} to be equipped an axe
   */
  public void equipTo(Engineer engineer) {
    engineer.setEquippedWeapon(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Axe axe)) {
      return false;
    }
    return hashCode() == axe.hashCode()
      && damage == axe.damage
      && weight == axe.weight
      && name.equals(axe.name)
      && type.equals(axe.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Axe.class, name, damage, weight, type);
  }
}
