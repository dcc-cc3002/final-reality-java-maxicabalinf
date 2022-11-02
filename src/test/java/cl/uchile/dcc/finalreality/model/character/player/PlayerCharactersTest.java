package cl.uchile.dcc.finalreality.model.character.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.*;
import cl.uchile.dcc.finalreality.model.weapon.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class PlayerCharactersTest {
  BlackMage bmg;
  WhiteMage wmg;
  Engineer eng;
  Knight kng;
  Thief thf;
  Weapon axe, bow, knife, staff, sword;

  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>();
    bmg = new BlackMage("bmg", 95, 347, 72, queue);
    wmg = new WhiteMage("wmg", 32, 47, 378,queue);
    eng = new Engineer("eng", 97, 48, queue);
    kng = new Knight("kng", 52, 47, queue);
    thf = new Thief("thf", 18, 94, queue);
    sword = new Sword("myWeapon", 20, 15);
    staff = new Staff("oneStaff", 78, 29, 10);
    bow = new Bow("aBow", 29, 47);
    axe = new Axe("axe", 20, 94);
    knife = new Knife("superKnife", 10, 47);

  }

  @Test
  void getCurrentMp() {
    assertEquals(72, bmg.getCurrentMp());
    assertEquals(378, wmg.getCurrentMp());
    assertNotEquals(wmg.getCurrentMp(), bmg.getCurrentMp());
  }

  @Test
  void setCurrentMp() throws InvalidStatValueException {
    bmg.setCurrentMp(0);
    wmg.setCurrentMp(0);
    assertEquals(wmg.getCurrentMp(), bmg.getCurrentMp());
    assertThrows(InvalidStatValueException.class, () -> bmg.setCurrentMp(-1));
    assertThrows(InvalidStatValueException.class, () -> wmg.setCurrentMp(-1));
    assertThrows(InvalidStatValueException.class, () -> bmg.setCurrentMp(bmg.getMaxMp()+1));
    assertThrows(InvalidStatValueException.class, () -> wmg.setCurrentMp(wmg.getMaxMp()+1));
  }

  @Test
  void getMaxMp() {
    assertEquals(72, bmg.getMaxMp());
    assertEquals(378, wmg.getMaxMp());
    assertNotEquals(wmg.getMaxMp(), bmg.getMaxMp());
  }

  @Test
  void equip() throws RestrictedWeaponException {
    assertNull(bmg.getEquippedWeapon());
    assertNull(wmg.getEquippedWeapon());
    assertNull(eng.getEquippedWeapon());
    assertNull(kng.getEquippedWeapon());
    assertNull(thf.getEquippedWeapon());
    bmg.equip(staff);
    wmg.equip(staff);
    eng.equip(axe);
    kng.equip(knife);
    thf.equip(sword);
    assertNotNull(bmg.getEquippedWeapon());
    assertNotNull(wmg.getEquippedWeapon());
    assertNotNull(eng.getEquippedWeapon());
    assertNotNull(kng.getEquippedWeapon());
    assertNotNull(thf.getEquippedWeapon());
  }

  @Test
  void getEquippedWeapon() throws RestrictedWeaponException {
    bmg.equip(knife);
    wmg.equip(staff);
    eng.equip(axe);
    kng.equip(sword);
    thf.equip(bow);
    assertEquals(knife, bmg.getEquippedWeapon());
    assertEquals(staff, wmg.getEquippedWeapon());
    assertEquals(axe, eng.getEquippedWeapon());
    assertEquals(sword, kng.getEquippedWeapon());
    assertEquals(bow, thf.getEquippedWeapon());
    assertNotEquals(eng.getEquippedWeapon(), bmg.getEquippedWeapon());
    assertNotEquals(kng.getEquippedWeapon(), wmg.getEquippedWeapon());
    assertNotEquals(thf.getEquippedWeapon(), eng.getEquippedWeapon());
    assertNotEquals(eng.getEquippedWeapon(), kng.getEquippedWeapon());
    assertNotEquals(wmg.getEquippedWeapon(), thf.getEquippedWeapon());
  }

  @Test
  void waitTurn() {
  }

  @Test
  void getName() {
    assertEquals("bmg",bmg.getName());
    assertEquals("wmg",wmg.getName());
    assertEquals("eng",eng.getName());
    assertEquals("kng",kng.getName());
    assertEquals("thf",thf.getName());
  }

  @Test
  void setCurrentHp() throws InvalidStatValueException {
    bmg.setCurrentHp(0);
    wmg.setCurrentHp(0);
    eng.setCurrentHp(0);
    kng.setCurrentHp(0);
    thf.setCurrentHp(0);
    assertEquals(0, bmg.getCurrentHp());
    assertEquals(0, wmg.getCurrentHp());
    assertEquals(0, eng.getCurrentHp());
    assertEquals(0, kng.getCurrentHp());
    assertEquals(0, thf.getCurrentHp());
    assertThrows(InvalidStatValueException.class, () -> bmg.setCurrentHp(-1));
    assertThrows(InvalidStatValueException.class, () -> wmg.setCurrentHp(-1));
    assertThrows(InvalidStatValueException.class, () -> eng.setCurrentHp(-1));
    assertThrows(InvalidStatValueException.class, () -> kng.setCurrentHp(-1));
    assertThrows(InvalidStatValueException.class, () -> thf.setCurrentHp(-1));
    assertThrows(InvalidStatValueException.class, () -> bmg.setCurrentHp(bmg.getMaxHp()+1));
    assertThrows(InvalidStatValueException.class, () -> wmg.setCurrentHp(wmg.getMaxHp()+1));
    assertThrows(InvalidStatValueException.class, () -> eng.setCurrentHp(eng.getMaxHp()+1));
    assertThrows(InvalidStatValueException.class, () -> kng.setCurrentHp(kng.getMaxHp()+1));
    assertThrows(InvalidStatValueException.class, () -> thf.setCurrentHp(thf.getMaxHp()+1));
  }

  @Test
  void getCurrentHp() throws InvalidStatValueException {
    assertEquals(95, bmg.getCurrentHp());
    assertEquals(32, wmg.getCurrentHp());
    assertEquals(97, eng.getCurrentHp());
    assertEquals(52, kng.getCurrentHp());
    assertEquals(18, thf.getCurrentHp());
    assertNotEquals(wmg.getMaxMp(), bmg.getMaxMp());
    bmg.setCurrentHp(0);
    wmg.setCurrentHp(0);
    eng.setCurrentHp(0);
    kng.setCurrentHp(0);
    thf.setCurrentHp(0);
    assertNotEquals(95, bmg.getCurrentHp());
    assertNotEquals(32, wmg.getCurrentHp());
    assertNotEquals(97, wmg.getCurrentHp());
    assertNotEquals(52, wmg.getCurrentHp());
    assertNotEquals(18, wmg.getCurrentHp());
  }

  @Test
  void getMaxHp() {
    assertEquals(95, bmg.getMaxHp());
    assertEquals(32, wmg.getMaxHp());
    assertEquals(97, eng.getMaxHp());
    assertEquals(52, kng.getMaxHp());
    assertEquals(18, thf.getMaxHp());
  }

  @Test
  void getDefense() {
    assertEquals(347, bmg.getDefense());
    assertEquals(47, wmg.getDefense());
    assertEquals(48, eng.getDefense());
    assertEquals(47, kng.getDefense());
    assertEquals(94, thf.getDefense());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(new BlackMage("bmg", 95, 347, 72, queue), bmg);
    assertEquals(new WhiteMage("wmg", 32, 47, 378,queue), wmg);
    assertEquals(new Engineer("eng", 97, 48, queue), eng);
    assertEquals(new Knight("kng", 52, 47, queue), kng);
    assertEquals(new Thief("thf", 18, 94, queue), thf);
    assertNotEquals(kng, eng);
    assertNotEquals(eng, bmg);
    assertNotEquals(bmg, kng);

    BlackMage bmg1, bmg2;
    WhiteMage wmg1, wmg2;
    Engineer eng1, eng2;
    Knight kng1, kng2;
    Thief thf1, thf2;
    bmg1 = bmg; bmg2 = bmg;
    wmg1 = wmg; wmg2 = wmg;
    eng1 = eng; eng2 = eng;
    kng1 = kng; kng2 = kng;
    thf1 = thf; thf2 = thf;
    assertEquals(bmg1, bmg2);
    assertEquals(wmg1, wmg2);
    assertEquals(eng1, eng2);
    assertEquals(kng1, kng2);
    assertEquals(thf1, thf2);
  }

  @Test
  void testToString() throws InvalidStatValueException {
    assertEquals(new BlackMage("bmg", 95, 347, 72, queue).toString(),
      bmg.toString()
    );
    assertEquals(new WhiteMage("wmg", 32, 47, 378,queue).toString(),
      wmg.toString()
    );
    assertEquals(new Engineer("eng", 97, 48, queue).toString(),
      eng.toString()
    );
    assertEquals(new Knight("kng", 52, 47, queue).toString(),
      kng.toString()
    );
    assertEquals(new Thief("thf", 18, 94, queue).toString(),
      thf.toString()
    );
  }

  @Test
  void testHashCode() {
  }
}