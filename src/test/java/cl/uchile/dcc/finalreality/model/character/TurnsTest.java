package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class TurnsTest {
  Enemy enemy;
  PlayerCharacter thief;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>();
    enemy = new Enemy("enemy", 20, 48, 38, queue);
    thief = new Thief("thief", 18, 93, queue);
  }

  @Test
  public void waitTurn() throws InterruptedException {
    enemy.waitTurn();
    Thread.sleep(6000);
    assertTrue(queue.contains(enemy));
    Weapon sword = new Weapon("sword", 92, 47, "SWORD");
    thief.equip(sword);
    thief.waitTurn();
    Thread.sleep(6000);
    assertTrue(queue.contains(thief));
    thief.waitTurn();
  }

}