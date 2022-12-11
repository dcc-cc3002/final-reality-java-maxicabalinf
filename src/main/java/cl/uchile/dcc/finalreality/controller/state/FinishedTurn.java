package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.model.items.spell.Spell;

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
  public void beginTimer() {
    game.getActualCharacter().waitTurn();
    this.changeState(new WaitingQueue());
  }

  /**
   * Check if the {@code game} is in {@link FinishedTurn} state.
   */
  @Override
  public boolean inFinishedTurn() {
    return true;
  }
}
