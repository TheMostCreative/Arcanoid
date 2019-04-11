package Arkanoid;

public class GameThread extends Thread {
  private Arkanoid game;
  
  public GameThread(Arkanoid game) {
    this.game = game;
  }
  
  public void run() {
    game.isRunning = true;
    game.isPaused = false;
    game.lastUpdate = System.nanoTime();
    
    while (game.isRunning) {
      try {
        if (game.isPaused) {
          game.lastUpdate = System.nanoTime();
          Thread.sleep(1L);
        } else {
          game.tick();
          game.lastUpdate = System.nanoTime();
          Thread.sleep((100.0D / game.tickrate));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
