package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;

public abstract class AbstractGameState implements GameState {

  private GameController game;

  /**
   * Associates a {@link GameController} to this {@link GameState}.
   *
   * @param game
   *     the {@link GameController} to be associated
   */
  public void setGame(GameController game) {
    this.game = game;
  }

  /**
   * Changes the state of the associated {@link GameController}.
   *
   * @param state
   *     the {@link GameController} new state
   */
  public void changeState(GameState state) {
    game.setState(state);
  }

  /**
   * Indicate an invalid state transition.
   */
  public void error() throws InvalidTransitionException {
    throw new InvalidTransitionException();
    // TODO implement InvalidTransitionExeption
  }

  // region : STATES
  public boolean inWaitingQueue() {
    return false;
  }

  public boolean inUndeterminedCharacter() {
    return false;
  }

  public boolean inPlayerChoice() {
    return false;
  }

  public boolean inEnemyChoice() {
    return false;
  }

  public boolean inFinishedTurn() {
    return false;
  }

  public boolean inEndOfGame() {
    return false;
  }

  // endregion

  // region : TRANSITIONS

  public void pickUpCharacter() throws InvalidTransitionException {
    error();
  }
  public void isAlly() throws InvalidTransitionException {
    error();
  }
  public void isEnemy() throws InvalidTransitionException {
    error();
  }

  /**
   * Change the character {@link Weapon}.
   *
   * @param character
   *     the {@link PlayerCharacter} whose {@link Weapon} will change
   * @param weapon
   *     the new {@link Weapon}
   */
  public void changeWeapon() throws InvalidTransitionException {
    //game.changeWeapon(character, weapon);
    error();
  }

  public void changeSpell() throws InvalidTransitionException {
    error();
  }

  public void changeTarget() throws InvalidTransitionException {
    error();
  }

  public void execute() throws InvalidTransitionException {
    error();
  }

  public void beginTimer() throws InvalidTransitionException {
    error();
  }

  public void emptyQueue() throws InvalidTransitionException {
    error();
  }

  public void allEnemiesDead() throws InvalidTransitionException {
    error();
  }

  public void allAlliesDead() throws InvalidTransitionException {
    error();
  }


  // endregion
}
