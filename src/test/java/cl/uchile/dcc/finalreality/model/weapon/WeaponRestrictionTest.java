package cl.uchile.dcc.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class WeaponRestrictionTest {
  Weapon axe, bow, knife, staff, sword;
  PlayerCharacter blackMage, engineer, knight, thief, whiteMage;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    // Weapons
    axe = new Axe("axe", 31, 29);
    bow = new Bow("bow", 5, 17);
    knife = new Knife("knife", 58, 86);
    staff = new Staff("staff", 88, 76, 74);
    sword = new Sword("sword", 75, 3);

    // Characters
    queue = new LinkedBlockingQueue<>();
    blackMage = new BlackMage("blackMage", 40, 2, 12, queue);
    engineer = new Engineer("engineer", 44, 89, queue);
    knight = new Knight("knight", 95, 83, queue);
    thief = new Thief("thief", 27, 84, queue);
    whiteMage = new WhiteMage("whiteMage", 27, 84, 9, queue);
  }

  @Test
  void equipTo() throws RestrictedWeaponException {
    // Sword
    knight.equip(sword); assertEquals(sword, knight.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> engineer.equip(sword));
    thief.equip(sword); assertEquals(sword, thief.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> blackMage.equip(sword));
    assertThrows(RestrictedWeaponException.class,
      () -> whiteMage.equip(sword));

    // Axe
    knight.equip(axe); assertEquals(axe, knight.getEquippedWeapon());
    engineer.equip(axe); assertEquals(axe, engineer.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> thief.equip(axe));
    assertThrows(RestrictedWeaponException.class,
      () -> blackMage.equip(axe));
    assertThrows(RestrictedWeaponException.class,
      () -> whiteMage.equip(axe));

    // Knife
    knight.equip(knife); assertEquals(knife, knight.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> engineer.equip(knife));
    thief.equip(knife); assertEquals(knife, thief.getEquippedWeapon());
    blackMage.equip(knife); assertEquals(knife, blackMage.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> whiteMage.equip(knife));

    // Staff
    assertThrows(RestrictedWeaponException.class,
      () -> knight.equip(staff));
    assertThrows(RestrictedWeaponException.class,
      () -> engineer.equip(staff));
    assertThrows(RestrictedWeaponException.class,
      () -> thief.equip(staff));
    blackMage.equip(staff); assertEquals(staff,
      blackMage.getEquippedWeapon());
    whiteMage.equip(staff); assertEquals(staff,
      whiteMage.getEquippedWeapon());

    // Bow
    assertThrows(RestrictedWeaponException.class,
      () -> knight.equip(bow));
    engineer.equip(bow); assertEquals(bow, engineer.getEquippedWeapon());
    thief.equip(bow); assertEquals(bow, thief.getEquippedWeapon());
    assertThrows(RestrictedWeaponException.class,
      () -> blackMage.equip(bow));
    assertThrows(RestrictedWeaponException.class,
      () -> whiteMage.equip(bow));
  }
}