package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;

/**
 * Class that contains the common behavior of the {@link PlayerChoice} and {@link EnemyChoice}
 * states.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public abstract class AbstractTargetChoice extends AbstractGameState {
  protected GameCharacter target = null;

  /**
   * Change the {@code actualCharacter}'s target for the actual turn. Target selection can only be
   * done in {@link PlayerChoice} or {@link EnemyChoice} states, and makes sense only for the
   * {@code actualCharacter}'s target.
   *
   * @param target
   *     the targeted {@link GameCharacter}
   */
  @Override
  public void setTarget(GameCharacter target) {
    this.target = target;
  }

  /**
   * Tell the {@code actualCharacter} to strike its {@code target}.
   *
   * @throws InvalidStatValueException
   *     if the {@code target}'s {@code Hp} turns out to be invalid in the process
   * @throws InvalidTransitionException
   *     if a {@code target} has not been set for the actual turn
   */
  @Override
  public void strike()
      throws InvalidStatValueException, InvalidTransitionException, NullWeaponException {
    // A target must have been chosen previously.
    if (target != null) {
      // Strike the chosen target.
      game.getActualCharacter().strike(target);

      // State transition to FinishedTurn.
      changeState(new FinishedTurn());
    } else {
      // You cannot strike if there is no target.
      throw new InvalidTransitionException("Cannot strike a null target");
    }
  }
}
