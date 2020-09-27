package touro.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model object that represents the Snake and allows it to grow, turn and move.
 */
public class Snake {

    private static final int START_LENGTH = 6;

    private final List<Square> squares = new ArrayList<>();

    private boolean grow = false;

    private int degrees = 180;

    public Snake() {
        createSnake();
    }

    /**
     * Creates the Snake of length START_LENGTH, starting at the middle of the Garden.
     */
    private void createSnake() {
        int x = Garden.WIDTH / 2;
        int y = Garden.HEIGHT / 2;
        for (int i = 0; i < START_LENGTH; i++) {
            squares.add(new Square((x + i), y));
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    /**
     * Grows the Snake one square.
     */
    public void grow() {
        setGrow(true);
    }

    public Square getHead() {
        return squares.get(0);
    }

    /**
     * Moves the Snake forward in whatever direction the head is facing.
     */
    public void move() {

        //save head position in variable previous square
        Square previousHead = getHead();
        int x = previousHead.getX();
        int y = previousHead.getY();

        double radians = Math.toRadians(degrees);

        Square newSquare;
        int newX = (int) Math.round(x + Math.cos(radians) * 1000);
        int newY = (int) Math.round(y + Math.sin(radians) * 1000);
        newSquare = new Square( newX, newY);

        squares.add(0, newSquare);
        if (!getGrow()) {
            squares.remove(squares.size() - 1);
        } else {
            setGrow(false);
        }


    }

    /**
     * @param food
     * @return true if the Food intersects with the Snake, otherwise false.
     */
    public boolean contains(Food food) {
        if(food == null) {
            return false;
        }
        int cellsize = GardenView.CELL_SIZE;
        int headX = getHead().getX() / 100;
        int headY = getHead().getY() / 100;
        Rectangle headSquare = new Rectangle(headX,headY,cellsize, cellsize);
        int foodX = food.getX() / 100;
        int foodY = food.getY() / 100;
        Rectangle foodSquare = new Rectangle(foodX, foodY, cellsize, cellsize);
        return headSquare.intersects(foodSquare);


    }

    /**
     * @return true if the Snake is within the bounds of the Garden, otherwise false.
     */
    public boolean inBounds() {
        Square head = getHead();
        int x = head.getX();
        int y = head.getY();

        return x > 0 && x < Garden.WIDTH && y > 0 && y < Garden.HEIGHT;
    }

    /**
     * @return true if the head of the Snake has intersected with itself, otherwise false.
     */
    public boolean eatsSelf() {
        Square head = getHead();

        //snake eats itself if it hits its body at 4th square or beyond
        //loop starts at 4th square and checks if head coordinates matches body coordinates
        for (int i = 3; i < squares.size(); i++) {
            Square bodySquare = squares.get(i);
            if (bodySquare.equals(head)) {
                return true;
            }

        }
        return false;
    }


    public boolean getGrow() {
        return grow;
    }

    public void setGrow(boolean grow) {
        this.grow = grow;
    }

    public int getDegrees() { return degrees; }

    public void setDegrees(int degrees) { this.degrees = degrees; }

}
