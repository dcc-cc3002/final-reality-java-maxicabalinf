package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.controller.state.GameState;

/**
 * An error thrown when trying to execute a transition being in the wrong {@link GameState}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public class InvalidTransitionException extends Exception {

  /**
   * Create a new {@link InvalidTransitionException} with a {@code description} of the
   * error.
   *
   * @param description
   *     description of the error
   */
  public InvalidTransitionException(String description) {
    super(description);
  }
}
