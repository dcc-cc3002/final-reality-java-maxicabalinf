package cl.uchile.dcc.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class WeaponTest {
  Weapon sword;
  Weapon axe;
  Staff staff1;
  Staff staff2;

  @BeforeEach
  public void setUp() {
    sword = new Weapon("myWeapon", 20, 15, "SWORD");
    axe = new Weapon("myAxe", 40, 14, "AXE");
    staff1 = new Staff("oneStaff", 78, 29, 10);
    staff2 = new Staff("anotherStaff", 83, 12, 65);
  }

  @Test
  public void getNameTest() {
    // getName() must return the declared name of the weapon.
    assertEquals("myWeapon", sword.getName());
    assertEquals("oneStaff", staff1.getName());
    assertNotEquals("a", sword.getName());
    assertNotEquals(staff2.getName(), staff1.getName());
  }

  @Test
  public void getDamage() {
    // getDamage() must return the declared damage of the weapon.
    assertEquals(20, sword.getDamage());
    assertEquals(78, staff1.getDamage());
    assertNotEquals(3, sword.getDamage());
    assertNotEquals(34, staff1.getDamage());
  }

  @Test
  public void getWeight() {
    // getWeight() must return the declared weight of the weapon.
    assertEquals(15, sword.getWeight());
    assertEquals(10, staff1.getWeight());
    assertNotEquals(0, sword.getWeight());
    assertNotEquals(staff2.getWeight(), staff1.getWeight());
  }

  @Test
  public void getType() {
    // getType() must return the declared type of the weapon.
    assertEquals("SWORD", sword.getType());
    assertNotEquals("other", sword.getType());
    assertEquals(staff2.getType(), staff1.getType());
    assertNotEquals(sword.getType(), staff1.getType());
  }

  @Test
  public void getMagicDamage() {
    assertEquals(29, staff1.getMagicDamage());
    assertNotEquals(staff2.getMagicDamage(), staff1.getMagicDamage());
  }

  @Test
  public void testEquals() {
    assertEquals(new Weapon("myWeapon", 20, 15, "SWORD"), sword);
    assertNotEquals(new Weapon("myWeapon", 20, 9, "SWORD"), sword);
    assertEquals(new Staff("oneStaff", 78, 29, 10), staff1);
    assertNotEquals(axe, sword);
    assertNotEquals(staff2, staff1);
    assertNotEquals(axe, staff1);
  }

  @Test
  public void testHashCode() {
    assertEquals(
      new Weapon("myWeapon", 20, 15, "SWORD").hashCode(), sword.hashCode()
    );
    assertNotEquals(
      new Weapon("myWeapon", 20, 9, "SWORD").hashCode(), sword.hashCode()
    );
    assertEquals(
      new Staff("oneStaff", 78, 29, 10).hashCode(), staff1.hashCode()
    );
    assertNotEquals(staff2.hashCode(), staff1.hashCode());
    assertNotEquals(axe.hashCode(), sword.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(
      new Weapon("myWeapon", 20, 15, "SWORD").toString(), sword.toString()
    );
    assertEquals(
      new Staff("oneStaff", 78, 29, 10).toString(), staff1.toString()
    );
    assertNotEquals(staff2.toString(), staff1.toString());
    assertNotEquals(
      new Weapon("myWeapon", 20, 9, "SWORD").toString(), sword.toString()
    );
    assertNotEquals(axe.hashCode(), sword.hashCode());
  }
}