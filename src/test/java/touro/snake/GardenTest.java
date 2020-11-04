package touro.snake;

import org.junit.Test;
import org.mockito.Mockito;
import touro.snake.strategy.SnakeStrategy;

import javax.sound.sampled.Clip;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GardenTest {
    private Snake snake = mock(Snake.class);
    private FoodFactory foodFactory = mock(FoodFactory.class);
    private SnakeEventListener listener = mock(SnakeEventListener.class);

    @Test
    public void moveSnake() {
        /*
        Tests that snake moves and that when snake's move does not result
        in death.
         */
        //given
        SnakeStrategy strategy = mock(SnakeStrategy.class);
        Garden garden = new Garden(snake, foodFactory, listener);

        doReturn(strategy).when(snake).getStrategy();
        doReturn(true).when(snake).inBounds();
        doReturn(false).when(snake).eatsSelf();
        Square square = mock(Square.class);
        doReturn(square).when(snake).getHead();

        //when and then
        assertTrue(garden.moveSnake());
        verify(snake).move();
    }

    @Test
    public void createFoodIfNecessary() {

        //given
        Garden garden = new Garden(snake, foodFactory, listener);
        when(foodFactory.newInstance()).thenReturn(mock(Food.class));

        //when
        garden.createFoodIfNecessary();

        //then
        verify(foodFactory).newInstance();
        assertNotNull(garden.getFood());
    }

    @Test
    public void onEatFood() {
        //given
        SnakeStrategy strategy = mock(SnakeStrategy.class);
        Food food = new Food(50, 20);
        when(foodFactory.newInstance()).thenReturn(food);
        SnakeEventListener listener = mock(SnakeEventListener.class);
        Garden garden = new Garden(snake, foodFactory, listener);
        List<Square> squares = List.of(new Square(50, 20));

        doReturn(strategy).when(snake).getStrategy();
        when(snake.inBounds()).thenReturn(true);
        when(snake.eatsSelf()).thenReturn(false);
        when(snake.getHead()).thenReturn(squares.get(0));

        //when
        garden.createFoodIfNecessary();
        garden.moveSnake();

        //then
        verify(listener).onEatFood();
    }
}