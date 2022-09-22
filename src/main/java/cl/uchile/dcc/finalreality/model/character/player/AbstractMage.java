package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

abstract public class AbstractMage extends AbstractPlayerCharacter {
    protected int currentMp;
    protected final int maxMp;

    protected AbstractMage(final @NotNull String name, final int maxHp, final int defense,
    int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException {
        super(name, maxHp, defense, turnsQueue);
        Require.statValueAtLeast(0, maxMp, "Max MP");
        this.maxMp = maxMp;
        this.currentMp = maxMp;
    }
}
