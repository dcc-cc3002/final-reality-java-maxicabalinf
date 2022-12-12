package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A Final Reality game is in {@link WaitingQueue} state at the beggining of every turn.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public class WaitingQueue extends AbstractGameState {

  /**
   * Change {@code state} to {@link UndeterminedCharacter}.
   */
  @Override
  public void pickUpCharacter() {
    this.changeState(new UndeterminedCharacter());
  }

  /**
   * Check wether a given {@code queue} is empty or not.
   *
   * @param queue
   *     the {@code queue} to be checked
   * @return
   *     {@code true} if the given {@code queue} is empty
   */
  @Override
  public boolean emptyQueue(BlockingQueue<GameCharacter> queue) {
    return queue.isEmpty();
  }

  /**
   * Trigger the player-winning case.
   */
  @Override
  public void allEnemiesDead() {
    this.changeState(new EndOfGame());
  }

  /**
   * Trigger the enemy-winning case.
   */
  @Override
  public void allAlliesDead() {
    this.changeState(new EndOfGame());
  }

  /**
   * Check if the {@code game} is in {@link WaitingQueue} state.
   */
  @Override
  public boolean inWaitingQueue() {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(WaitingQueue.class, game);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final WaitingQueue that)) {
      return false;
    }
    return hashCode() == that.hashCode()
    && game == that.game;
  }
}
