package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.Mage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;
import cl.uchile.dcc.finalreality.model.items.spell.*;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;
import cl.uchile.dcc.finalreality.model.items.weapon.Sword;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class SpellFunctionTest {

  Spell thunder, fire, cure, venom, paralyze;
  Mage blackMage, whiteMage;
  Enemy enemy;
  BlockingQueue<GameCharacter> turnsQueue;
  Weapon staff, sword;

  // Hp and Mp values before modifying.
  int previousEnemyHp;
  int previousBlackMp;
  int previousBlackHp;
  int previousWhiteHp;
  int previousWhiteMp;

  void updateStats() {
    previousEnemyHp = enemy.getCurrentHp();
    previousBlackMp = blackMage.getCurrentMp();
    previousBlackHp = blackMage.getCurrentHp();
    previousWhiteMp = whiteMage.getCurrentMp();
    previousWhiteHp = whiteMage.getCurrentHp();
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
    blackMage = new BlackMage("bMage", 5, 51, 26, turnsQueue);
    whiteMage = new WhiteMage("wMage", 31, 16, 86, turnsQueue);
    enemy = new Enemy("enemy", 55, 89, 69, turnsQueue);

    // Weapons
    staff = new Staff("staff", 57, 14, 60);
    sword = new Sword("sword", 23, 20);
  }

  @Test
  void affect() throws RestrictedSpellException, MissingStatException, InvalidStatValueException {
    // Store Hp and Mp values before modifying.
    updateStats();

    // region: THUNDER
    // Thunder spell should reduce enemy's Hp in an amount of staff's magicDamage with .3
    // probability. We assume it does affect the enemy.
    blackMage.cast(thunder, enemy);
    assertEquals(
      previousEnemyHp - blackMage.getEquippedWeapon().getMagicDamage(),
      enemy.getCurrentHp()
    );

    // Thunder costs 15 Mp.
    assertEquals(previousBlackMp - 15, blackMage.getCurrentMp());
    // endregion

    // region : FIRE
    // Fire spell should reduce enemy's Hp in an amount of staff's magicDamage with .2
    // probability. We assume it does affect the enemy.
    updateStats();
    blackMage.cast(fire, enemy);
    assertEquals(
      previousEnemyHp - blackMage.getEquippedWeapon().getMagicDamage(),
      enemy.getCurrentHp()
    );

    // Fire costs 15 Mp.
    assertEquals(previousBlackMp - 15, blackMage.getCurrentMp());
    // endregion

    // region : CURE
    // Cure spell should improve an ally's Hp by 30%.
    updateStats();
    whiteMage.cast(cure, blackMage);

    // Cure costs 15 Mp.
    if (previousBlackHp * 1.3 < blackMage.getMaxHp()) {
      assertTrue(previousBlackHp * 1.3 == blackMage.getCurrentHp());
    }
    else { // Hp has a max value.
      assertTrue(blackMage.getMaxHp() == blackMage.getCurrentHp());
    }
    // endregion

    // region : VENOM
    // Venom spell should change the enemy's state to Envenomed.
    updateStats();
    whiteMage.cast(venom, enemy);
    assertTrue(enemy.isEnvenomed());

    // Venom costs 40 Mp.
    assertEquals(previousWhiteMp - 40, whiteMage.getCurrentMp());
    // endregion

    // region: PARALYZE
    // Paralyze spell should change the enemy's state to Paralyzed.
    updateStats();
    whiteMage.cast(paralyze, enemy);
    assertTrue(enemy.isParalyzed());

    // Paralyze costs 25 Mp.
    assertEquals(previousWhiteMp - 25, whiteMage.getCurrentMp());
    // endregion
  }

  @Test
  void testAffect() {
  }
}