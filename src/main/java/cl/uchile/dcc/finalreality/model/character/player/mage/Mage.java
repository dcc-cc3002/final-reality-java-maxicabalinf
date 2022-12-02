package cl.uchile.dcc.finalreality.model.character.player.mage;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.items.spell.Spell;
import cl.uchile.dcc.finalreality.model.items.weapon.Staff;

/**
 * A {@link PlayerCharacter} type able to equip {@link Staff}s and cast spells.
 *
 * @author <a href="https://www.github.com/maxicabalinf">Maximiliano Cabalin F.</a>
 */
public interface Mage extends PlayerCharacter {

  // TODO review cast() method
  void cast(Spell spell, GameCharacter character) throws RestrictedSpellException, InvalidStatValueException, MissingStatException;

  void cast(GameCharacter character) throws RestrictedSpellException, InvalidStatValueException, MissingStatException;

  void equip(Spell spell) throws RestrictedSpellException;

  int getCurrentMp();

  void setCurrentMp(int newMp) throws InvalidStatValueException;

  int getMaxMp();
}
