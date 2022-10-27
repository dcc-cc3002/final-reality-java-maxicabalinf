package cl.uchile.dcc.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
  Enemy enemy1;
  Enemy enemy2;
  BlackMage bmg;
  WhiteMage wmg;
  Engineer eng;
  Knight kng;
  Thief thf;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>();
    enemy1 = new Enemy("en1", 82, 92, 265, queue);
    enemy2 = new Enemy("en2", 29, 64, 28, queue);
    bmg = new BlackMage("bmg", 95, 347, 72, queue);
    wmg = new WhiteMage("wmg", 32, 47, 378,queue);
    eng = new Engineer("eng", 97, 48, queue);
    kng = new Knight("kng", 52, 47, queue);
    thf = new Thief("thf", 18, 94, queue);
  }

  @Test
  void getWeight() {
    assertEquals( 82, enemy1.getWeight());
    assertNotEquals(enemy2.getWeight(), enemy1.getWeight());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(new Enemy("en1", 82, 92, 265, queue), enemy1);
    assertEquals(new Enemy("en2", 29, 64, 28, queue), enemy2);
    assertNotEquals(enemy2, enemy1);
    assertNotEquals(bmg, enemy1);
    assertNotEquals(wmg, enemy1);
    assertNotEquals(eng, enemy1);
    assertNotEquals(kng, enemy1);
    assertNotEquals(thf, enemy1);

    Enemy enemy3, enemy4;
    enemy3 = enemy1; enemy4= enemy1;
    assertEquals(enemy3, enemy4);

    Weapon sword = new Weapon("sword", 82,74,"SWORD");
    assertFalse(enemy1.equals(sword));
  }

  @Test
  void testToString() throws InvalidStatValueException {
    assertEquals(new Enemy("en1", 82, 92, 265, queue).toString(), enemy1.toString());
    assertEquals(new Enemy("en2", 29, 64, 28, queue).toString(), enemy2.toString());
    assertNotEquals(enemy2.toString(), enemy1.toString());
    assertNotEquals(bmg.toString(), enemy1.toString());
    assertNotEquals(wmg.toString(), enemy1.toString());
    assertNotEquals(eng.toString(), enemy1.toString());
    assertNotEquals(kng.toString(), enemy1.toString());
    assertNotEquals(thf.toString(), enemy1.toString());
  }

  @Test
  void testHashCode() throws InvalidStatValueException {
    assertEquals(new Enemy("en1", 82, 92, 265, queue).hashCode(), enemy1.hashCode());
    assertEquals(new Enemy("en2", 29, 64, 28, queue).hashCode(), enemy2.hashCode());
    assertNotEquals(enemy2.hashCode(), enemy1.hashCode());
    assertNotEquals(bmg.hashCode(), enemy1.hashCode());
    assertNotEquals(wmg.hashCode(), enemy1.hashCode());
    assertNotEquals(eng.hashCode(), enemy1.hashCode());
    assertNotEquals(kng.hashCode(), enemy1.hashCode());
    assertNotEquals(thf.hashCode(), enemy1.hashCode());
  }
}