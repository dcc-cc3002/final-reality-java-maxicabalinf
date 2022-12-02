package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;

public interface GameState {

  void setGame(GameController game);
  void changeState(GameState state);
  void error() throws InvalidTransitionException;

  // region : STATES
  boolean inWaitingQueue();
  boolean inUndeterminedCharacter();
  boolean inPlayerChoice();
  boolean inEnemyChoice();
  boolean inFinishedTurn();
  boolean inEndOfGame();
  // endregion

  // region : TRANSITIONS
  void pickUpCharacter() throws InvalidTransitionException;
  void isAlly() throws InvalidTransitionException;
  void isEnemy() throws InvalidTransitionException;
  void changeWeapon() throws InvalidTransitionException;
  void changeSpell() throws InvalidTransitionException;
  void changeTarget() throws InvalidTransitionException;
  void execute() throws InvalidTransitionException;
  void beginTimer() throws InvalidTransitionException;
  void emptyQueue() throws InvalidTransitionException;
  void allEnemiesDead() throws InvalidTransitionException;
  void allAlliesDead() throws InvalidTransitionException;
  // endregion
}
