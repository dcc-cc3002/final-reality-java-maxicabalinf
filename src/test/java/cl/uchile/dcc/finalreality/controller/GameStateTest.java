package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.controller.state.*;
import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import cl.uchile.dcc.finalreality.view.GameView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameController.getGameController;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameStateTest {

  public BlockingQueue<GameCharacter> turnsQueue;
  public List<PlayerCharacter> playerCharacters;
  public List<Enemy> enemyCharacters;
  public GameView view;
  public  GameController controller;
  public GameState endOfGame;
  public GameState enemyChoice;
  public GameState finishedTurn;
  public GameState playerChoice;
  public GameState undeterminedCharacter;
  public GameState waitingQueue;

  @BeforeEach
  void setUp() {
    turnsQueue = new LinkedBlockingQueue<>();
    playerCharacters = new ArrayList<>();
    enemyCharacters = new ArrayList<>();
    view = new GameView();
    controller = getGameController(
      turnsQueue, playerCharacters, enemyCharacters, view
    );
    endOfGame = new EndOfGame();
    enemyChoice = new EnemyChoice();
    finishedTurn = new FinishedTurn();
    playerChoice = new PlayerChoice();
    undeterminedCharacter = new UndeterminedCharacter();
    waitingQueue = new WaitingQueue();
  }

  @AfterEach
  void reset() {
    controller.resetGame();
  }

  @Test
  void setGameAndGetGame() {
    endOfGame.setGame(controller);
    enemyChoice.setGame(controller);
    finishedTurn.setGame(controller);
    playerChoice.setGame(controller);
    undeterminedCharacter.setGame(controller);
    waitingQueue.setGame(controller);

    // Set games should equal the given one.
    assertEquals(controller, endOfGame.getGame());
    assertEquals(controller, enemyChoice.getGame());
    assertEquals(controller, finishedTurn.getGame());
    assertEquals(controller, playerChoice.getGame());
    assertEquals(controller, undeterminedCharacter.getGame());
    assertEquals(controller, waitingQueue.getGame());
  }

  @Test
  void changeState() {
    // Initial state is WaitingQueue.
    assertTrue(controller.inWaitingQueue());

    // Associate to another state. This state should be able to change the game state from now.
    endOfGame = new EndOfGame();
    enemyChoice = new EnemyChoice();

    endOfGame.setGame(controller);
    endOfGame.changeState(enemyChoice);

    // Change to any state should be effective.
    assertTrue(controller.inEnemyChoice());
  }

  @Test
  void error() {
    // Any state should throw this exeption
    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.error());
    assertThrows(InvalidTransitionException.class,
      () -> enemyChoice.error());
    assertThrows(InvalidTransitionException.class,
      () -> finishedTurn.error());
    assertThrows(InvalidTransitionException.class,
      () -> playerChoice.error());
    assertThrows(InvalidTransitionException.class,
      () -> undeterminedCharacter.error());
    assertThrows(InvalidTransitionException.class,
      () -> waitingQueue.error());
  }

  @Test
  void pickUpCharacter() {
    // All states but WaitingQueue should throw an exception for this transition.
    assertThrows(InvalidTransitionException.class, () -> playerChoice.pickUpCharacter());
    controller.setState(waitingQueue);
    assertDoesNotThrow(() -> waitingQueue.pickUpCharacter());
  }

  @Test
  void isAlly() {
    // All states but UndeterminedCharacter should throw an exception for this transition.
    assertThrows(InvalidTransitionException.class, () -> endOfGame.isAlly());
    controller.setState(undeterminedCharacter);
    assertDoesNotThrow(() -> undeterminedCharacter.isAlly());
  }

  @Test
  void isEnemy() {
    // All states but UndeterminedCharacter should throw an exception for this transition.
    assertThrows(InvalidTransitionException.class,
      () -> finishedTurn.isEnemy());
    controller.setState(undeterminedCharacter);
    assertDoesNotThrow(() -> undeterminedCharacter.isEnemy());
  }

  @Test
  void beginTimer() throws InvalidStatValueException, NullWeaponException,
    InvalidTransitionException, InterruptedException, RestrictedWeaponException {
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    playerCharacters.add(bMage);
    controller.waitTurn(bMage);
    controller.pickUpCharacter();

    // This should change the state from FinishedTurn to WaitingQueue. No other
    // state than FinishedTurn can execute this transition.
    controller.setState(playerChoice);
    assertThrows(InvalidTransitionException.class,
      () -> playerChoice.beginTimer());

    // There is no character enqueued yet.
    assertFalse(controller.getTurnsQueue().contains(bMage));
    controller.setState(finishedTurn);
    assertDoesNotThrow(() -> finishedTurn.beginTimer());
    Thread.sleep(6000);

    // beginTimer() enqueues the actual character. Note that the controller method sets
    // actualCharacter to null, but not the state method.
    assertTrue(controller.getTurnsQueue().contains(bMage));
    assertTrue(controller.inWaitingQueue());
  }

  @Test
  void emptyQueue() throws InvalidStatValueException, InvalidTransitionException,
    NullWeaponException, InterruptedException, RestrictedWeaponException {
    // All states but WaitingQueue should throw an exception for this transition.
    assertThrows(InvalidTransitionException.class,
      () -> finishedTurn.emptyQueue(controller.getTurnsQueue())
      );

    // turnsQueue is still empty and WaitingQueue can check it.
    controller.setState(waitingQueue);
    assertTrue(waitingQueue.emptyQueue(controller.getTurnsQueue()));

    // Enqueue.
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    playerCharacters.add(bMage);
    controller.waitTurn(bMage);
    Thread.sleep(6000);

    // turnsQueue is not empty now.
    assertFalse(waitingQueue.emptyQueue(controller.getTurnsQueue()));

  }

  @Test
  void allEnemiesDead() throws InvalidTransitionException {
    // Only WaitingQueue can execute this transition.
    assertThrows(InvalidTransitionException.class,
      () -> undeterminedCharacter.allEnemiesDead());

    controller.setState(waitingQueue);
    waitingQueue.allEnemiesDead();
    assertTrue(controller.inEndOfGame());
  }

  @Test
  void allAlliesDead() throws InvalidTransitionException {
    // Only WaitingQueue can execute this transition.
    assertThrows(InvalidTransitionException.class,
      () -> undeterminedCharacter.allAlliesDead());

    controller.setState(waitingQueue);
    waitingQueue.allAlliesDead();
    assertTrue(controller.inEndOfGame());
  }

  @Test
  void changeWeapon() throws InvalidStatValueException, RestrictedWeaponException {
    // Game dynamic implementation in GameControllerTest.

    // Only PlayerChoice or EnemyChoice can execute this transition.
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 40, 40, 20);
    controller.setState(endOfGame);
    Weapon knife = controller.createKnife("bow", 23, 14);

    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.changeWeapon(bMage, knife));
  }

  @Test
  void changeSpell() throws InvalidTransitionException,
    RestrictedSpellException, InvalidStatValueException, RestrictedWeaponException {
    // Game dynamic implementation in GameControllerTest.

    // Only PlayerChoice or EnemyChoice can execute this transition.
    Mage bMage = controller.createBlackMage("bMage", 40, 40, 20);
    controller.setState(endOfGame);
    Spell thunder = controller.createThunder();

    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.changeSpell(bMage, thunder));
  }

  @Test
  void setTarget() throws InvalidStatValueException, RestrictedWeaponException {
    // Game dynamic implementation in GameControllerTest.

    // Only PlayerChoice or EnemyChoice can execute this transition.
    Mage bMage = controller.createBlackMage("bMage", 40, 40, 20);
    controller.setState(endOfGame);
    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.setTarget(bMage));
  }

  @Test
  void strike() throws InvalidStatValueException, RestrictedWeaponException {
    // Game dynamic implementation in GameControllerTest.

    // Only PlayerChoice or EnemyChoice can execute this transition.
    controller.setState(endOfGame);
    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.strike());
  }

  @Test
  void cast() throws InvalidStatValueException, RestrictedWeaponException {
    // Game dynamic implementation in GameControllerTest.

    // Only PlayerChoice or EnemyChoice can execute this transition.
    Mage bMage = controller.createBlackMage("bMage", 40, 40, 20);
    controller.setState(endOfGame);
    assertThrows(InvalidTransitionException.class,
      () -> endOfGame.cast(bMage));
  }

  @Test
  void inWaitingQueue() {
    // Only the state with the same name as the method return true.
    assertFalse(undeterminedCharacter.inWaitingQueue());
  }

  @Test
  void inUndeterminedCharacter() {
    // Only the state with the same name as the method return true.
    assertFalse(waitingQueue.inUndeterminedCharacter());
  }

  @Test
  void inPlayerChoice() {
    // Only the state with the same name as the method return true.
    assertFalse(undeterminedCharacter.inPlayerChoice());
  }

  @Test
  void inEnemyChoice() {
    // Only the state with the same name as the method return true.
    assertFalse(undeterminedCharacter.inEnemyChoice());
  }

  @Test
  void inFinishedTurn() {
    // Only the state with the same name as the method return true.
    assertFalse(undeterminedCharacter.inFinishedTurn());
  }

  @Test
  void inEndOfGame() {
    // Only the state with the same name as the method return true.
    assertFalse(undeterminedCharacter.inEndOfGame());
  }

  @Test
  void equalsWorking() {
    assertEquals(new EndOfGame(), endOfGame);
    assertEquals(new EnemyChoice(), enemyChoice);
    assertEquals(new FinishedTurn(), finishedTurn);
    assertEquals(new PlayerChoice(), playerChoice);
    assertEquals(new UndeterminedCharacter(), undeterminedCharacter);
    assertEquals(new WaitingQueue(), waitingQueue);

    // Equals by same reference ("==").
    GameState endOfGame2 = endOfGame;
    GameState enemyChoice2 = enemyChoice;
    GameState finishedTurn2 = finishedTurn;
    GameState playerChoice2 = playerChoice;
    GameState undeterminedCharacter2 = undeterminedCharacter;
    GameState waitingQueue2 = waitingQueue;

    assertEquals(endOfGame2, endOfGame);
    assertEquals(enemyChoice2, enemyChoice);
    assertEquals(finishedTurn2, finishedTurn);
    assertEquals(playerChoice2, playerChoice);
    assertEquals(undeterminedCharacter2, undeterminedCharacter);
    assertEquals(waitingQueue2, waitingQueue);

    // Different class object must not be equal.
    assertNotEquals(waitingQueue, endOfGame);
    assertNotEquals(endOfGame, enemyChoice);
    assertNotEquals(enemyChoice, finishedTurn);
    assertNotEquals(finishedTurn, playerChoice);
    assertNotEquals(playerChoice, undeterminedCharacter);
    assertNotEquals(undeterminedCharacter, waitingQueue);
  }

  @Test
  void hashCodeEquals() {
    GameState endOfGame2 = new EndOfGame();
    GameState enemyChoice2 = new EnemyChoice();
    GameState finishedTurn2 = new FinishedTurn();
    GameState playerChoice2 = new PlayerChoice();
    GameState undeterminedCharacter2 = new UndeterminedCharacter();
    GameState waitingQueue2 = new WaitingQueue();

    endOfGame.setGame(controller); endOfGame2.setGame(controller);
    enemyChoice.setGame(controller); enemyChoice2.setGame(controller);
    finishedTurn.setGame(controller); finishedTurn2.setGame(controller);
    playerChoice.setGame(controller); playerChoice2.setGame(controller);
    undeterminedCharacter.setGame(controller); undeterminedCharacter2.setGame(controller);
    waitingQueue.setGame(controller); waitingQueue2.setGame(controller);

    assertEquals(endOfGame.hashCode(),
      endOfGame2.hashCode());
    assertEquals(enemyChoice.hashCode(),
      enemyChoice2.hashCode());
    assertEquals(finishedTurn.hashCode(),
      finishedTurn2.hashCode());
    assertEquals(playerChoice.hashCode(),
      playerChoice2.hashCode());
    assertEquals(undeterminedCharacter.hashCode(),
      undeterminedCharacter2.hashCode());
    assertEquals(waitingQueue.hashCode(),
      waitingQueue2.hashCode());
  }
}