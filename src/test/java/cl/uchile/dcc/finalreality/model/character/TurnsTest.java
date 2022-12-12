package cl.uchile.dcc.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.*;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.player.normal.Thief;
import cl.uchile.dcc.finalreality.model.items.weapon.Sword;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TurnsTest extends Thread {
  Enemy enemy;
  Thief thief;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>(2);
    enemy = new Enemy("enemy", 20, 48, 38, queue);
    thief = new Thief("thief", 18, 93, queue);
  }

  @Test
  public void waitTurn()
      throws InterruptedException, RestrictedWeaponException, NullWeaponException {
    enemy.waitTurn();
    Thread.sleep(6000);
    assertTrue(queue.contains(enemy));
    Sword sword = new Sword("sword", 92, 47);
    thief.equip(sword);
    thief.waitTurn();
    Thread.sleep(6000);
    assertTrue(queue.contains(thief));
  }

}