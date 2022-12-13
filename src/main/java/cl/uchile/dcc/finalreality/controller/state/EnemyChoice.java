package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.model.character.Enemy;
import java.util.Objects;

/**
 * A Final Reality game is in {@code EnemyChoice} state if the {@code actualCharacter} playing
 * its turn is an {@link Enemy}.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class EnemyChoice extends AbstractTargetChoice {

  /**
   * Check if the {@code game} is in {@link EnemyChoice} state.
   */
  @Override
  public boolean inEnemyChoice() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(EnemyChoice.class, game, target);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final EnemyChoice that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && game == that.game
      && target == that.target;
  }
}
