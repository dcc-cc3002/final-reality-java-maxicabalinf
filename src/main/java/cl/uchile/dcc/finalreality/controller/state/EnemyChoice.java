package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.model.character.Enemy;

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
}
