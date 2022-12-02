package cl.uchile.dcc.finalreality.controller.state;

public class WaitingQueue extends AbstractGameState {

  @Override
  public void pickUpCharacter() {
    this.changeState(new UndeterminedCharacter());
  }

  @Override
  public void emptyQueue() {
  }

  @Override
  public void allEnemiesDead() {
    this.changeState(new EndOfGame());
  }

  @Override
  public void allAlliesDead() {
    this.changeState(new EndOfGame());
  }

  @Override
  public boolean inWaitingQueue() {
    return true;
  }
}
