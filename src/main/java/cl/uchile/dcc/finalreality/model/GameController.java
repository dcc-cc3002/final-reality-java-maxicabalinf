package cl.uchile.dcc.finalreality.model;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
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

  // region : CHARACTERS
  private PlayerCharacter createEngineer(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter engineer = new Engineer(name, hp, defense, turnsQueue);
    engineer.equip(weapon);
    return engineer;
  }

  private PlayerCharacter createKnight(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter knight = new Knight(name, hp, defense, turnsQueue);
    knight.equip(weapon);
    return knight;
  }
  
  private PlayerCharacter createThief(String name, int hp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Sword("sword", 20, 20);
    PlayerCharacter thief = new Thief(name, hp, defense, turnsQueue);
    thief.equip(weapon);
    return thief;
  }

  private Mage createBlackMage(String name, int hp, int mp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage blackMage = new BlackMage(name, hp, defense, mp, turnsQueue);
    blackMage.equip(weapon);
    return blackMage;
  }

  private Mage createWhiteMage(String name, int hp, int mp, int defense)
      throws InvalidStatValueException, RestrictedWeaponException {
    Weapon weapon = new Staff("staff", 20, 20, 20);
    Mage whiteMage = new WhiteMage(name, hp, defense, mp, turnsQueue);
    whiteMage.equip(weapon);
    return whiteMage;
  }

  private Enemy createEnemy(String name, int weight, int hp, int defense)
      throws InvalidStatValueException {
    return new Enemy(name, weight, hp, defense, turnsQueue);
  }
  // endregion

  // region : WEAPONS
  private Weapon createAxe(String name, int damage, int weight) {
    return new Axe(name, damage, weight);
  }

  private Weapon createBow(String name, int damage, int weight) {
    return new Bow(name, damage, weight);
  }

  private Weapon createKnife(String name, int damage, int weight) {
    return new Knife(name, damage, weight);
  }

  private Weapon createStaff(String name, int damage, int magicDamage, int weight) {
    return new Staff(name, damage, magicDamage, weight);
  }

  private Weapon createSword(String name, int damage, int weight) {
    return new Sword(name, damage, weight);
  }
  // endregion

  // region : ACTIONS
  private void attack(GameCharacter attacker, GameCharacter target)
      throws InvalidStatValueException {
    attacker.attack(target);
  }

  private void castCure(Mage attacker, GameCharacter target) throws RestrictedSpellException {
    attacker.cast(new Cure(), target);
  }

  private void castFire(Mage attacker, GameCharacter target) throws RestrictedSpellException {
    attacker.cast(new Fire(), target);
  }

  private void castParalyze(Mage attacker, GameCharacter target) throws RestrictedSpellException {
    attacker.cast(new Paralyze(), target);
  }

  private void castThunder(Mage attacker, GameCharacter target) throws RestrictedSpellException {
    attacker.cast(new Thunder(), target);
  }

  private void castVenom(Mage attacker, GameCharacter target) throws RestrictedSpellException {
    attacker.cast(new Venom(), target);
  }
  // endregion

  private void waitTurn(GameCharacter character) {
    character.waitTurn();
  }

  private void onPlayerWin() {
    System.out.println("You win!");
  }

  private void onEnemyWin() {
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
