package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import java.util.Objects;

/**
 * A {@link Weapon} equippable by {@link Engineer}s and {@link Thief}s.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class Bow extends AbstractWeapon {

  /**
   * Creates a new {@code bow}.
   *
   * @param name
   *     the bow's name
   * @param damage
   *     the bow's max hp
   * @param weight
   *     the bow's defense
   */
  public Bow(final String name, final int damage, final int weight) {
    super(name, damage, weight, "BOW");
  }

  /**
   * Equips a {@code bow} to an {@link Engineer}.
   *
   * @param engineer
   *     the {@link Engineer} to be equipped a {@code bow}
   */
  public void equipTo(Engineer engineer) {
    engineer.setEquippedWeapon(this);
  }

  /**
   * Equips a {@code bow} to a {@link Thief}.
   *
   * @param thief
   *     the {@link Thief} to be equipped a {@code bow}
   */
  public void equipTo(Thief thief) {
    thief.setEquippedWeapon(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Bow bow)) {
      return false;
    }
    return hashCode() == bow.hashCode()
      && name.equals(bow.name)
      && damage == bow.damage
      && weight == bow.weight
      && type.equals(bow.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Bow.class, name, damage, weight, type);
  }
}
