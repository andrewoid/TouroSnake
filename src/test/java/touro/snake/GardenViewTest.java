package touro.snake;

import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.JComponent.setDefaultLocale;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static touro.snake.GardenView.CELL_SIZE;

public class GardenViewTest {

    @Test
    public void paintGrass() {
        //given
        Garden garden = mock(Garden.class);
        GardenView view = new GardenView(garden);
        int width = view.getWidth();
        int height = view.getHeight();

        Graphics g = mock(Graphics.class);

        //when
        view.paintGrass(g);

        //then
        verify(g).fillRect(0, 0, width, height);
    }

    @Test
    public void paintSnake() {
        //given
        Snake snake = mock(Snake.class);
        Garden garden = mock(Garden.class);
        GardenView view = new GardenView(garden);
        Graphics g = mock(Graphics.class);

        List<Square> squares = List.of(
                new Square(50000, 20000),
                new Square(51000, 21000),
                new Square(52000, 22000)
        );

        when(garden.getSnake()).thenReturn(snake); //getting the snake from the garden
        when(snake.getSquares()).thenReturn(squares); //getting the list of squares created above

        //when
        view.paintSnake(g);

        //then  --checking that the cells (for which the coordinates are found in the list created above) are filled
        verify(g).fillRect(50000 /100, 20000 /100, CELL_SIZE, CELL_SIZE);
        verify(g).fillRect(51000 /100, 21000 /100, CELL_SIZE, CELL_SIZE);
        verify(g).fillRect(52000 /100, 22000 /100, CELL_SIZE, CELL_SIZE);
    }


    @Test
    public void paintFood() {
        //given
        Garden garden = mock(Garden.class);
        GardenView view = new GardenView(garden);

        when(garden.getFood()).thenReturn(mock(Food.class));

        Food food = garden.getFood();
        when(food.getX()).thenReturn(5000);
        when(food.getY()).thenReturn(10000);
        int x = food.getX()/100;
        int y = food.getY()/100;

        Graphics g = mock(Graphics.class);

        //when
        view.paintFood(g);

        //then
        verify(g).fillRect(x,y, GardenView.CELL_SIZE, GardenView.CELL_SIZE);
    }

    @Test
    public void paintFood_nullFood() {
        //given
        Garden garden = mock(Garden.class);
        GardenView view = new GardenView(garden);
        Graphics g = mock(Graphics.class);

        //when
        view.paintFood(g);

        //then
        verifyNoInteractions(g);
    }
}