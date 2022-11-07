package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.model.weapon.Weapon;

/**
 * An error thrown when a {@link Weapon} misses a stat field.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class MissingStatException extends Exception {

  public MissingStatException(String description) {
    super(description);
  }
}
