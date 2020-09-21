package touro.snake;

import javax.swing.*;
import java.awt.*;

public class GardenView extends JComponent {

    private final Garden garden;
    public static final int CELL_SIZE = 1;

    public GardenView(Garden garden) {
        this.garden = garden;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrass(g);
        paintFood(g);
        paintSnake(g);
    }

    void paintGrass(Graphics g) {
        // Berger
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    void paintSnake(Graphics g) {
        g.setColor(Color.BLACK);
        for (Square s : garden.getSnake().getSquares()) {
            g.fillRect(s.getX()/100, s.getY()/100, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintFood(Graphics g) {
        // Berger
        if (garden.getFood() != null) {
            Food food = garden.getFood();
            g.setColor(Color.RED);

            int x = food.getX() / 100;
            int y = food.getY() / 100;
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }
}
