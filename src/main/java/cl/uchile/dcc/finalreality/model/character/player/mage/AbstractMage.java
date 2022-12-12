package cl.uchile.dcc.finalreality.model.character.player.mage;

import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.AbstractPlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a <i>mage</i> character in the game.
 * A mage is a {@link PlayerCharacter} who can use magic and cast spells.
 *
 * <p>Mage characters aside from having all {@link GameCharacter} fields ({@code name},
 * {@code maxHp}, {@code defense}, {@code turnsQueue}) and abilities,
 * have <i>mana points</i> ({@code currentMp}, {@code maxMp}).
 *
 * @author <a href="https://github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public abstract class AbstractMage extends AbstractPlayerCharacter implements Mage {
  protected int currentMp;
  protected final int maxMp;
  protected Spell equippedSpell = null;

  protected AbstractMage(final @NotNull String name, final int maxHp, final int defense,
      int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    Require.statValueAtLeast(0, maxMp, "Max MP");
    this.maxMp = maxMp;
    this.currentMp = maxMp;
  }
  // region : BEHAVIORS

  /**
   * Sets the mage's equipped {@link Spell}.
   */
  public void setEquippedSpell(Spell spell) {
    this.equippedSpell = spell;
  }

  public abstract void equip(Spell spell) throws RestrictedSpellException;

  /**
   * Return this character's equipped {@link Spell}.
   */
  public Spell getEquippedSpell() {
    return equippedSpell;
  }

  /**
   * Cast the {@code equippedSpell} towards another {@link GameCharacter}.
   */
  public abstract void cast(GameCharacter character)
    throws RestrictedSpellException, InvalidStatValueException, MissingStatException, NullWeaponException;
  // endregion

  // region : ACCESSORS

  /**
   * Returns the character's current MP.
   */
  @Override
  public int getCurrentMp() {
    return currentMp;
  }

  /**
   * Sets the character's current MP to {@code newMp}.
   */
  @Override
  public void setCurrentMp(final int newMp) throws InvalidStatValueException {
    Require.statValueAtLeast(0, newMp, "Current MP");
    Require.statValueAtMost(maxMp, newMp, "Current MP");
    this.currentMp = newMp;
  }

  /**
   * Returns the character's max MP.
   */
  @Override
  public int getMaxMp() {
    return maxMp;
  }
  // endregion
}
