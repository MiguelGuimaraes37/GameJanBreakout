package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Bar {
    private Rectangle rectangle;

    public Bar(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void fill() {
        rectangle.fill();
    }


    public int getX() {
        return rectangle.getX();
    }

    public void setColor(Color c) {
        rectangle.setColor(c);
    }
    public void moveRight() {
        rectangle.translate(10, 0);
    }

    public void moveLeft() {
        rectangle.translate(-10, 0);
    }
}
