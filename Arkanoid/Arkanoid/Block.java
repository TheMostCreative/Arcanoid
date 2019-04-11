package Arkanoid;

import java.awt.Point;

public class Block {
  public Point position = new Point(0, 0);
  public int width = 70;
  public int height = 35;
  public java.awt.Color mainColor = java.awt.Color.red.darker();
  
  public Block() {}
  
  public Point bounceVector(java.awt.Rectangle hitbox) { Point p = new Point(1, 1);
    java.awt.Rectangle hb_t = new java.awt.Rectangle(position.x, position.y, width, height / 3);
    java.awt.Rectangle hb_b = new java.awt.Rectangle(position.x, position.y + height - height / 3, width, height / 3);
    java.awt.Rectangle hb_l = new java.awt.Rectangle(position.x, position.y, width / 10, height);
    java.awt.Rectangle hb_r = new java.awt.Rectangle(position.x + width - width / 10, position.y, width / 10, height);
    if ((hb_t.intersects(hitbox)) || (hb_b.intersects(hitbox))) y = -1;
    if ((hb_r.intersects(hitbox)) || (hb_l.intersects(hitbox))) x = -1;
    return p;
  }
  
  public void render(java.awt.Graphics g) {
    g.setColor(mainColor);
    g.fillRect(position.x, position.y, width, height);
    
    for (int i = 0; i < height / 4; i++) {
      g.setColor(mainColor.darker());
      g.drawLine(position.x + i, position.y + height - i, position.x + width - 1, position.y + height - i);
      g.drawLine(position.x + width - 1 - i, position.y + i, position.x + width - 1 - i, position.y + height);
      g.setColor(mainColor.brighter());
      g.drawLine(position.x, position.y + i, position.x + width - 1 - i, position.y + i);
      g.drawLine(position.x + i, position.y + height - i, position.x + i, position.y);
    }
  }
}
