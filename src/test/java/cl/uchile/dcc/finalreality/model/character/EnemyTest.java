package cl.uchile.dcc.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest {
  Enemy enemy1, enemy2;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>();
    enemy1 = new Enemy("en1", 82, 92, 265, queue);
    enemy2 = new Enemy("en2", 29, 64, 28, queue);
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

    Enemy enemy3, enemy4;
    enemy3 = enemy1; enemy4= enemy1;
    assertEquals(enemy3, enemy4);

    String str = "a";
    assertNotEquals(enemy1, str);
  }

  @Test
  void testToString() throws InvalidStatValueException {
    assertEquals(new Enemy("en1", 82, 92, 265, queue).toString(), enemy1.toString());
    assertEquals(new Enemy("en2", 29, 64, 28, queue).toString(), enemy2.toString());
    assertNotEquals(enemy2.toString(), enemy1.toString());
  }

  @Test
  void testHashCode() throws InvalidStatValueException {
    assertEquals(new Enemy("en1", 82, 92, 265, queue).hashCode(), enemy1.hashCode());
    assertEquals(new Enemy("en2", 29, 64, 28, queue).hashCode(), enemy2.hashCode());
    assertNotEquals(enemy2.hashCode(), enemy1.hashCode());
  }
}