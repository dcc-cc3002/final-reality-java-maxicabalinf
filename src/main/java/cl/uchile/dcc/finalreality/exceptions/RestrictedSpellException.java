package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;

/**
 * An error to indicate the inability of a {@link Mage} to cast a {@link Spell}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class RestrictedSpellException extends Exception {

  /**
   * Creates a new {@code RestrictedSpellException} with a {@code description} of the
   * error.
   *
   * @param description
   *     description of the error
   */
  public RestrictedSpellException(String description) {
    super("This mage cannot cast the given spell. " + description);
  }
}
