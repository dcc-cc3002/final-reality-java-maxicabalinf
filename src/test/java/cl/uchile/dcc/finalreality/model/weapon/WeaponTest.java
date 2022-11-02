package cl.uchile.dcc.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class WeaponTest {
  Weapon axe, bow, knife, sword, staff1, staff2;

  @BeforeEach
  public void setUp() {
    axe = new Axe("axe", 40, 14);
    bow = new Bow("bow", 40, 2);
    knife = new Knife("knife", 12, 44);
    sword = new Sword("sword", 20, 15);
    staff1 = new Staff("staff1", 78, 29, 10);
    staff2 = new Staff("staff2", 83, 12, 65);
  }

  @Test
  public void getNameTest() {
    // getName() must return the declared name of the weapon.
    assertEquals("sword", sword.getName());
    assertNotEquals("a", sword.getName());
  }

  @Test
  public void getDamage() {
    // getDamage() must return the declared weapon damage.
    assertEquals(20, sword.getDamage());
    assertNotEquals(3, sword.getDamage());
  }

  @Test
  public void getWeight() {
    // getWeight() must return the declared weight of the weapon.
    assertEquals(15, sword.getWeight());
    assertNotEquals(0, sword.getWeight());
  }

  @Test
  public void getType() {
    // getType() must return the declared type of the weapon.
    assertEquals("SWORD", sword.getType());
    assertNotEquals("other", sword.getType());
  }

  @Test
  public void getMagicDamage() throws MissingStatException {
    // Only Staffs have magic damage.
    assertThrows(MissingStatException.class,
      () -> axe.getMagicDamage());
    assertThrows(MissingStatException.class,
      () -> bow.getMagicDamage());
    assertThrows(MissingStatException.class,
      () -> knife.getMagicDamage());
    assertThrows(MissingStatException.class,
      () -> sword.getMagicDamage());

    assertEquals(29, staff1.getMagicDamage());
    assertNotEquals(staff2.getMagicDamage(), staff1.getMagicDamage());
  }

  @Test
  public void testEquals() {
    assertEquals(new Axe("axe", 40, 14), axe);
    assertEquals(new Bow("bow", 40, 2), bow);
    assertEquals(new Knife("knife", 12, 44), knife);
    assertEquals(
      new Staff("staff1", 78, 29, 10), staff1
    );
    assertEquals(new Sword("sword", 20, 15), sword);
    assertNotEquals(knife, axe);
  }

  @Test
  public void testHashCode() {
    assertEquals(
      new Sword("sword", 20, 15).hashCode(), sword.hashCode()
    );
    assertNotEquals(
      new Sword("sword", 20, 9).hashCode(), sword.hashCode()
    );
    assertEquals(
      new Staff("staff1", 78, 29, 10).hashCode(),
      staff1.hashCode()
    );
    assertNotEquals(staff2.hashCode(), staff1.hashCode());
    assertNotEquals(axe.hashCode(), sword.hashCode());
  }

  @Test
  public void testToString() {
    // toString() must return something like "Weapon{name='%s', damage=%d, weight=%d, type=%s}"
    assertEquals(
      new Sword("sword", 20, 15).toString(), sword.toString()
    );
    assertNotEquals(
      "sword", sword.toString()
    );
  }
}