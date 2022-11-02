package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Staff;

/**
 * A {@link PlayerCharacter} type able to equip {@link Staff}s and cast spells.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public interface Mage extends PlayerCharacter {

  int getCurrentMp();

  void setCurrentMp(int newMp) throws InvalidStatValueException;

  int getMaxMp();
}
