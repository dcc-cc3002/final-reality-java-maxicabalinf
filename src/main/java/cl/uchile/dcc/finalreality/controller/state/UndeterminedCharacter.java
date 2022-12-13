package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.Objects;

/**
 * A Final Reality game is in {@link UndeterminedCharacter} state right after picking up
 * a {@link GameCharacter} from its {@code turnsQueue}. Now the character type must be determined
 * to continue the game.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class UndeterminedCharacter extends AbstractGameState {

  /**
   * Change game {@code state} to {@link PlayerChoice}.
   */
  @Override
  public void isAlly() {
    this.changeState(new PlayerChoice());
  }

  /**
   * Change game {@code state} to {@link EnemyChoice}.
   */
  @Override
  public void isEnemy() {
    this.changeState(new EnemyChoice());
  }

  /**
   * Check if the {@code game} is in {@link UndeterminedCharacter} state.
   */
  @Override
  public boolean inUndeterminedCharacter() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(UndeterminedCharacter.class, game);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final UndeterminedCharacter that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && game == that.game;
  }
}
