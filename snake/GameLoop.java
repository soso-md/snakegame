package snake;


import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Subhomoy Haldar
 * @version 2016.12.17
 */
public class GameLoop implements Runnable {
    private final GameField grid;
    private final GraphicsContext context;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean pause;
    private boolean keyIsPressed;
    private boolean deadSnake = false;
    GameController controller;
    int oldPoints = 1;

    public GameLoop(final GameField grid, final GraphicsContext context, GameController gc) {
    	this.controller = gc;
    	this.grid = grid;
        this.context = context;
        frameRate = 10;
        interval = 1000.0f / frameRate; // 1000 ms in a second
        running = true;   
        keyIsPressed = false;
    }



	@Override
    public void run() {
        while (running) {   
        	
        	while (pause) {
        		keyIsPressed = false;
        		System.out.println("pause");
        	}
            // Time the update and paint calls
            float time = System.currentTimeMillis();

            grid.update();
            Painter.paint(grid, context);
            keyIsPressed = false;
            
  
            if( grid.getSnake().getPoints().size() > oldPoints) {
            	controller.setScore(grid.getSnake());
            	oldPoints++;
            }
            
            if (!grid.getSnake().isSafe()) {
            	  stop();
            	  controller.gameOver(grid.getSnake());
//            	  deadSnake = true;
//                pause();
//                Painter.paintResetMessage(context);
//                break;
            }
            

            time = System.currentTimeMillis() - time;
           
            // Adjust the timing correctly
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
    

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    public void setKeyPressed() {
        keyIsPressed = true;
    }
    public void resetKeyPressed() {
        keyIsPressed = false;
    }
    
    public boolean isGameOver() {
    	return deadSnake;
    }

    public void pause() {
    	grid.getSnake().setStill();
    	//pause = true;
    }

    public boolean isPaused() {
        return grid.getSnake().isStill();
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public int getFrameRate() {
        return frameRate;
    }
    
    public void setFrameRate(int frameRate) {
    	this.interval = 1000.0f / frameRate;
        this.frameRate = frameRate;
    }

	public void resume() {
		pause = false;	
	}
}