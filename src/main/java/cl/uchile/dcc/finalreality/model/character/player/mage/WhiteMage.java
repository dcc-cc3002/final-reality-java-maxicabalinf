/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player.mage;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PlayerCharacter} that can equip {@code Staff}s and use <i>white magic</i>.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalín F.</a>
 */

public class WhiteMage extends AbstractMage {

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param maxHp
   *     the character's max hp
   * @param defense
   *     the character's defense
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public WhiteMage(final @NotNull String name, final int maxHp, final int defense,
      int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, maxMp, turnsQueue);
  }

  /**
   * Cast the {@code equippedSpell} to affect a {@link GameCharacter}.
   *
   * @param target
   *     the {@link GameCharacter} to recieve the {@link Spell}
   * @throws RestrictedSpellException
   *     if this mage is unable to cast the {@link Spell}
   * @throws InvalidStatValueException
   *     if an out-of-bounds value is set for the target stats
   */
  @Override
  public void cast(GameCharacter target)
      throws RestrictedSpellException, InvalidStatValueException,
      MissingStatException, NullWeaponException {
    // The Mage must have enough Mp to cast the Spell, and must carry a Staff.
    if (!getEquippedWeapon().isStaff()) {
      throw new RestrictedSpellException("A Staff must be equipped to cast any Spell");
    }
    if (getCurrentMp() - equippedSpell.getCost() >= 0) {
      equippedSpell.affect(target, this);
      setCurrentMp(getCurrentMp() - equippedSpell.getCost());
    }
  }

  /**
   * Equips a {@link Weapon} to a {@link WhiteMage}.
   *
   * @param weapon
   *     the {@link Weapon} to be equipped
   * @throws RestrictedWeaponException
   *     error thrown if {@link WhiteMage} is unable to equip such {@code weapon}
   */
  @Override
  public void equip(Weapon weapon) throws RestrictedWeaponException {
    weapon.equipTo(this);
  }

  @Override
  public void equip(Spell spell) throws RestrictedSpellException {
    spell.equipTo(this);
  }

  // region : UTILITY METHODS
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final WhiteMage that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && currentHp == that.currentHp
        && currentMp == that.currentMp
        && maxMp == that.maxMp
        && name.equals(that.name)
        && maxHp == that.maxHp
        && defense == that.defense;
  }

  @Override
  public String toString() {
    return "WhiteMage{maxHp=%d, currentHp=%d, maxMp=%d, currentMp=%d, defense=%d, name='%s'}"
      .formatted(maxMp, currentHp, maxMp, currentMp, defense, name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(WhiteMage.class, name, maxHp, defense, maxMp);
  }
  // endregion
}
