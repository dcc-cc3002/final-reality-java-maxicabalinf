package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import cl.uchile.dcc.finalreality.model.items.spell.*;
import cl.uchile.dcc.finalreality.model.items.weapon.Knife;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameController.rand;
import static org.junit.jupiter.api.Assertions.*;

class SpellFunctionTest {

  Spell thunder, fire, cure, venom, paralyze;
  Mage blackMage, whiteMage;
  Enemy enemy;
  BlockingQueue<GameCharacter> turnsQueue;
  Weapon staff, knife;

  // Hp and Mp values before modifying.
  int previousEnemyHp;
  int previousBlackMp;
  int previousBlackHp;
  int previousWhiteHp;
  int previousWhiteMp;

  /**
   * Store Hp and Mp of tested {@link Mage}s for later comparison.
   */
  void updateStats() {
    previousEnemyHp = enemy.getCurrentHp();
    previousBlackMp = blackMage.getCurrentMp();
    previousBlackHp = blackMage.getCurrentHp();
    previousWhiteMp = whiteMage.getCurrentMp();
    previousWhiteHp = whiteMage.getCurrentHp();
  }

  /**
   * Make sure the next random integer number from the GameController class satisfies a bound.
   *
   * @param lessThan
   *     the parameter to satisfy being less than
   * @param upBound
   *     the exclusive upper bound
   */
  void ensureRand(int lessThan, int upBound) {
    rand.setSeed(10);
    Random rand2 = new Random(10);
    while (rand2.nextInt(upBound) >= lessThan) {
      rand.nextInt(upBound);
    }
  }

  /**
   * Ensure a {@link Mage} has enough Mp to cast a {@link Spell}.
   *
   * @param mage
   *     the {@link Mage} whose Mp will be restored
   */
  void ensureMp(Mage mage) throws InvalidStatValueException {
    mage.setCurrentMp(40);
  }

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    // Spells
    thunder = new Thunder();
    fire = new Fire();
    cure = new Cure();
    venom = new Venom();
    paralyze = new Paralyze();

    // GameCharacters
    turnsQueue = new LinkedBlockingQueue<>();
    blackMage = new BlackMage("bMage", 5, 51, 40, turnsQueue);
    whiteMage = new WhiteMage("wMage", 31, 16, 40, turnsQueue);
    enemy = new Enemy("enemy", 55, 89, 69, turnsQueue);

    // Weapons
    staff = new Staff("staff", 57, 14, 60);
    knife = new Knife("knife", 23, 20);
  }

  @Test
  void affect() throws RestrictedSpellException, MissingStatException, InvalidStatValueException,
      NullWeaponException, RestrictedWeaponException {
    // Store Hp and Mp values before modifying. Restore Mp to mage.
    ensureMp(blackMage);
    updateStats();
    // region: THUNDER
    // Thunder spell should reduce enemy's Hp in an amount of staff's magicDamage with .3
    // probability.
    blackMage.equip(thunder);

    // No Spell can be cast if there's no Staff equipped
    //// No Weapon has been equipped yet, so it's null.
    assertThrows(NullWeaponException.class,
      () -> blackMage.cast(enemy));

    //// A Staff is needed.
    blackMage.equip(knife);
    assertThrows(RestrictedSpellException.class,
      () -> blackMage.cast(enemy));

    //// Staff will be equipped from now on. Casts are enabled then.
    blackMage.equip(staff);
    ensureRand(30, 100);
    blackMage.cast(enemy);
    assertEquals(
      previousEnemyHp - blackMage.getEquippedWeapon().getMagicDamage(),
      enemy.getCurrentHp()
    );

    // Thunder costs 15 Mp.
    assertEquals(previousBlackMp - 15, blackMage.getCurrentMp());
    // endregion

    // region : FIRE
    // Fire spell should reduce enemy's Hp in an amount of staff's magicDamage with .2
    // probability.
    ensureMp(blackMage);
    updateStats();
    blackMage.equip(fire);
    ensureRand(2, 10);
    blackMage.cast(enemy);
    assertEquals(
      previousEnemyHp - blackMage.getEquippedWeapon().getMagicDamage(),
      enemy.getCurrentHp()
    );

    // Fire costs 15 Mp.
    assertEquals(previousBlackMp - 15, blackMage.getCurrentMp());
    // endregion

    // region : CURE
    // Cure spell should improve an ally's Hp by 30%.
    ensureMp(whiteMage);
    updateStats();

    // No Spell can be cast if there's no Staff equipped
    //// No Weapon has been equipped yet, so it's null. WhiteMages can only equip Staves as weapons.
    assertThrows(NullWeaponException.class,
      () -> whiteMage.cast(enemy));

    //// Staff will be equipped from now on. Casts are enabled then.
    whiteMage.equip(staff);
    whiteMage.equip(cure);
    whiteMage.cast(blackMage);

    // Cure costs 15 Mp.
    if (previousBlackHp * 1.3 < blackMage.getMaxHp()) {
      assertEquals(previousBlackHp * 1.3, blackMage.getCurrentHp());
    }
    else { // Hp has a max value.
      assertEquals(blackMage.getMaxHp(), blackMage.getCurrentHp());
    }
    // endregion

    // region : VENOM
    // Venom spell should change the enemy's state to Envenomed.
    ensureMp(whiteMage);
    updateStats();
    whiteMage.equip(venom);
    whiteMage.cast(enemy);
    assertTrue(enemy.isEnvenomed());

    // Venom costs 40 Mp.
    assertEquals(previousWhiteMp - 40, whiteMage.getCurrentMp());
    // endregion

    // region: PARALYZE
    // Paralyze spell should change the enemy's state to Paralyzed.
    ensureMp(whiteMage);
    updateStats();
    whiteMage.equip(paralyze);
    whiteMage.cast(enemy);
    assertTrue(enemy.isParalyzed());

    // Paralyze costs 25 Mp.
    assertEquals(previousWhiteMp - 25, whiteMage.getCurrentMp());
    // endregion
  }
}