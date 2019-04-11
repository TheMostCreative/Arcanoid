package Arkanoid;

import java.awt.Point;

public class Player
{
  private Arkanoid game;
  int height = 17;
  int width = 150;
  Point position = new Point(0, 0);
  
  public Player(Arkanoid game) {
    this.game = game;
    position = new Point(0, height / 2 - height - 20);
  }
  

  public Point bounceVector(java.awt.Rectangle hitbox)
  {
    Point p = new Point(1, 1);
    java.awt.Rectangle hb_t = new java.awt.Rectangle(position.x - width / 2, position.y - height / 2, width, height / 3);
    java.awt.Rectangle hb_b = new java.awt.Rectangle(position.x - width / 2, position.y + height / 2 - height / 3, width, height / 3);
    java.awt.Rectangle hb_l = new java.awt.Rectangle(position.x - width / 2, position.y - height / 2, width / 10, height);
    java.awt.Rectangle hb_r = new java.awt.Rectangle(position.x + width / 2 - width / 10, position.y - height / 2, width / 10, height);
    if ((hb_t.intersects(hitbox)) || (hb_b.intersects(hitbox))) y = -1;
    if ((hb_r.intersects(hitbox)) || (hb_l.intersects(hitbox))) x = 1;
    return p;
  }
  
  public void render(java.awt.Graphics g) {
    g.setColor(java.awt.Color.lightGray);
    g.fillRect(position.x - width / 2, position.y - height / 2, width, height);
  }
}
