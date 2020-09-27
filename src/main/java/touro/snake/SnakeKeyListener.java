package touro.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyListener that is responsible for turning the Snake.
 */
public class SnakeKeyListener extends KeyAdapter {

    private final Snake snake;

    public SnakeKeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                snake.setDegrees(snake.getDegrees() - 5);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDegrees(snake.getDegrees() - 5);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDegrees(snake.getDegrees() + 5);
                break;
            case KeyEvent.VK_UP:
                snake.setDegrees(snake.getDegrees() + 5);
                break;
        }
    }
}
