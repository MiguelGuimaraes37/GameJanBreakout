package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private Position position;
    private Rectangle rectangle;

    public Brick(Position position, Rectangle rectangle, Color color) {
        this.position = new Position(rectangle.getX(),rectangle.getY());
        this.rectangle = rectangle;
        rectangle.setColor(color);
        rectangle.fill();
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
