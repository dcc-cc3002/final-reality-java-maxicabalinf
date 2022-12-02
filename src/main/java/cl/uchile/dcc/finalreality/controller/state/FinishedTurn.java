package cl.uchile.dcc.finalreality.controller.state;

public class FinishedTurn extends AbstractGameState {

  @Override
  public void beginTimer() {
    this.changeState(new WaitingQueue());
  }

  @Override
  public boolean inFinishedTurn() {
    return true;
  }
}
