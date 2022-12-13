package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.controller.GameController;
import cl.uchile.dcc.finalreality.controller.state.*;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.state.*;
import cl.uchile.dcc.finalreality.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameController.getGameController;
import static org.junit.jupiter.api.Assertions.*;

class CharacterStateTest {

  public CharacterState burnt;
  public CharacterState envenomed;
  public CharacterState normal;
  public CharacterState paralyzed;
  public BlockingQueue<GameCharacter> turnsQueue;
  public List<PlayerCharacter> playerCharacters;
  public List<Enemy> enemyCharacters;
  public GameView view;
  public GameController controller;

  @BeforeEach
  void setUp() {
    turnsQueue = new LinkedBlockingQueue<>();
    playerCharacters = new ArrayList<>();
    enemyCharacters = new ArrayList<>();
    view = new GameView();
    controller = getGameController(
      turnsQueue, playerCharacters, enemyCharacters, view
    );
    burnt = new Burnt();
    envenomed = new Envenomed();
    normal = new Normal();
    paralyzed = new Paralyzed();
  }

  @Test
  void setCharacter() throws RestrictedWeaponException, InvalidStatValueException {
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    burnt.setCharacter(bMage);
    envenomed.setCharacter(bMage);
    normal.setCharacter(bMage);
    paralyzed.setCharacter(bMage);

    assertEquals(bMage, burnt.getCharacter());
    assertEquals(bMage, envenomed.getCharacter());
    assertEquals(bMage, normal.getCharacter());
    assertEquals(bMage, paralyzed.getCharacter());
  }

  @Test
  void changeState() throws RestrictedWeaponException, InvalidStatValueException {
    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    // setCharacter is an association in one way, whereas changeState is both ways.
    burnt.setCharacter(bMage);
    burnt.changeState(burnt);
    assertTrue(bMage.isBurnt());
    burnt.changeState(new Normal());
    // Verify change
    assertTrue(bMage.isNormal());

  }

  @Test
  void isBurnt() {
    // Only Burnt state verifies this.
    assertFalse(normal.isBurnt());
  }

  @Test
  void isEnvenomed() {
    // Only Envenomed state verifies this.
    assertFalse(normal.isEnvenomed());
  }

  @Test
  void isNormal() {
    // Only Normal state verifies this.
    assertFalse(burnt.isNormal());
  }

  @Test
  void isParalyzed() {
    // Only Paralyzed state verifies this.
    assertFalse(normal.isParalyzed());
  }

  @Test
  void equalsWorking() {
    assertEquals(new Burnt(), burnt);
    assertEquals(new Envenomed(), envenomed);
    assertEquals(new Normal(), normal);
    assertEquals(new Paralyzed(), paralyzed);

    // Equals by same reference ("==").
    CharacterState burnt2 = burnt;
    CharacterState envenomed2 = envenomed;
    CharacterState normal2 = normal;
    CharacterState paralyzed2 = paralyzed;

    assertEquals(burnt2, burnt);
    assertEquals(envenomed2, envenomed);
    assertEquals(normal2, normal);
    assertEquals(paralyzed2, paralyzed);

    // Different class object must not be equal.
    assertNotEquals(burnt, paralyzed);
    assertNotEquals(envenomed, burnt);
    assertNotEquals(normal, envenomed);
    assertNotEquals(paralyzed, normal);
  }

  @Test
  void hashCodeEquals() throws RestrictedWeaponException, InvalidStatValueException {
    CharacterState burnt2 = new Burnt();
    CharacterState envenomed2 = new Envenomed();
    CharacterState normal2 = new Normal();
    CharacterState paralyzed2 = new Paralyzed();

    PlayerCharacter bMage = controller.createBlackMage(
      "bMage", 20, 40, 20);
    burnt.setCharacter(bMage); burnt2.setCharacter(bMage);
    envenomed.setCharacter(bMage); envenomed2.setCharacter(bMage);
    normal.setCharacter(bMage); normal2.setCharacter(bMage);
    paralyzed.setCharacter(bMage); paralyzed2.setCharacter(bMage);

    assertEquals(burnt.hashCode(),
      burnt2.hashCode());
    assertEquals(envenomed.hashCode(),
      envenomed2.hashCode());
    assertEquals(normal.hashCode(),
      normal2.hashCode());
    assertEquals(paralyzed.hashCode(),
      paralyzed2.hashCode());
  }
}