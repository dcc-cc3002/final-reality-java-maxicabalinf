package cl.uchile.dcc.finalreality.controller.state;

public class EnemyChoice extends AbstractGameState {

  @Override
  public void execute() {
    this.changeState(new FinishedTurn());
  }

  @Override
  public boolean inEnemyChoice() {
    return true;
  }
}
