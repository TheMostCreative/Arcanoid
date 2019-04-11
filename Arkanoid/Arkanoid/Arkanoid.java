package Arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Arkanoid extends javax.swing.JPanel
{
  public int width;
  public int height;
  public int tickrate = 30;
  public Player player;
  private Ball ball;
  private int balls = 3;
  public boolean isRunning = false; public boolean isPaused = false;
  

  private Color[] rowColors = { Color.gray, Color.pink, Color.red.darker(), Color.green.darker(), Color.yellow.darker(), Color.blue.darker() };
  
  public ArrayList<Block> blocks;
  
  private java.awt.Image backgroundTile;
  
  public long lastUpdate;
  private GameThread gameThread;
  
  public Arkanoid(int width, int height)
  {
    this.width = width;
    this.height = height;
    
    backgroundTile = new javax.swing.ImageIcon(getClass().getResource("/Arkanoid/Backgroundtile.png")).getImage();
    
    reset();
    
    setFocusable(true);
    addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(java.awt.event.MouseEvent e) {
        player.position.x = (e.getX() - getWidth() / 2);
        repaint();
      }
    });
    addKeyListener(new java.awt.event.KeyAdapter()
    {
      public void keyPressed(java.awt.event.KeyEvent e)
      {
        if ((e.getKeyCode() == 32) && (!isRunning)) run();
        if (e.getKeyCode() == 27) togglePause();
        if (e.getKeyCode() == 81) quit();
      }
    });
  }
  
  public void reset()
  {
    player = new Player(this);
    ball = new Ball(this);
    balls = 3;
    
    createBlocks(6, 10);
  }
  
  public void run() {
    if ((gameThread != null) && (gameThread.isAlive())) gameThread.interrupt();
    reset();
    gameThread = new GameThread(this);
    gameThread.start();
  }
  
  public void togglePause() {
    isPaused = (!isPaused);
  }
  
  public void quit() {
    isRunning = false;
  }
  
  private void createBlocks(int rows, int columns)
  {
    blocks = new ArrayList();
    
    int gap = 10;
    
    float w = (width - 10.0F) / columns - 10.0F;
    float h = 30.0F;
    for (int x = 0; x < columns; x++) {
      for (int y = 0; y < rows; y++) {
        Block b = new Block();
        mainColor = rowColors[(y % rowColors.length)];
        position.x = ((int)(x * (w + gap) + gap) - width / 2);
        position.y = ((int)(y * (h + gap) + gap) - height / 2);
        height = ((int)h);
        width = ((int)w);
        blocks.add(b);
      }
    }
  }
  
  public void OnBallLost() {
    balls -= 1;
    if (balls <= 0) OnGameOver(false); else
      ball.position = new java.awt.Point(0, 0);
  }
  
  public void OnBlockBroken(Block b) {}
  
  public void OnGameOver(boolean won) {
    quit();
  }
  
  public void tick() {
    double deltatime = (System.nanoTime() - lastUpdate) / 1000000.0D;
    ball.tick(deltatime);
    if (blocks.isEmpty()) OnGameOver(true);
    repaint();
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    g.translate((getWidth() - width) / 2, (getHeight() - height) / 2);
    
    int bgScale = 3;
    for (int x = 0; x < width; x += backgroundTile.getWidth(null) * bgScale) {
      for (int y = 0; y < height; y += backgroundTile.getHeight(null) * bgScale) {
        g.drawImage(backgroundTile, x, y, backgroundTile.getWidth(null) * bgScale, backgroundTile.getHeight(null) * bgScale, null);
      }
    }
    
    g.translate(width / 2, height / 2);
    
    player.render(g);
    ball.render(g);
    
    if (blocks != null) for (int i = 0; i < blocks.size(); i++) { ((Block)blocks.get(i)).render(g);
      }
    g.setColor(Color.green);
    g.setFont(new java.awt.Font("Arial", 1, 30));
    String msg = "";
    if (!isRunning) { msg = "Press space to win";
    } else if (isPaused) msg = "Game Paused";
    java.awt.FontMetrics fm = g.getFontMetrics();
    g.drawString(msg, -fm.stringWidth(msg) / 2, fm.getHeight() / 2);
  }
}
