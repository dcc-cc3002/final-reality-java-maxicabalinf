package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import java.util.concurrent.BlockingQueue;


/**
 * Abstract Class that holds the general behavior of a {@link GameState} object.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */
public abstract class AbstractGameState implements GameState {

  protected GameController game;

  /**
   * Associate a {@link GameController} to this {@link GameState}.
   *
   * @param game
   *     the {@link GameController} to be associated
   */
  @Override
  public void setGame(GameController game) {
    this.game = game;
  }

  /**
   * Change the state of the associated {@link GameController}.
   *
   * @param state
   *     the {@link GameController} new state
   */
  @Override
  public void changeState(GameState state) {
    game.setState(state);
  }

  /**
   * Indicate an invalid state transition.
   */
  @Override
  public void error() throws InvalidTransitionException {
    throw new InvalidTransitionException("Cannot execute transition being in the actual GameState");
  }

  // region : STATE TRANSITIONS
  /**
   * Retrieve a {@link GameCharacter} from the {@code turnsQueue}.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void pickUpCharacter() throws InvalidTransitionException {
    error();
  }

  /**
   * Change game {@code state} to {@link PlayerChoice}.
   *
   * @throws InvalidTransitionException
   *     if the this {@code state} does not allow this action
   */
  @Override
  public void isAlly() throws InvalidTransitionException {
    error();
  }

  /**
   * Change game {@code state} to {@link EnemyChoice}.
   *
   * @throws InvalidTransitionException
   *     if the this {@code state} does not allow this action
   */
  @Override
  public void isEnemy() throws InvalidTransitionException {
    error();
  }

  /**
   * Begin timer to enqueue the {@code game}'s {@code actualCharacter} in {@code turnsQueue}.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void beginTimer() throws InvalidTransitionException {
    error();
  }

  /**
   * Check, if the state allows, wether a given {@code queue} is empty or not.
   *
   * @param queue
   *     the {@code queue} to be checked
   * @return
   *     {@code true} is the given {@code queue} is empty
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public boolean emptyQueue(BlockingQueue<GameCharacter> queue) throws InvalidTransitionException {
    error();
    return false;
  }

  /**
   * Change state to {@link EndOfGame} in the player-winning case.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void allEnemiesDead() throws InvalidTransitionException {
    error();
  }

  /**
   * Change state to {@link EndOfGame} in the enemy-winning case.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void allAlliesDead() throws InvalidTransitionException {
    error();
  }
  // endregion

  // region : USER ACTIONS
  /**
   * Change the character {@link Weapon}.
   *
   * @param character
   *     the {@link PlayerCharacter} whose {@link Weapon} will change
   * @param weapon
   *     the new {@link Weapon}
   */
  @Override
  public void changeWeapon(PlayerCharacter character, Weapon weapon)
      throws InvalidTransitionException, RestrictedWeaponException {
    error();
  }

  /**
   * Change a {@link Mage}'s equipped {@link Spell}.
   *
   * @param mage
   *     the {@link Mage} to have it's {@link Spell} changed
   * @param spell
   *     the new {@link Spell}
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   * @throws RestrictedSpellException
   *     if the given {@link Mage} is unable to cast such {@link Spell}
   */
  @Override
  public void changeSpell(Mage mage, Spell spell)
      throws InvalidTransitionException, RestrictedSpellException {
    error();
  }

  /**
   * Change the {@code actualCharacter}'s target for the actual turn. Target selection can only be
   * done in {@link PlayerChoice} or {@link EnemyChoice} states, and makes sense only for the
   * {@code actualCharacter}'s target.
   *
   * @param target
   *     the targeted {@link GameCharacter}
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void setTarget(GameCharacter target) throws InvalidTransitionException {
    error();
  }

  /**
   * Tell the {@code actualCharacter} to strike its {@code target}.
   *
   * @throws InvalidStatValueException
   *     if the {@code target}'s {@code Hp} turns out to be invalid in the process
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void strike() throws InvalidStatValueException, InvalidTransitionException {
    error();
  }

  /**
   * Tell a {@link Mage} to cast its {@code equippedSpell} towards the chosen {@code target}.
   *
   * @param mage
   *     the {@link Mage} who will cast the {@link Spell}
   * @throws RestrictedSpellException
   *     if the given {@link Mage} is unable to cast such {@link Spell}
   * @throws InvalidStatValueException
   *     if the {@code target}'s {@code Hp} turns out to be invalid in the process
   * @throws MissingStatException
   *     if the given {@link Mage} is not carrying a {@link Staff}
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  @Override
  public void cast(Mage mage) throws RestrictedSpellException, InvalidStatValueException,
      MissingStatException, InvalidTransitionException {
    error();
  }
  // endregion

  // region : STATE VERIFIERS
  /**
   * Check if the {@code game} is in {@link WaitingQueue} state.
   */
  @Override
  public boolean inWaitingQueue() {
    return false;
  }

  /**
   * Check if the {@code game} is in {@link UndeterminedCharacter} state.
   */
  @Override
  public boolean inUndeterminedCharacter() {
    return false;
  }

  /**
   * Check if the {@code game} is in {@link PlayerChoice} state.
   */
  @Override
  public boolean inPlayerChoice() {
    return false;
  }

  /**
   * Check if the {@code game} is in {@link EnemyChoice} state.
   */
  @Override
  public boolean inEnemyChoice() {
    return false;
  }

  /**
   * Check if the {@code game} is in {@link FinishedTurn} state.
   */
  @Override
  public boolean inFinishedTurn() {
    return false;
  }

  /**
   * Check if the {@code game} is in {@link EndOfGame} state.
   */
  @Override
  public boolean inEndOfGame() {
    return false;
  }
  // endregion
}
