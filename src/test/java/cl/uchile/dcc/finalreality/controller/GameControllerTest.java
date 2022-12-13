package cl.uchile.dcc.finalreality.controller;

import static cl.uchile.dcc.finalreality.controller.GameController.getGameController;
import static cl.uchile.dcc.finalreality.controller.GameController.rand;
import static org.junit.jupiter.api.Assertions.*;

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
import cl.uchile.dcc.finalreality.model.items.weapon.*;
import cl.uchile.dcc.finalreality.view.GameView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


class GameControllerTest {

  public BlockingQueue<GameCharacter> turnsQueue;
  public List<PlayerCharacter> playerCharacters;
  public List<Enemy> enemyCharacters;
  public GameView view;
  public  GameController controller;

  @BeforeEach
  void setUp() {
    turnsQueue = new LinkedBlockingQueue<>();
    playerCharacters = new ArrayList<>();
    enemyCharacters = new ArrayList<>();
    view = new GameView();
    controller = getGameController(
      turnsQueue, playerCharacters, enemyCharacters, view
    );
  }

  @AfterEach
  void reset() {
    controller.resetGame();
  }

  @Test
  void playEnemy() throws InvalidTransitionException, NullWeaponException, InterruptedException, MissingStatException, RestrictedSpellException, InvalidStatValueException, RestrictedWeaponException {
    // region : SET GAME ENVIRONMENT AND GO TO ENEMY CHOICE
    // Weights set so the two enemies come first. All PlayerCharacters all set to have weight = 20
    // by default.
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    playerCharacters.add(bMage);
    Enemy enemy1 = controller.createEnemy("enemy1", 14, 20, 14);
    enemyCharacters.add(enemy1);
    Enemy enemy2 = controller.createEnemy("enemy2", 14, 20, 14);
    enemyCharacters.add(enemy2);

    // At the beggining of a turn, the game should be in WaitingQueue state.
    assertTrue(controller.inWaitingQueue());

    //// No character in the turnsQueue yet, so emptyQueue() should be true.
    assertTrue(controller.emptyQueue());

    //// Enqueue all characters.
    for (GameCharacter character : playerCharacters) {
      controller.waitTurn(character);
    }
    for (GameCharacter character : enemyCharacters) {
      controller.waitTurn(character);
    }
    Thread.sleep(6000);

    //// It should be able to execute pickUpCharacter() too.
    controller.pickUpCharacter();

    // After that, the game should be in UndeterminedCharacter state. It should be able to
    // execute checkCharacter().
    assertTrue(controller.inUndeterminedCharacter());
    controller.checkCharacter();

    // Now, the game should be in PlayerChoice state. User actions must be available
    assertTrue(controller.inEnemyChoice());
    // endregion

    // region : USER ACTIONS
    // Strike cannot happen if there is no selected Target.
    assertThrows(InvalidTransitionException.class,
      () -> controller.strike()
    );

    // After setting a Target, an attack can be executed.
    //// Setting target
    controller.setTarget(playerCharacters.get(0));

    //// Save previous stats for comparison.
    int previousHp = playerCharacters.get(0).getCurrentHp();

    //// Execute attack.
    controller.strike();

    //// Verify effect.
    assertEquals(Math.max(previousHp - 10, 0),
      playerCharacters.get(0).getCurrentHp()
    );
    // endregion

    // region : AFTER USER
    // Game now should be in FinishedTurn state.
    assertTrue(controller.inFinishedTurn());

    // As the player finished its turn, is has to return to the queue.
    //// The Black Mage still is not in the turnsQueue
    GameCharacter character = controller.getActualCharacter();
    BlockingQueue<GameCharacter> queue = controller.getTurnsQueue();
    assertFalse(queue.contains(character));

    //// Now it should be.
    controller.beginTimer();
    Thread.sleep(6000);
    queue = controller.getTurnsQueue();
    assertTrue(queue.contains(character));

    // Now the game returns to its initial state.
    assertNull(controller.getActualCharacter());
    assertTrue(controller.inWaitingQueue());

    // The mage Hp was 20 initially, and the attack was 10, so it should be 10.
    assertEquals(10, playerCharacters.get(0).getCurrentHp());

    // There should be no winner yet, so it should be impossible to end the game.
    assertFalse(controller.checkWinner());
    assertFalse(controller.inEndOfGame());

    // Continue with the other characters waiting. Everything will be executed again.
    assertTrue(controller.inWaitingQueue());
    assertFalse(controller.emptyQueue());
    controller.pickUpCharacter();
    assertTrue(controller.inUndeterminedCharacter());
    controller.checkCharacter();
    assertTrue(controller.inEnemyChoice());
    assertFalse(controller.inPlayerChoice()); // Just to make sure.
    controller.setTarget(playerCharacters.get(0));
    previousHp = playerCharacters.get(0).getCurrentHp();
    controller.strike();
    assertEquals(Math.max(previousHp - 10, 0),
      playerCharacters.get(0).getCurrentHp()
    );
    assertTrue(controller.inFinishedTurn());
    character = controller.getActualCharacter();
    queue = controller.getTurnsQueue();
    assertFalse(queue.contains(character));
    controller.beginTimer(); // Turn end.
    Thread.sleep(6000);
    queue = controller.getTurnsQueue();
    assertTrue(queue.contains(character));
    assertNull(controller.getActualCharacter());
    assertTrue(controller.inWaitingQueue());

    // By now, the mage has recieved two attacks of damage = 10, with initialHp = 20, so it should
    // be dead.
    assertTrue(controller.checkWinner());
    assertTrue(controller.inEndOfGame());
    // endregion
  }

  @Test
  void playUser() throws InvalidTransitionException, InterruptedException,
    RestrictedWeaponException, InvalidStatValueException, RestrictedSpellException,
    MissingStatException, NullWeaponException {
    // region : SET GAME ENVIRONMENT AND GO TO PLAYER CHOICE
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 40, 40, 20);
    playerCharacters.add(bMage);
    Enemy enemy = controller.createEnemy("enemy", 30, 20, 14);
    enemyCharacters.add(enemy);

    // At the beggining of a turn, the game should be in WaitingQueue state.
    assertTrue(controller.inWaitingQueue());

    //// No character in the turnsQueue yet, so emptyQueue() should be true.
    assertTrue(controller.emptyQueue());

    //// It should be able to execute pickUpCharacter() too.
    bMage.waitTurn(); enemy.waitTurn();
    Thread.sleep(6000);
    controller.pickUpCharacter();

    // After that, the game should be in UndeterminedCharacter state. It should be able to
    // execute checkCharacter().
    assertTrue(controller.inUndeterminedCharacter());
    controller.checkCharacter();

    // Now, the game should be in PlayerChoice state. User actions must be available
    assertTrue(controller.inPlayerChoice());
    Weapon knife = controller.createKnife("bow", 23, 14);
    // endregion

    // region : USER ACTIONS

    // A Weapon change should occur.
    controller.changeWeapon((PlayerCharacter) controller.getActualCharacter(),
      knife
    );
    assertEquals(knife,
      ((PlayerCharacter) controller.getActualCharacter()).getEquippedWeapon()
    );


    // A Spell change should occur.
    Spell thunder = controller.createThunder();
    controller.changeSpell((Mage) controller.getActualCharacter(),
      thunder
    );
    assertEquals(thunder,
      ((Mage) controller.getActualCharacter()).getEquippedSpell()
    );


    // Strike cannot happen if there is no selected Target.
    assertThrows(InvalidTransitionException.class,
      () -> controller.strike()
    );

    // Cast cannot be executed if there is no selected Target, even if there's an equipped Staff.
    controller.changeWeapon((PlayerCharacter) controller.getActualCharacter(),
      controller.createStaff("staff", 29, 49, 12)
    );
    assertThrows(InvalidTransitionException.class,
      () -> controller.cast((Mage) controller.getActualCharacter())
    );

    // After setting a Target, an attack can be executed.
    //// Setting target
    controller.setTarget(enemyCharacters.get(0));

    //// Mages cannot cast any Spell if they don't have an equipped Staff.
    ////// Set a weapon different from a Staff
    controller.changeWeapon((PlayerCharacter) controller.getActualCharacter(), knife
    );

    ////// Assure random will allow Thunder to be cast.
    rand.setSeed(10);
    Random rand2 = new Random(10);
    while (rand2.nextInt(10) >= 3) {
      rand.nextInt(10);
    }
    assertThrows(RestrictedSpellException.class,
      () -> controller.cast((Mage) controller.getActualCharacter())
    );

    //// Set Staff so casting is enabled.
    controller.changeWeapon((PlayerCharacter) controller.getActualCharacter(),
      controller.createStaff("staff", 29, 49, 12)
    );

    //// Assure random will allow Thunder to be cast.
    rand.setSeed(10);
    rand2.setSeed(10);
    while (rand2.nextInt(10) >= 3) {
      rand.nextInt(10);
    }

    //// Save previous stats for comparison.
    int previousHp = enemyCharacters.get(0).getCurrentHp();
    int magicDam = (
      (PlayerCharacter) controller.getActualCharacter()
    ).getEquippedWeapon().getMagicDamage();

    //// Cast Thunder Spell.
    controller.cast((Mage) controller.getActualCharacter());

    //// Verify Thunder effect.
    assertEquals(Math.max(previousHp - magicDam, 0),
      enemyCharacters.get(0).getCurrentHp()
    );
    // endregion

    // region : AFTER USER
    // Game now should be in FinishedTurn state.
    assertTrue(controller.inFinishedTurn());

    // As the player finished its turn, is has to return to the queue.
    //// The Black Mage still is not in the turnsQueue
    GameCharacter character = controller.getActualCharacter();
    BlockingQueue<GameCharacter> queue = controller.getTurnsQueue();
    assertFalse(queue.contains(character));

    //// Now it should be.
    controller.beginTimer();
    Thread.sleep(6000);
    queue = controller.getTurnsQueue();
    assertTrue(queue.contains(character));

    // Now the game returns to its initial state.
    assertNull(controller.getActualCharacter());
    assertTrue(controller.inWaitingQueue());

    // The enemy Hp was 20 initially, and the attack was 20, so it should be dead.
    assertEquals(0, enemyCharacters.get(0).getCurrentHp());

    // Then, all enemies are dead, so verifying winner should return true and should end the game.
    assertTrue(controller.checkWinner());
    assertTrue(controller.inEndOfGame());
    // endregion
  }

  @Test
  void characterFactory() throws InvalidStatValueException, RestrictedWeaponException {
    String name = "character";
    int maxHp = 12, maxMp = 74, defense = 56, weight = 46;

    // Character Factory methods must return equal object to those created by the model.
    //// Engineer
    assertEquals(new Engineer(name, maxHp, defense, turnsQueue),
      controller.createEngineer(name, maxHp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createEngineer(name, -1, -2)
    );

    //// Knight
    assertEquals(new Knight(name, maxHp, defense, turnsQueue),
      controller.createKnight(name, maxHp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createKnight(name, -1, -2)
    );

    //// Thief
    assertEquals(new Thief(name, maxHp, defense, turnsQueue),
      controller.createThief(name, maxHp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createThief(name, -1, -2)
    );

    //// BlackMage
    assertEquals(new BlackMage(name, maxHp, defense, maxMp, turnsQueue),
      controller.createBlackMage(name, maxHp, maxMp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createBlackMage(name, -1, -2, 0)
    );

    //// WhiteMage
    assertEquals(new WhiteMage(name, maxHp, defense, maxMp, turnsQueue),
      controller.createWhiteMage(name, maxHp, maxMp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createWhiteMage(name, -1, -2, 0)
    );

    //// Enemy
    assertEquals(new Enemy(name, weight, maxHp, defense, turnsQueue),
      controller.createEnemy(name, weight, maxHp, defense)
    );
    assertThrows(InvalidStatValueException.class,
      () -> controller.createEnemy(name, -1, 2,-2)
    );
  }


  @Test
  void weaponFactory() {
    String name = "weapon";
    int damage = 84, weight = 28, magicDamage = 37;
    // Character Factory methods must return equal object to those created by the model.
    //// Axe
    assertEquals(new Axe(name, damage, weight),
      controller.createAxe(name, damage, weight)
    );
    //// Bow
    assertEquals(new Bow(name, damage, weight),
      controller.createBow(name, damage, weight)
    );
    //// Knife
    assertEquals(new Knife(name, damage, weight),
      controller.createKnife(name, damage, weight)
    );
    //// Staff
    assertEquals(new Staff(name, damage, magicDamage, weight),
      controller.createStaff(name, damage, magicDamage, weight)
    );
    //// Sword
    assertEquals(new Sword(name, damage, weight),
      controller.createSword(name, damage, weight));
  }

  @Test
  void spellFactory() {
    // Spell Factory methods must return equal object to those created by the model.
    // Cure
    assertEquals(new Cure(), controller.createCure());

    // Fire
    assertEquals(new Fire(), controller.createFire());

    // Paralyze
    assertEquals(new Paralyze(), controller.createParalyze());

    // Thunder
    assertEquals(new Thunder(), controller.createThunder());

    // Venom
    assertEquals(new Venom(), controller.createVenom());
  }
}