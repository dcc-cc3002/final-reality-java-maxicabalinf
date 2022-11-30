package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;

/**
 * An error to indicate the inability of a {@link PlayerCharacter} to equip a {@link Weapon}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class RestrictedWeaponException extends Exception {

  /**
   * Creates a new {@code RestrictedWeaponException} with a {@code description} of the
   * error.
   *
   * @param description
   *     description of the error
   */
  public RestrictedWeaponException(String description) {
    super("The provided weapon is not available for this character. " + description);
  }
}
