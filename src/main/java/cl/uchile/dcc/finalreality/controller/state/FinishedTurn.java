package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import java.util.Objects;

/**
 * A {@link GameState} that comes right after a {@code strike} or a {@link Spell} {@code cast}
 * is executed.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class FinishedTurn extends AbstractGameState {

  /**
   * Begin timer for the {@code actualCharacter} to be enqueued in {@code turnsQueue}.
   */
  @Override
  public void beginTimer() throws NullWeaponException {
    game.waitTurn(game.getActualCharacter());
    this.changeState(new WaitingQueue());
  }

  /**
   * Check if the {@code game} is in {@link FinishedTurn} state.
   */
  @Override
  public boolean inFinishedTurn() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(FinishedTurn.class, game);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final FinishedTurn that)) {
      return false;
    }
    return hashCode() == that.hashCode()
      && game == that.game;
  }
}
