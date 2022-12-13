package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;

/**
 * An error to indicate that it is impossible to retireve a null {@link Weapon}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class NullWeaponException extends Exception {

  /**
   * Create a new {@link NullWeaponException}.
   */
  public NullWeaponException() {
    super();
  }
}
