/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.exceptions;

/**
 * This error is used to represent an invalid stat value.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author ~Your name~
 */
public class InvalidStatValueException extends Exception {
  public int bound;

  /**
   * Creates a new {@code InvalidStatValueException} with a {@code description} of the
   * error.
   *
   * @param description
   *     description of the invalid stat.
   */
  public InvalidStatValueException(String description, int bound) {
    super("The provided value is not a valid stat value. " + description);
    this.bound = bound;
  }
}