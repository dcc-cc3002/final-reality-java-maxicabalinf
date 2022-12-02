package cl.uchile.dcc.finalreality.controller.state;

public class UndeterminedCharacter extends AbstractGameState {

  @Override
  public void isEnemy() {
    this.changeState(new EnemyChoice());
  }

  @Override
  public void isAlly() {
    this.changeState(new PlayerChoice());
  }

  @Override
  public boolean inUndeterminedCharacter() {
    return true;
  }
}
