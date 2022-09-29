package cl.uchile.dcc.finalreality.exceptions;

/**
 * This error is used to represent an invalid stat value.
 *
 * @author <a href="https://github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class GreaterStatValueException extends InvalidStatValueException {

  /**
   * Creates a new {@code GreaterStatValueException} with a {@code description} of the
   * error.
   *
   * @param description
   *     the excepction description
   */
  public GreaterStatValueException(String description) {
    super(description);
  }
}
