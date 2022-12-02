package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.state.GameState;
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
import cl.uchile.dcc.finalreality.model.items.spell.Cure;
import cl.uchile.dcc.finalreality.model.items.spell.Fire;
import cl.uchile.dcc.finalreality.model.items.spell.Paralyze;
import cl.uchile.dcc.finalreality.model.items.spell.Thunder;
import cl.uchile.dcc.finalreality.model.items.spell.Venom;
import cl.uchile.dcc.finalreality.model.items.weapon.Axe;
import cl.uchile.dcc.finalreality.model.items.weapon.Bow;
import cl.uchile.dcc.finalreality.model.items.weapon.Knife;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Sword;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import cl.uchile.dcc.finalreality.view.GameView;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class GameController {
  private final BlockingQueue<GameCharacter> turnsQueue;
  private final List<PlayerCharacter> playerCharacters;
  private final List<Enemy> enemyCharacters;
  private final GameView view;
  private GameState state;
  private GameCharacter actualCharacter;

  public GameController(
      BlockingQueue<GameCharacter> turnsQueue,
      List<PlayerCharacter> playerCharacters,
      List<Enemy> enemyCharacters,
      GameView view) {
    this.turnsQueue = turnsQueue;
    this.playerCharacters = playerCharacters;
    this.enemyCharacters = enemyCharacters;
    this.view = view;
  }

  public void setState(GameState state) {
    this.state = state;
    state.setGame(this);
  }

  // region : STATE TRANSITIONS
  public void pickUpCharacter() throws InvalidTransitionException, InterruptedException {
    actualCharacter = turnsQueue.take();
    state.pickUpCharacter();
  }

  public void checkCharacter() throws InvalidTransitionException {
    actualCharacter.checkCharacter(this);
  }
  public void isAlly() throws InvalidTransitionException {
    state.isAlly();
  }

  public void isEnemy() throws InvalidTransitionException {
    state.isEnemy();
  }

  public void changeWeapon() throws InvalidTransitionException {
    state.changeWeapon();
  }

  public void changeSpell() throws InvalidTransitionException {
    state.changeSpell();
  }

  public void changeTarget() throws InvalidTransitionException {
    state.changeTarget();
  }

  public void execute() throws InvalidTransitionException {
    state.execute();
  }

  public void beginTimer() throws InvalidTransitionException {
    state.beginTimer();
  }

  public void emptyQueue() throws InvalidTransitionException {
    state.emptyQueue();
  }

  public void allEnemiesDead() throws InvalidTransitionException {
    state.allEnemiesDead();
  }

  public void allAlliesDead() throws InvalidTransitionException {
    state.allAlliesDead();
  }
  // endregion

  // region : STATE VERIFIERS
  public boolean inWaitingQueue() {
    return state.inWaitingQueue();
  }

  public boolean inUndeterminedCharacter() {
    return state.inUndeterminedCharacter();
  }

  public boolean inPlayerChoice() {
    return state.inPlayerChoice();
  }

  public boolean inEnemyChoice() {
    return state.inEnemyChoice();
  }

  public boolean inFinishedTurn() {
    return state.inFinishedTurn();
  }

  public boolean inEndOfGame() {
    return state.inEndOfGame();
  }
  // endregion

  // region : CHARACTERS
  public PlayerCharacter createEngineer(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter engineer = new Engineer(name, hp, defense, turnsQueue);
    engineer.equip(weapon);
    return engineer;
  }

  public PlayerCharacter createKnight(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter knight = new Knight(name, hp, defense, turnsQueue);
    knight.equip(weapon);
    return knight;
  }
  
  public PlayerCharacter createThief(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter thief = new Thief(name, hp, defense, turnsQueue);
    thief.equip(weapon);
    return thief;
  }

  public Mage createBlackMage(String name, int hp, int mp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage blackMage = new BlackMage(name, hp, defense, mp, turnsQueue);
    blackMage.equip(weapon);
    return blackMage;
  }

  public Mage createWhiteMage(String name, int hp, int mp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage whiteMage = new WhiteMage(name, hp, defense, mp, turnsQueue);
    whiteMage.equip(weapon);
    return whiteMage;
  }

  public Enemy createEnemy(String name, int weight, int hp, int defense)
      throws InvalidStatValueException {
    return new Enemy(name, weight, hp, defense, turnsQueue);
  }
  // endregion

  // region : WEAPONS
  public Weapon createAxe(String name, int damage, int weight) {
    return new Axe(name, damage, weight);
  }

  public Weapon createBow(String name, int damage, int weight) {
    return new Bow(name, damage, weight);
  }

  public Weapon createKnife(String name, int damage, int weight) {
    return new Knife(name, damage, weight);
  }

  public Weapon createStaff(String name, int damage, int magicDamage, int weight) {
    return new Staff(name, damage, magicDamage, weight);
  }

  public Weapon createSword(String name, int damage, int weight) {
    return new Sword(name, damage, weight);
  }
  // endregion

  // region : GAME ACTIONS
  public void strike(GameCharacter attacker, GameCharacter target)
      throws InvalidStatValueException {
    attacker.strike(target);
  }

  public void cast(Mage mage, GameCharacter target)
    throws RestrictedSpellException, InvalidStatValueException, MissingStatException {
    mage.cast(target);
  }

//  public void castCure(Mage attacker, GameCharacter target) throws RestrictedSpellException, InvalidStatValueException {
//    attacker.cast(new Cure(), target);
//  }
//
//  public void castFire(Mage attacker, GameCharacter target) throws RestrictedSpellException, InvalidStatValueException {
//    attacker.cast(new Fire(), target);
//  }
//
//  public void castParalyze(Mage attacker, GameCharacter target) throws RestrictedSpellException, InvalidStatValueException {
//    attacker.cast(new Paralyze(), target);
//  }
//
//  public void castThunder(Mage attacker, GameCharacter target) throws RestrictedSpellException, InvalidStatValueException {
//    attacker.cast(new Thunder(), target);
//  }
//
//  public void castVenom(Mage attacker, GameCharacter target) throws RestrictedSpellException, InvalidStatValueException {
//    attacker.cast(new Venom(), target);
//  }
  // endregion

  public void waitTurn(GameCharacter character) {
    character.waitTurn();
  }

  public void onPlayerWin() {
    System.out.println("You win!");
  }

  public void onEnemyWin() {
    System.out.println("You lose :(");
  }

  boolean checkWinner() {
    boolean win = true;
    for (PlayerCharacter ally : playerCharacters) {
      if (ally.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      onPlayerWin();
      return true;
    }
    win = true;
    for (Enemy enemy : enemyCharacters) {
      if (enemy.getCurrentHp() != 0) {
        win = false;
        break;
      }
    }
    if (win) {
      onEnemyWin();
      return true;
    }
    return false;
  }

  public void play()
      throws InvalidStatValueException, RestrictedWeaponException {
    while (!checkWinner()) { // End of game condition.
      // TODO implement game dynamic
    }
    System.out.println("GAME OVER");
  }
}
