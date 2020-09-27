package touro.snake;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SnakeKeyListenerTest {

    @Test
    public void keyPressed_left() {
        //given
        Snake snake = mock(Snake.class);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        //when
        snakeKeyListener.keyPressed(keyEvent);

        //then
        verify(snake).setDegrees(-5);
        verify(snake).getDegrees();
        verifyNoMoreInteractions(snake);
    }

    @Test
    public void keyPressed_right() {
        //given
        Snake snake = mock(Snake.class);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

        //when
        snakeKeyListener.keyPressed(keyEvent);

        //then
        verify(snake).setDegrees(5);
        verify(snake).getDegrees();
        verifyNoMoreInteractions(snake);
    }

    @Test
    public void keyPressed_up() {
        //given
        Snake snake = mock(Snake.class);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        //when
        snakeKeyListener.keyPressed(keyEvent);

        //then
        verify(snake).setDegrees(5);
        verify(snake).getDegrees();
        verifyNoMoreInteractions(snake);
    }

    @Test
    public void keyPressed_down() {
        //given
        Snake snake = mock(Snake.class);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        //when
        snakeKeyListener.keyPressed(keyEvent);

        //then
        verify(snake).setDegrees(-5);
        verify(snake).getDegrees();
        verifyNoMoreInteractions(snake);
    }

    @Test
    public void keyPressed_nonArrowKey() {
        //given
        Snake snake = mock(Snake.class);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        KeyEvent keyEvent = mock(KeyEvent.class);

        //when
        snakeKeyListener.keyPressed(keyEvent);

        //then
        verifyZeroInteractions(snake);
    }
}