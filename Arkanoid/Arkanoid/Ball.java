package Arkanoid;

import java.awt.Point;

public class Ball
{
  private Arkanoid game;
  private int radius = 20;
  Point position = new Point(0, 0);
  
  Point movement = new Point(1, 1);
  float speed = 0.4F;
  
  public Ball(Arkanoid game) {
    this.game = game;
  }
  
  public void tick(double deltatime)
  {
    position.translate((int)(movement.x * (speed * deltatime)), (int)(movement.y * (speed * deltatime)));
    if (Math.abs(position.x) >= Math.abs(game.width / 2)) movement.x = (-movement.x);
    if (position.y <= -game.height / 2) movement.y = (-movement.y);
    if (position.y >= game.height / 2) { game.OnBallLost();
    }
    java.awt.Rectangle hitbox = new java.awt.Rectangle(position.x - radius, position.y - radius, radius * 2, radius * 2);
    Point pv = game.player.bounceVector(hitbox);
    movement.x *= x;
    movement.y *= y;
    
    for (int i = 0; i < game.blocks.size(); i++) {
      Block b = (Block)game.blocks.get(i);
      pv = b.bounceVector(hitbox);
      movement.x *= x;
      movement.y *= y;
      if ((x < 0) || (y < 0)) {
        game.OnBlockBroken(b);
        game.blocks.remove(b);
      }
    }
  }
  
  public void render(java.awt.Graphics g) {
    g.setColor(java.awt.Color.white);
    g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
  }
}
