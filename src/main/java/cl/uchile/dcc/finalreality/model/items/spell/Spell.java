package cl.uchile.dcc.finalreality.model.items.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.MissingStatException;
import cl.uchile.dcc.finalreality.exceptions.RestrictedSpellException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.mage.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.mage.WhiteMage;

public interface Spell {
  void affect(GameCharacter character, BlackMage blackMage)
    throws RestrictedSpellException, InvalidStatValueException, MissingStatException;

  void affect(GameCharacter character, WhiteMage whiteMage)
      throws RestrictedSpellException;

  void equipTo(BlackMage blackMage) throws RestrictedSpellException;

  void equipTo(WhiteMage whiteMage) throws RestrictedSpellException;
}
