package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private Rectangle rectangle;

    public Brick(int x, int y, int width, int height, Color color) {
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setColor(color);
        rectangle.fill();
    }

    public Brick() {

    }

    public void destroyBrick() {
        rectangle.delete();
    }

    public int getX() {
        return rectangle.getX();
    }

    public int getY() {
        return rectangle.getY();
    }

    public int getWidth() {
        return rectangle.getWidth();
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
