package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Ball {
    private Ellipse ellipse;
    private Direction nextDirection;
    private boolean firstMove;


    public Ball(Ellipse ellipse) {
        this.ellipse = ellipse;
        this.nextDirection = Direction.UP;
        this.firstMove=true;
    }

    public int getX() {
        return ellipse.getX();
    }

    public int getY(){
        return ellipse.getY();
    }

    public void fill() {
        ellipse.fill();
    }

    public void moveUp() {
        ellipse.translate(0, -10);
    }

    public void moveDiagonalDownLeft() {
        ellipse.translate(-10,10);
    }

    public void moveDiagonalDownRight() {
        ellipse.translate(10,10);
    }

    public void moveDiagonalUpRight() {
        ellipse.translate(10,-10);
    }

    public void moveDiagonalUpLeft() {
        ellipse.translate(-10,-10);
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }


    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setColor(Color color) {
        ellipse.setColor(color);
    }
}
