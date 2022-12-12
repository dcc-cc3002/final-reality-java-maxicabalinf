package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.state.EndOfGame;
import cl.uchile.dcc.finalreality.controller.state.EnemyChoice;
import cl.uchile.dcc.finalreality.controller.state.FinishedTurn;
import cl.uchile.dcc.finalreality.controller.state.GameState;
import cl.uchile.dcc.finalreality.controller.state.PlayerChoice;
import cl.uchile.dcc.finalreality.controller.state.UndeterminedCharacter;
import cl.uchile.dcc.finalreality.controller.state.WaitingQueue;
import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import cl.uchile.dcc.finalreality.model.character.player.normal.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.normal.Knight;
import cl.uchile.dcc.finalreality.model.character.player.normal.Thief;
import cl.uchile.dcc.finalreality.model.items.spell.*;
import cl.uchile.dcc.finalreality.model.items.weapon.Axe;
import cl.uchile.dcc.finalreality.model.items.weapon.Bow;
import cl.uchile.dcc.finalreality.model.items.weapon.Knife;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Sword;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import cl.uchile.dcc.finalreality.view.GameView;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Set of all commands needed to play a game of Final Reality. There are restrictions related to
 * the current {@link GameState}.
 */
public class GameController {
  private final BlockingQueue<GameCharacter> turnsQueue;
  private final List<PlayerCharacter> playerCharacters;
  private final List<Enemy> enemyCharacters;
  private final GameView view;
  private GameState state = null;
  private GameCharacter actualCharacter;
  private static GameController gameInstance = null;

  public static Random rand = new Random();

  /**
   * Create a {@link GameController} instance.
   */
  private GameController(
      BlockingQueue<GameCharacter> turnsQueue,
      List<PlayerCharacter> playerCharacters,
      List<Enemy> enemyCharacters,
      GameView view) {
    this.turnsQueue = turnsQueue;
    this.playerCharacters = playerCharacters;
    this.enemyCharacters = enemyCharacters;
    this.view = view;
    this.actualCharacter = null;
  }

  /**
   * Get the only instance of the game.
   *
   * @param turnsQueue
   *     queue containing all active characters on the game
   * @param playerCharacters
   *     array of all characters used by the player
   * @param enemyCharacters
   *     array of all characters used by the CPU
   * @param view
   *     the associated {@link GameView}
   * @return
   *     the single {@link GameController} instance
   */
  public static GameController getGameController(
      BlockingQueue<GameCharacter> turnsQueue,
      List<PlayerCharacter> playerCharacters,
      List<Enemy> enemyCharacters,
      GameView view) {
    if (gameInstance == null) {
      gameInstance = new GameController(turnsQueue, playerCharacters, enemyCharacters, view);
      gameInstance.setState(new WaitingQueue());
    }
    return gameInstance;
  }

  /**
   * Update the game's actual state.
   *
   * @param state
   *     the new {@link GameState}
   */
  public void setState(GameState state) {
    this.state = state;
    state.setGame(this);
  }

  // region : STATE TRANSITIONS

  /**
   * Retrieve a {@link GameCharacter} from the {@code turnsQueue}.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   * @throws InterruptedException
   *     when the {@code turnsQueue} process is interrupted
   */
  public void pickUpCharacter() throws InvalidTransitionException, InterruptedException {
    // Characters can only be retrieved in WaitingQueue state.
    if (inWaitingQueue()) {
      actualCharacter = turnsQueue.take();
    }

    // Change state.
    state.pickUpCharacter();
  }

  /**
   * Check wether the {@code turnsQueue} is empty or not.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public boolean emptyQueue() throws InvalidTransitionException {
    return state.emptyQueue(turnsQueue);
  }

  /**
   * Trigger the player-winning case.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void allEnemiesDead() throws InvalidTransitionException {
    state.allEnemiesDead();
  }

  /**
   * Trigger the enemy-winning case.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void allAlliesDead() throws InvalidTransitionException {
    state.allAlliesDead();
  }

  /**
   * Check if the {@code actualCharacter} is wether an ally ({@link PlayerCharacter})
   * or an {@link Enemy}. Executes isAlly() or isEnemy() transition immediately when corresponds.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void checkCharacter() throws InvalidTransitionException {
    actualCharacter.checkCharacter(this);
  }

  /**
   * Change game {@code state} to {@link PlayerChoice}.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void isAlly() throws InvalidTransitionException {
    state.isAlly();
  }

  /**
   * Change game {@code state} to {@link EnemyChoice}.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void isEnemy() throws InvalidTransitionException {
    state.isEnemy();
  }

  /**
   * Begin timer for the {@code actualCharacter} to be enqueued in {@code turnsQueue}. After that,
   * returns the game to its initial state.
   *
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void beginTimer() throws InvalidTransitionException, NullWeaponException {
    state.beginTimer();
    actualCharacter = null;
  }

  /**
   * Check if all members of the {@link PlayerCharacter} or {@link Enemy} lists are dead.
   *
   * @throws InvalidTransitionException
   *     when running in a {@code state} different than {@link WaitingQueue}
   */
  boolean checkWinner() throws InvalidTransitionException {
    // Hold wether there's a winner or not.
    boolean win = true;

    // Check if all PlayerCharacters are dead.
    // Just one of them alive means the player has not lost.
    for (PlayerCharacter ally : playerCharacters) {
      if (ally.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      // The player has lost.
      allAlliesDead();
      return true;
    }

    // Same procedure for the Enemy case.
    win = true;
    for (Enemy enemy : enemyCharacters) {
      if (enemy.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      allEnemiesDead();
      return true;
    }

    // There is no winner.
    return false;
  }

  // endregion

  // region : STATE VERIFIERS

  /**
   * Check if the {@code game} is in {@link WaitingQueue} state.
   */
  public boolean inWaitingQueue() {
    return state.inWaitingQueue();
  }

  /**
   * Check if the {@code game} is in {@link UndeterminedCharacter} state.
   */
  public boolean inUndeterminedCharacter() {
    return state.inUndeterminedCharacter();
  }

  /**
   * Check if the {@code game} is in {@link PlayerChoice} state.
   */
  public boolean inPlayerChoice() {
    return state.inPlayerChoice();
  }

  /**
   * Check if the {@code game} is in {@link EnemyChoice} state.
   */
  public boolean inEnemyChoice() {
    return state.inEnemyChoice();
  }

  /**
   * Check if the {@code game} is in {@link FinishedTurn} state.
   */
  public boolean inFinishedTurn() {
    return state.inFinishedTurn();
  }

  /**
   * Check if the {@code game} is in {@link EndOfGame} state.
   */
  public boolean inEndOfGame() {
    return state.inEndOfGame();
  }
  // endregion

  // region : CHARACTER FACTORY

  /**
   * Create a new {@link Engineer} with the specified stats.
   *
   * @param name
   *     the {@link Engineer}'s name
   * @param maxHp
   *     the {@link Engineer}'s initial Hp
   * @param defense
   *     the {@link Engineer}'s defense ability
   * @return
   *     the new {@link Engineer}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public PlayerCharacter createEngineer(String name, int maxHp, int defense)
      throws InvalidStatValueException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter engineer = new Engineer(name, maxHp, defense, turnsQueue);
    try {
      engineer.equip(weapon);
    } catch (RestrictedWeaponException e) {
      // Engineers are able to equip Swords.
    }
    return engineer;
  }

  /**
   * Create a new {@link Knight} with the specified stats.
   *
   * @param name
   *     the {@link Knight}'s name
   * @param maxHp
   *     the {@link Knight}'s initial Hp
   * @param defense
   *     the {@link Knight}'s defense ability
   * @return
   *     the new {@link Knight}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public PlayerCharacter createKnight(String name, int maxHp, int defense)
      throws InvalidStatValueException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter knight = new Knight(name, maxHp, defense, turnsQueue);
    try {
      knight.equip(weapon);
    } catch (RestrictedWeaponException e) {
      // Knights are able to equip Swords.
    }
    return knight;
  }

  /**
   * Create a new {@link Thief} with the specified stats.
   *
   * @param name
   *     the {@link Thief}'s name
   * @param maxHp
   *     the {@link Thief}'s initial Hp
   * @param defense
   *     the {@link Thief}'s defense ability
   * @return
   *     the new {@link Thief}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public PlayerCharacter createThief(String name, int maxHp, int defense)
      throws InvalidStatValueException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter thief = new Thief(name, maxHp, defense, turnsQueue);
    try {
      thief.equip(weapon);
    } catch (RestrictedWeaponException e) {
      // Thiefs are able to equip Swords.
    }
    return thief;
  }

  /**
   * Create a new {@link BlackMage} with the specified stats.
   *
   * @param name
   *     the {@link BlackMage}'s name
   * @param maxHp
   *     the {@link BlackMage}'s initial Hp
   * @param maxMp
   *     the {@link BlackMage}'s initial Mp
   * @param defense
   *     the {@link BlackMage}'s defense ability
   * @return
   *     the new {@link BlackMage}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public Mage createBlackMage(String name, int maxHp, int maxMp, int defense)
      throws InvalidStatValueException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage blackMage = new BlackMage(name, maxHp, defense, maxMp, turnsQueue);
    try {
      blackMage.equip(weapon);
    } catch (RestrictedWeaponException e) {
      // BlackMages are able to equip Staves.
    }
    return blackMage;
  }

  /**
   * Create a new {@link WhiteMage} with the specified stats.
   *
   * @param name
   *     the {@link WhiteMage}'s name
   * @param maxHp
   *     the {@link WhiteMage}'s initial Hp
   * @param maxMp
   *     the {@link WhiteMage}'s initial Mp
   * @param defense
   *     the {@link WhiteMage}'s defense ability
   * @return
   *     the new {@link WhiteMage}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public Mage createWhiteMage(String name, int maxHp, int maxMp, int defense)
      throws InvalidStatValueException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage whiteMage = new WhiteMage(name, maxHp, defense, maxMp, turnsQueue);
    try {
      whiteMage.equip(weapon);
    } catch (RestrictedWeaponException e) {
      // WhiteMages are able to equip Staves.
    }
    return whiteMage;
  }

  /**
   * Create a new {@link Enemy} with the specified stats.
   *
   * @param name
   *     the {@link Enemy}'s name
   * @param weight
   *     the {@link Enemy}'s weight
   * @param maxHp
   *     the {@link Enemy}'s initial Hp
   * @param defense
   *     the {@link Enemy}'s defense ability
   * @return
   *     the new {@link Enemy}
   * @throws InvalidStatValueException
   *     if any of the given stats is out of bounds
   */
  public Enemy createEnemy(String name, int weight, int maxHp, int defense)
      throws InvalidStatValueException {
    return new Enemy(name, weight, maxHp, defense, turnsQueue);
  }
  // endregion

  // region : WEAPON FACTORY
  /**
   * Create a {@link Axe} with the given stats.
   *
   * @param name
   *     the {@link Axe}'s name
   * @param damage
   *     the {@link Axe}'s damage
   * @param weight
   *     the {@link Axe}'s weight
   * @return
   *     the new {@link Axe}
   */
  public Weapon createAxe(String name, int damage, int weight) {
    return new Axe(name, damage, weight);
  }

  /**
   * Create a {@link Bow} with the given stats.
   *
   * @param name
   *     the {@link Bow}'s name
   * @param damage
   *     the {@link Bow}'s damage
   * @param weight
   *     the {@link Bow}'s weight
   * @return
   *     the new {@link Bow}
   */
  public Weapon createBow(String name, int damage, int weight) {
    return new Bow(name, damage, weight);
  }

  /**
   * Create a {@link Knife} with the given stats.
   *
   * @param name
   *     the {@link Knife}'s name
   * @param damage
   *     the {@link Knife}'s damage
   * @param weight
   *     the {@link Knife}'s weight
   * @return
   *     the new {@link Knife}
   */
  public Weapon createKnife(String name, int damage, int weight) {
    return new Knife(name, damage, weight);
  }

  /**
   * Create a {@link Staff} with the given stats.
   *
   * @param name
   *     the {@link Staff}'s name
   * @param damage
   *     the {@link Staff}'s damage
   * @param magicDamage
   *     the {@link Staff}'s magicDamage
   * @param weight
   *     the {@link Staff}'s weight
   * @return
   *     the new {@link Staff}
   */
  public Weapon createStaff(String name, int damage, int magicDamage, int weight) {
    return new Staff(name, damage, magicDamage, weight);
  }

  /**
   * Create a {@link Sword} with the given stats.
   *
   * @param name
   *     the {@link Sword}'s name
   * @param damage
   *     the {@link Sword}'s damage
   * @param weight
   *     the {@link Sword}'s weight
   * @return
   *     the new {@link Sword}
   */
  public Weapon createSword(String name, int damage, int weight) {
    return new Sword(name, damage, weight);
  }

  // endregion
  /**
   * Return the {@code actualCharacter} who's playing their turn.
   */
  public GameCharacter getActualCharacter() {
    return actualCharacter;
  }

  /**
   * Return the actual Turns Queue in the game.
   */
  public BlockingQueue<GameCharacter> getTurnsQueue() {
    return turnsQueue;
  }

  // region : SPELL FACTORY
  /**
   * Create a new {@link Cure} {@link Spell}.
   *
   * @return
   *     the new spell
   */
  public Spell createCure() {
    return new Cure();
  }

  /**
   * Create a new {@link Fire} {@link Spell}.
   *
   * @return
   *     the new spell
   */
  public Spell createFire() {
    return new Fire();
  }

  /**
   * Create a new {@link Paralyze} {@link Spell}.
   *
   * @return
   *     the new spell
   */
  public Spell createParalyze() {
    return new Paralyze();
  }

  /**
   * Create a new {@link Thunder} {@link Spell}.
   *
   * @return
   *     the new spell
   */
  public Spell createThunder() {
    return new Thunder();
  }

  /**
   * Create a new {@link Venom} {@link Spell}.
   *
   * @return
   *     the new spell
   */
  public Spell createVenom() {
    return new Venom();
  }
  // endregion

  // region : USER ACTIONS
  /**
   * Change a {@link PlayerCharacter}'s equipped {@link Weapon}.
   *
   * @param character
   *     the {@link PlayerCharacter} to have it's {@link Weapon} changed
   * @param weapon
   *     the new {@link Weapon}
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   * @throws RestrictedWeaponException
   *     if the given {@link PlayerCharacter} is unable to equip such {@link Weapon}
   */
  public void changeWeapon(PlayerCharacter character, Weapon weapon)
      throws InvalidTransitionException, RestrictedWeaponException {
    state.changeWeapon(character, weapon);
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
  public void changeSpell(Mage mage, Spell spell)
      throws InvalidTransitionException, RestrictedSpellException {
    state.changeSpell(mage, spell);
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
  public void setTarget(GameCharacter target)
      throws InvalidTransitionException {
    state.setTarget(target);
  }

  /**
   * Tell the {@code actualCharacter} to strike its {@code target}.
   *
   * @throws InvalidStatValueException
   *     if the {@code target}'s {@code Hp} turns out to be invalid in the process
   * @throws InvalidTransitionException
   *     if the game {@code state} does not allow this action
   */
  public void strike() throws InvalidStatValueException, InvalidTransitionException, NullWeaponException {
    state.strike();
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
  public void cast(Mage mage) throws RestrictedSpellException, InvalidStatValueException,
    MissingStatException, InvalidTransitionException, NullWeaponException {
    state.cast(mage);
  }
  // endregion

  /**
   * Enqueue a {@link GameCharacter} to {@code turnsQueue}.
   *
   * @param character
   *     the {@link GameCharacter} to be enqueued
   */
  public void waitTurn(GameCharacter character) throws NullWeaponException {
    character.waitTurn();
  }


  /**
   * Run the game dynamic.
   *
   * @throws InvalidTransitionException
   *     when trying to execute a function being in the wrong {@code state}
   * @throws InterruptedException
   *     if a process related to {@code turnsQueue} is interrupted
   */
  public void play() throws InvalidTransitionException, InterruptedException, NullWeaponException {
    // The game ends when it is in End of Game state.
    while (!inEndOfGame()) {
      // If there's a winner, the game should end.
      if (checkWinner()) {
        break;
      }

      // The game can only continue with some character waiting for its turn.
      while (inWaitingQueue()) {
        // Retrieve a character if available
        if (!emptyQueue()) {
          pickUpCharacter();
        }
      }

      // Check wether the character is PlayerCharacter or Enemy. Change state to
      // PlayerChoice or EnemyChoice when corresponding.
      checkCharacter();

      // Wait for player's action to continue. Enemy attacks are automatic.
      while (true) {
        if (!inPlayerChoice()) {
          break;
        }
      }

      // A character action was executed, so its turn ends.
      if (inFinishedTurn()) {
        beginTimer();
      }
    }
    System.out.println("GAME OVER");
  }
}
