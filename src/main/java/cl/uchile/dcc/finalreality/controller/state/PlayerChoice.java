package cl.uchile.dcc.finalreality.controller.state;

import cl.uchile.dcc.finalreality.exceptions.InvalidTransitionException;

public class PlayerChoice extends AbstractGameState {

  @Override
  public void execute() {
    this.changeState(new FinishedTurn());
  }

  @Override
  public void changeWeapon() {
  }

  @Override
  public void changeSpell(){
  }

  @Override
  public void changeTarget() {
  }

  @Override
  public boolean inPlayerChoice() {
    return true;
  }
}
