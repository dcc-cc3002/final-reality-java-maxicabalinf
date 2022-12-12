package cl.uchile.dcc.finalreality.controller.state;

import java.util.Objects;

/**
 * A Final Reality game ends when all characters in either the Player or Enemy team are dead.
 * This state finishes the game.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class EndOfGame extends AbstractGameState {

  /**
   * Check if the {@code game} is in {@link EndOfGame} state.
   */
  @Override
  public boolean inEndOfGame() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(EndOfGame.class, game);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final EndOfGame that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && game == that.game;
  }
}
