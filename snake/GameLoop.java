package snake;

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

    public GameLoop(final GameField grid, final GraphicsContext context) {
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
            
            if (!grid.getSnake().isSafe()) {
                pause();
                Painter.paintResetMessage(context);
                break;
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
        this.frameRate = frameRate;
    }

	public void resume() {
		pause = false;	
	}
}