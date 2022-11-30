/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedWeaponException;
import cl.uchile.dcc.finalreality.model.character.AbstractCharacter;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.items.weapon.AbstractWeapon;
import cl.uchile.dcc.finalreality.model.items.weapon.Weapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a player-controlled character in the game.
 *
 * <p>All player characters have a {@code name}, a maximum amount of <i>hit points</i>
 * ({@code maxHp}), a {@code defense} value, a queue of {@link GameCharacter}s that are
 * waiting for their turn ({@code turnsQueue}), and can equip a {@link AbstractWeapon}.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author ~Your name~
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
    PlayerCharacter {
  protected Weapon equippedWeapon = null;

  /**
   * Creates a new character.
   * This constructor is <b>protected</b>, because it'll only be used by subclasses.
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
  protected AbstractPlayerCharacter(@NotNull final String name, final int maxHp,
      final int defense, @NotNull final BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
  }

  /**
   * Creates a new {@code thread} to add an {@code enemy} to a {@code queue} after some
   * {@code delay} depending on its equipped {@link Weapon}'s {@code weight}.
   *
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(
      /* command = */ this::addToQueue,
      /* delay = */ this.getEquippedWeapon().getWeight() / 10,
      /* unit = */ TimeUnit.SECONDS);
  }

  /**
   * Equips a weapon to the character.
   */
  @Override
  public abstract void equip(Weapon weapon) throws RestrictedWeaponException;

  /**
   * Returns the character's equipped weapon.
   */
  @Override
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }


  /**
   * Sets the character's equipped weapon.
   */
  public void setEquippedWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
  }

  /**
   * Attack another {@link GameCharacter}.
   */
  public void attack(GameCharacter character) throws InvalidStatValueException {
    character.beAttacked(this.getEquippedWeapon().getDamage());
  }
}
